--회원 부가정보
CREATE TABLE member_add_info (
	username 		VARCHAR(30) 	NOT NULL,			--아이디
	fullname 		VARCHAR(20),						--이름
	tel 			VARCHAR(13),						--연락처(휴대폰)
	belong			VARCHAR(100),						--소속
	etc				VARCHAR(255),						--신청 사유
	reg_dt			DATETIME		DEFAULT SYSDATE,	--등록일
	upd_dt			DATETIME,							--수정일
	
	FOREIGN KEY (username) REFERENCES member_account (username)
);

INSERT INTO member_add_info (username, fullname) VALUES ('admin', 'admin');