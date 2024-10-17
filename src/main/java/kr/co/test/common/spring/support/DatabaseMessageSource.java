package kr.co.test.common.spring.support;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.AbstractMessageSource;

public class DatabaseMessageSource extends AbstractMessageSource {

	private Messages messages;
	
	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		MessageFormat mf = null;
		
		String lang = locale.getLanguage();
		String msg = messages.getMessage(code, lang);
		
		if (msg != null) {
			mf = new MessageFormat(msg, locale);
		}
		
		return mf;
	}
	
}
