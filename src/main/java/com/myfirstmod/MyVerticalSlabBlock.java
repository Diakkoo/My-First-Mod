package com.myfirstmod;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class MyVerticalSlabBlock extends HorizontalFacingBlock {
    //创建一个垂直版的安山岩台阶

    public static final MapCodec<MyVerticalSlabBlock> CODEC = Block.createCodec(MyVerticalSlabBlock::new);

    //构造函数
    public MyVerticalSlabBlock(Block.Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    //实现HorizontalFAcingBlock的抽象方法getCodec()
    @Override
    protected MapCodec<? extends MyVerticalSlabBlock> getCodec(){
        return CODEC;
    }

    //重写HorizontalFacingBlock的appendProperties方法
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx){
        Direction dir = state.get(FACING);
        return switch(dir) {
            case NORTH -> VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
            case SOUTH -> VoxelShapes.cuboid(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
            case EAST -> VoxelShapes.cuboid(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            case WEST -> VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
            default -> VoxelShapes.fullCube();
        };
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }


    //创建实例
    //Minecraft 1.21.1
    public static final MyVerticalSlabBlock MY_VERTICAL_SLAB_BLOCK = new MyVerticalSlabBlock(Block.Settings.copy(Blocks.POLISHED_ANDESITE));
}
