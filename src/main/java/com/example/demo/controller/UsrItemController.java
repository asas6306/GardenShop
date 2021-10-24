package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Item;
import com.example.demo.dto.Rq;
import com.example.demo.service.BasketService;
import com.example.demo.service.ItemService;
import com.example.demo.service.SimplerService;
import com.example.demo.util.ResultData;
import com.example.demo.util.Util;

@Controller
public class UsrItemController extends _BaseController {
	@Autowired
	ItemService is;
	@Autowired
	BasketService bs;
	@Autowired
	SimplerService ss;
	
	
	@RequestMapping("/usr/item/list")
	public String list(HttpServletRequest req, @RequestParam(defaultValue = "all") String group, @RequestParam(defaultValue = "1") int page) {
		
		int itemsCnt = is.getItemsCnt(group);
		
		// 페이징
		if(itemsCnt != 0) {
			int pageCnt = 10;
			page = ss.page(req, page, pageCnt, itemsCnt);
			
			List<Item> items = is.getItemsForPaging(group, page, pageCnt);
			req.setAttribute("items", items);
		} else {
			req.setAttribute("items", null);
		}
		
		return "/usr/item/list";
	}
	
	@RequestMapping("/usr/item/basket")
	public String basket(HttpServletRequest req) {
		
		Rq rq = (Rq)req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		List<Item> items = bs.getBasketList(uid);
		req.setAttribute("items", items);
		
		return "/usr/item/basket";
	}
	
	@RequestMapping("/usr/item/putIn")
	@ResponseBody
	public String putIn(int iid, int uid) {
		
		ResultData basketPutInRd = bs.putIn(iid, uid);
		
		return Util.msgAndBack(basketPutInRd.getMsg());
	}
	
	@RequestMapping("/usr/item/putOut")
	@ResponseBody
	public String putOut(HttpServletRequest req, int bid) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		Item item = is.getItemByBid(bid);
		
		if(item == null)
			return msgAndBack(req, "해당 상품이 존재하지 않습니다.");
		else if(uid != item.getUid())
			return msgAndBack(req, "해당 상품을 주문 할 수 없습니다.");
		else {
			ResultData basketPutOutRd = bs.putOut(bid);
			return Util.msgAndReplace(basketPutOutRd.getMsg(), "basket");
		}
	}
	
	@RequestMapping("/usr/item/order")
	public String order(HttpServletRequest req, int bid) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		Item item = is.getItemByBid(bid);
		
		if(item == null)
			return msgAndBack(req, "해당 상품이 존재하지 않습니다.");
		else if(uid != item.getUid())
			return msgAndBack(req, "해당 상품을 주문 할 수 없습니다.");
		else {
			req.setAttribute("item", item);
			return "/usr/item/order";
		}
	}
	
	@RequestMapping("/usr/item/doOrder")
	@ResponseBody
	public String doOrder(HttpServletRequest req, int bid) {
		Rq rq = (Rq) req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		Item item = is.getItemByBid(bid);
		
		if(item == null)
			return msgAndBack(req, "해당 상품이 존재하지 않습니다.");
		else if(uid != item.getUid())
			return msgAndBack(req, "해당 상품을 주문 할 수 없습니다.");
		else {
			ResultData doOrderRd = is.doOrder(bid);
			return Util.msgAndReplace(doOrderRd.getMsg(), "orderList");
		}
	}
	
	@RequestMapping("/usr/item/orderList")
	public String orderList(HttpServletRequest req, int bid) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		int uid = rq.getLoginedMemberUid();
		
		
		
		return "/usr/item/order";
	}
	
	@RequestMapping("/usr/item/chkbox")
	public String chkbox(HttpServletRequest req) {
		
		String[] chk = req.getParameterValues("chk");
		
		System.out.println(chk.length);
		
		return "/usr/item/order";
	}
}
