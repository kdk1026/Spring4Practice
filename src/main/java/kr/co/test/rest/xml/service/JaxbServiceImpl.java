package kr.co.test.rest.xml.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import common.LogDeclare;
import common.spring.resolver.ParamCollector;
import kr.co.test.rest.xml.vo.JaxFood;
import kr.co.test.rest.xml.vo.JaxFoods;

@Service
public class JaxbServiceImpl extends LogDeclare implements JaxbService {

	@Override
	public JaxFood food(ParamCollector paramCollector) {
		JaxFood food = new JaxFood();
		food.setName("Belgian Waffles");
		food.setPrice("$5.95");
		return food;
	}

	@Override
	public JaxFoods foods(ParamCollector paramCollector) {
		List<JaxFood> foodList = new ArrayList<JaxFood>();
		JaxFood food = null;
		
		food = new JaxFood();
		food.setName("Belgian Waffles");
		food.setPrice("$5.95");
		foodList.add(food);
		
		food = new JaxFood();
		food.setName("Strawberry Belgian Waffles");
		food.setPrice("$7.95");
		foodList.add(food);
		
		JaxFoods foods = new JaxFoods();
		foods.setFoodList(foodList);
		
		return foods;
	}
	
}
