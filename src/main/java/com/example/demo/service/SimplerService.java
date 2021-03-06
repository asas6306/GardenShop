package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class SimplerService {
	// @@@@@@@@@@@ Service @@@@@@@@@@@
	public int page(HttpServletRequest req, int page, int pageCnt, int itemsCnt) {
		if(page < 1)
			page = 1;
		
		int allPageCnt = (int)(Math.ceil((double)itemsCnt / pageCnt));
		if(allPageCnt == 0)
			allPageCnt = 1;
		
		if(page > allPageCnt) {
			page = allPageCnt;
		} else if(page < 1) {
			page = 1;
		}
		
		int pageStack;
		int pageIndex = 5;
		if(page % pageIndex == 0) {
			pageStack = page;
		} else {
			pageStack = ((int)(Math.floor(page / pageIndex)) + 1) * pageIndex;
		}
		
		List<Integer> printPageIndexs = new ArrayList<Integer>();
		for(int i = 4; i >= 0; i--) 
			if(pageStack - i <= allPageCnt)
				printPageIndexs.add(pageStack - i);
		req.setAttribute("page", page);
		page = (page - 1) * pageCnt;
		req.setAttribute("printPageIndexs", printPageIndexs);
		int printPageIndexUp = printPageIndexs.get(printPageIndexs.size()-1) + 1;
		req.setAttribute("printPageIndexUp", printPageIndexUp);
		int printPageIndexDown = printPageIndexs.get(0) - 1;
		req.setAttribute("printPageIndexDown", printPageIndexDown);
		
		return page;
	}
}
