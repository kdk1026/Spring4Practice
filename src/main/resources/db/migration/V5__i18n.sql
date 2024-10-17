--Locale Language
CREATE TABLE i18n_lang (
	lang 		VARCHAR(2),					--Loacle Language
    name 		VARCHAR(50) 	NOT NULL,	--Locale명
    
    PRIMARY KEY (lang)		
);

INSERT INTO i18n_lang (lang, name) VALUES ('ko', '한국어');
INSERT INTO i18n_lang (lang, name) VALUES ('en', '영어');
INSERT INTO i18n_lang (lang, name) VALUES ('ja', '일본어');

--Locale 메시지
CREATE TABLE i18n_msg (
	code		VARCHAR(100),						--메시지 코드
	lang		VARCHAR(2) 		NOT NULL,			--Loacle Language
	desc		VARCHAR(100),
	msg			VARCHAR(2000)	NOT NULL,			--메시지
	reg_dt		DATETIME		DEFAULT SYSDATE,	--등록일
	upd_dt		DATETIME,							--수정일
	
	PRIMARY KEY (code, lang),
	FOREIGN KEY (lang) REFERENCES i18n_lang (lang)
);

INSERT INTO i18n_msg (code, lang, desc, msg) VALUES ('S0000', 'ko', '정상', '정상 처리되었습니다.');
INSERT INTO i18n_msg (code, lang, desc, msg) VALUES ('S0000', 'en', 'Success', 'Successfully processed.');
INSERT INTO i18n_msg (code, lang, desc, msg) VALUES ('S0000', 'ja', '通常の', '通常の処理された。');
INSERT INTO i18n_msg (code, lang, desc, msg) VALUES ('9999', 'ko', 'Required value', '{0} 를(을) 입력하여 주세요.');
INSERT INTO i18n_msg (code, lang, desc, msg) VALUES ('9999', 'en', 'Success', 'Please enter {0}.');
INSERT INTO i18n_msg (code, lang, desc, msg) VALUES ('9999', 'ja', '必要な値', '{0}を（を）入力してください。');