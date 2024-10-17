--회원 계정
CREATE TABLE member_account (
	seq 			INT 			AUTO_INCREMENT,		--일련번호
	username 		VARCHAR(30) 	NOT NULL UNIQUE,	--아이디
	password		VARCHAR(100) 	NOT NULL,			--비밀번호
	enabled			TINYINT 		DEFAULT 0,			--계정 상태(0:차단, 1:정상)
	reg_dt			DATETIME		DEFAULT SYSDATE,	--등록일
	reg_ip			VARCHAR(30),						--등록 IP
	last_login_dt	DATETIME,							--최종 로그인 시간
	last_pwd_upd_dt	DATETIME,							--최종 비밀번호 변경 시간
	last_ebd_upd_dt	DATETIME,							--최종 계정 상태 수정일
	
	PRIMARY KEY (seq)
);

INSERT INTO member_account (username, password, enabled) VALUES ('admin', '$2a$10$AaEI68z6AZKpMTPHGhyq6OLlADhUUKjE9ebfuX1aPxXWoMF42lKkq', 1);