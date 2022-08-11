package com.xxx.designPattern.builder;

/**
 * 用餐订单构造器
 */
public class MealBuilder {
    public Meals prepareVegMeal() {
        Meals meals = new Meals();
        meals.addItem(new VegBurger());
        meals.addItem(new Coke());
        return meals;
    }

    public Meals prepareNonVegMeal() {
        Meals meals = new Meals();
        meals.addItem(new ChickenBurger());
        meals.addItem(new Pepsi());
        return meals;
    }
}
