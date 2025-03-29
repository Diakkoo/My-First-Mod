package com.myfirstmod;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class MyItem extends Item{

    public MyItem(Settings settings) {
        super(settings);
    }


    //为物品添加了当右击时播放羊毛被破坏的声音
    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    //为物品添加提示
    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type){
        tooltip.add(Text.translatable("item.my-first-mod.my-item.tooltip"));
    }

    //新物品的实例
    //Minecraft 1.21.1
    public static final MyItem MY_ITEM = new MyItem(new Item.Settings().maxCount(16).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true)));
}                                                    //.maxCount使物品的最大堆叠数为16  .component创建物品实例时使得该物品不可被破坏
