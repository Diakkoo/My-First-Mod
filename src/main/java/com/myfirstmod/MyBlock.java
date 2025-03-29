package com.myfirstmod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MyBlock extends Block {
    //我想让方块可以在充能后释放闪电
    //先创建一个布尔值CHARGED，标记方块的充能状态
    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

    //构造函数
    public MyBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(CHARGED, false));
    }

    //覆盖appendProperties并加入布尔值CHARGED以注册属性
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }

    //通过onUse方法使方块充能
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 1);    //播放充能音效
        world.setBlockState(pos, state.with(CHARGED, true));                       //将方块的状态设置为充能
        return ActionResult.SUCCESS;                                                     //返回操作成功
    }


    //召唤闪电
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (world.getBlockState(pos).get(CHARGED)){
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);                      //创造闪电实体
            if (lightningEntity != null) {                                                                  //先判断闪电实体有没有正确生成
                lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));                    //设置闪电位置（以方块底部为中心）
            }
            world.spawnEntity(lightningEntity);                                                             //将闪电生成到世界中
        }

        world.setBlockState(pos, state.with(CHARGED, false));
        super.onSteppedOn(world, pos, state, entity);
    }

    //创建方块实例
    //Minecraft 1.21.1
    public static final Block MY_BLOCK = new MyBlock(AbstractBlock.Settings.create().strength(1.0f).sounds(BlockSoundGroup.ROOTED_DIRT));
}
