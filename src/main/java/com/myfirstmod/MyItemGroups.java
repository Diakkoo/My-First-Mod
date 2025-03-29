package com.myfirstmod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;


public final class MyItemGroups {
    public static final ItemGroup MyGroup = FabricItemGroup.builder()
            .icon(() -> new ItemStack(MyItem.MY_ITEM))
            .displayName(Text.translatable("mygroup"))
            .entries((context, entries) -> {
                entries.add(MyItem.MY_ITEM);        //将物品添加进物品组
                entries.add(MyFood.MY_FOOD);        //将食物注册进物品组
                entries.add(MyBlock.MY_BLOCK);      //将方块注册进物品组
            })
            .build();
}
