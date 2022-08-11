package com.xxx.designPattern.builder;

/**
 * 建造者(Builder)模式
 * @See https://www.yiibai.com/design_pattern/builder_pattern.html
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meals vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal : ");
        vegMeal.showItems();
        System.out.println("Total Cost : " + vegMeal.getCost());

        Meals nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal:");
        nonVegMeal.showItems();
        System.out.println("Total Cost : " + nonVegMeal.getCost());
    }
}
