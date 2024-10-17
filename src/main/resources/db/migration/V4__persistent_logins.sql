--자동로그인
CREATE TABLE persistent_logins (
	username 		VARCHAR(64) 	NOT NULL,			--아이디
    series 			VARCHAR(64) 	NOT NULL,			--
    token 			VARCHAR(64) 	NOT NULL,			--자동로그인 토큰
    last_used 		DATETIME 		DEFAULT SYSDATE,	--
    PRIMARY KEY (series)		
);