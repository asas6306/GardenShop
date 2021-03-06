CREATE DATABASE shop;

USE shop;

# CREATE TABLE item(
# iid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
# `name` CHAR(30) NOT NULL,
# `group` CHAR(30) NOT NULL,
# price INT(30) UNSIGNED NOT NULL
# );
ALTER TABLE item ADD `regDate` DATETIME DEFAULT NOW();

# CREATE TABLE itemDetail(
# iid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
# `count` INT(5) UNSIGNED NOT NULL DEFAULT 0,
# orderd  INT(5) UNSIGNED NOT NULL DEFAULT 0,
# `order` INT(5) UNSIGNED NOT NULL DEFAULT 0
# );

CREATE TABLE car(
cid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
`name` CHAR(30) NOT NULL,
`year` CHAR(4) NOT NULL,
`group` CHAR(30) NOT NULL,
price INT(30) UNSIGNED NOT NULL
);
ALTER TABLE car ADD `code` CHAR(5) NOT NULL AFTER `name`;
# 샘플
# INSERT INTO genfile SET relTypeCode = 'car', relId = 5, originFileName = 'SONATA.png', fileExt = 'png', typeCode = 'common', type2Code = 'SEDAN', fileSize = 341276, fileExtTypeCode = 'img', fileExtType2Code = 'png', fileNo = 0, fileDir = '5';
# INSERT INTO genfile SET relTypeCode = 'car', relId = 6, originFileName = 'TUCSON.png', fileExt = 'png', typeCode = 'common', type2Code = 'SUV', fileSize = 391467, fileExtTypeCode = 'img', fileExtType2Code = 'png', fileNo = 0, fileDir = '6';

CREATE TABLE carDetail(
cid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
sales INT(5) UNSIGNED NOT NULL DEFAULT 0, # 판매량
`count` INT(5) UNSIGNED NOT NULL DEFAULT 0, # 보유수량
orderd  INT(5) UNSIGNED NOT NULL DEFAULT 0, # 받은주문
`order` INT(5) UNSIGNED NOT NULL DEFAULT 0  # 발주
);

CREATE TABLE `order` (
oid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
uid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT, 
cid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
cnt INT(5) UNSIGNED NOT NULL,
`regDate` DATETIME DEFAULT NOW(),
`delDate` DATETIME DEFAULT NULL
);
ALTER TABLE `order` ADD confirm TINYINT(1) NOT NULL DEFAULT 0 AFTER regDate; # 구매확정

CREATE TABLE `member`(
uid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
ID CHAR(30) NOT NULL,
PW VARCHAR(100) NOT NULL,
nickname CHAR(30) NOT NULL,
NAME CHAR(30) NOT NULL,
email CHAR(50) NOT NULL,
phoneNo CHAR(20) NOT NULL,
address1 CHAR(100) NOT NULL,
address2 CHAR(100) NOT NULL,
regDate DATETIME DEFAULT NOW(),
updateDate DATETIME DEFAULT NOW(),
delState TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
delDate DATETIME
);
/* member테이블에 authKey칼럼 추가 및 초기화 */
/* UUID()는 날짜기반 난수 생성이기 때문에 유추가 가능함 */
ALTER TABLE MEMBER ADD authKey CHAR(130) UNIQUE KEY DEFAULT UUID() AFTER PW;

#authLevel 생성 및 1번 uid 관리자 권한(3:일반회원, 7:관리자)
ALTER TABLE `member` ADD authLevel TINYINT(2) UNSIGNED DEFAULT 3 AFTER PW;
UPDATE `member` SET authLevel = 7 WHERE uid = 1;

CREATE TABLE `genFile` (
`fid` INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT, #번호
`regDate` DATETIME DEFAULT NOW(), # 작성날짜
`updateDate` DATETIME DEFAULT NOW(), # 갱신날짜
`delDate` DATETIME DEFAULT NULL, # 삭제날짜
`delState` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0, # 상태(0:미삭제, 1:삭제)
`relTypeCode` CHAR(50) NOT NULL, # 관련 데이터 타입(article, member, etc...)
`relId` INT(10) UNSIGNED NOT NULL, # 관련 데이터 번호
`originFileName` VARCHAR(100) NOT NULL, # 업로드 당시 파일명
`fileExt` CHAR(10) NOT NULL, # 확장자
`typeCode` CHAR(20) NOT NULL, # 종류코드
`type2Code` CHAR(20) NOT NULL, # 종류2코드 일종의 카테고리
`fileSize` INT(10) UNSIGNED NOT NULL, # 파일크기
`fileExtTypeCode` CHAR(10) NOT NULL, # 파일 규격 코드(ex. img)
`fileExtType2Code` CHAR(10) NOT NULL, # 파일 규격2 코드(ex. jpg, png)
`fileNo` SMALLINT(2) UNSIGNED NOT NULL, # 파일번호
`fileDir` CHAR(20) NOT NULL, # 파일이 저장되는 폴더명
KEY `relId` (`relId`, `relTypeCode`, `typeCode`, `type2Code`, `fileNo`)
);

# 부가정보테이블
CREATE TABLE attr (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `relTypeCode` CHAR(20) NOT NULL,
    `relId` INT(10) UNSIGNED NOT NULL,
    `typeCode` CHAR(30) NOT NULL,
    `type2Code` CHAR(70) NOT NULL,
    `value` TEXT NOT NULL
);

# attr 유니크 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`);

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`, `type2Code`);

# attr에 만료날짜 추가
ALTER TABLE `attr` ADD COLUMN `expireDate` DATETIME NULL AFTER `value`;

# 임시로 만들어진 회원은, 비번변경할 필요가 없도록 설정
INSERT INTO attr (
	regDate,
	updateDate,
	relTypeCode,
	relId,
	typeCode,
	type2Code,
	`value`,
	expireDate
)
SELECT NOW(), NOW(), 'member', uid, 'extra', 'needToChangePassword', 0, NULL
FROM `member`;

CREATE TABLE auth (
authLevel INT(3) UNSIGNED NOT NULL,
authName CHAR(20) NOT NULL
);
INSERT INTO auth SET authLevel = 3, authName = '일반회원';
INSERT INTO auth SET authLevel = 7, authName = '관리자';

CREATE TABLE counsel(
cid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
uid INT(10) UNSIGNED NOT NULL,
target CHAR(30) NOT NULL,
regDate DATETIME DEFAULT NOW(),
respState TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
respDate DATETIME
);
ALTER TABLE counsel ADD `year` CHAR(4) NOT NULL AFTER target;

CREATE TABLE `basket`(
bid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
uid INT(10) UNSIGNED NOT NULL,
iid INT(10) UNSIGNED NOT NULL,
`count`  INT(5) UNSIGNED NOT NULL
);

CREATE TABLE article (
aid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
uid INT(10) UNSIGNED NOT NULL,
title CHAR(50) NOT NULL,
`body` TEXT NOT NULL,
regDate DATETIME DEFAULT NOW(),
updateDate DATETIME DEFAULT NOW(),
blindState TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
blindDate DATETIME,
delState TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
delDate DATETIME,
`like` INT(10) UNSIGNED NOT NULL DEFAULT 0,
articleType CHAR(50) NOT NULL
);
ALTER TABLE article ADD group CHAR(10) NOT NULL;