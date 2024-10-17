package kr.co.test.rest.xml.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "JaxFood")
public class JaxFood {

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
