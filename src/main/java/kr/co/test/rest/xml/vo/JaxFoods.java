package kr.co.test.rest.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "JaxFoods")
public class JaxFoods {

	@XmlElement(name = "food")
	private List<JaxFood> foodList;

	public void setFoodList(List<JaxFood> foodList) {
		this.foodList = foodList;
	}
	
}
