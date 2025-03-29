package com.myfirstmod;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;

public class MyFood extends Item {
    //确保类继承于Item而非Items

    public static final FoodComponent MY_FOOD_COMPONENT = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(1.2F)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
            .build();


    public MyFood(Settings settings){
        super(settings.food(MY_FOOD_COMPONENT));
    }


    //Minecraft 1.21.1
    //创建食物实例
    public static final MyFood MY_FOOD = new MyFood(new Item.Settings());
    //确保实例的类是MY_FOOD而不是继承的Item类，否则食物无法正确实例化，无法食用
}
