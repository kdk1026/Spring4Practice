--회원 권한
CREATE TABLE member_roles (
	seq 			INT 			AUTO_INCREMENT,	--일련번호
	username 		VARCHAR(30) 	NOT NULL,		--아이디
	role			VARCHAR(50),
	
	PRIMARY KEY (seq),
	FOREIGN KEY (username) REFERENCES member_account (username)
);

INSERT INTO member_roles (username, role) VALUES ('admin', 'ROLE_ADMIN');