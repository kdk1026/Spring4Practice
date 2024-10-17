package kr.co.test.rest.xml.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("XsFood")
public class XsFood {

	private String name;
	private String price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
