package kr.co.test.rest.xml.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import kr.co.test.rest.xml.vo.XsFood;
import kr.co.test.rest.xml.vo.XsFoods;

@Service
public class XstreamServiceImpl extends LogDeclare implements XstreamService {

	@Override
	public XsFood food(ParamCollector paramCollector) {
		XsFood food = new XsFood();
		food.setName("Belgian Waffles");
		food.setPrice("$5.95");
		return food;
	}

	@Override
	public XsFoods foods(ParamCollector paramCollector) {
		List<XsFood> foodList = new ArrayList<XsFood>();
		XsFood food = null;
		
		food = new XsFood();
		food.setName("Belgian Waffles");
		food.setPrice("$5.95");
		foodList.add(food);
		
		food = new XsFood();
		food.setName("Strawberry Belgian Waffles");
		food.setPrice("$7.95");
		foodList.add(food);
		
		XsFoods foods = new XsFoods();
		foods.setFoodList(foodList);
		
		return foods;
	}
	
}
