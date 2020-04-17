package com.wejoyclass.itops.cloud.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apple {

    private int id;
    private String name;
    private String color;
    private double weight;

    public Apple(){}
    public Apple(int id,String name,String color,double weight){
        this.id = id;
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    public static boolean isGreenApple(Apple apple) {     return "green".equals(apple.getColor()); }
    public static boolean isHeavyApple(Apple apple) {     return apple.getWeight() > 150; }

    @Override
    public String toString() {
        return "Apple[id="+ id +",name=" + name + ",color=" + color + ",weight=" + weight + "]";
    }
}
