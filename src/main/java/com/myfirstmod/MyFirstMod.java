package com.myfirstmod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFirstMod implements ModInitializer {
	public static final String MOD_ID = "my-first-mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		//注册MyItem中定义的实例
		Registry.register(
				Registries.ITEM,
				Identifier.of("my-first-mod", "my-item"),  //用模组ID:My-First-Mod作为物品的命名空间
				MyItem.MY_ITEM        //引用MyItem中的物品实例
		);

//		FuelRegistry.INSTANCE.add(MyItem.MY_ITEM, 300);                 //将物品注册为燃料或者可堆肥

//		将物品注册进功能性物品栏，并且放在火把的左边。
//		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {content.addBefore(Items.TORCH, MyItem.MY_ITEM);});

		//注册物品组
		Registry.register(
				Registries.ITEM_GROUP,
				Identifier.of("my-first-mod", "my-group"),
				MyItemGroups.MyGroup
		);


		//注册食物
		Registry.register(
				Registries.ITEM,
				Identifier.of("my-first-mod", "my-food"),
				MyFood.MY_FOOD
		);

		//注册方块
		Registry.register(
				Registries.BLOCK,
				Identifier.of("my-first-mod", "my-block"),
				MyBlock.MY_BLOCK
		);
		//将方块注册进物品
		Registry.register(
				Registries.ITEM,
				Identifier.of("my-first-mod", "my-block"),
				new BlockItem(MyBlock.MY_BLOCK, new Item.Settings())
		);

		LOGGER.info("Hello Fabric world!");
	}
}