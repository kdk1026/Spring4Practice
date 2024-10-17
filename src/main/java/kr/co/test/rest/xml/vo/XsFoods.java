package kr.co.test.rest.xml.vo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("XsFoods")
public class XsFoods {
	
	@XStreamImplicit(itemFieldName = "food")
	private List<XsFood> foodList;

	public void setFoodList(List<XsFood> foodList) {
		this.foodList = foodList;
	}
	
}
