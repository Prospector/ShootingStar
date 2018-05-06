package prospector.shootingstar.base.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import prospector.shootingstar.base.blockentity.StarBlockEntity;

import javax.annotation.Nullable;

public class StarBlockWithEntity extends StarBlock {
	protected Class<? extends StarBlockEntity> blockEntityClass;

	public StarBlockWithEntity(String modId, String name, Material materialIn, Class<? extends StarBlockEntity> blockEntityClass) {
		super(modId, name, materialIn);
		this.blockEntityClass = blockEntityClass;
	}

	public StarBlockWithEntity(String modId, String name, Class<? extends StarBlockEntity> blockEntityClass) {
		this(modId, name, Material.ROCK, blockEntityClass);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		try {
			StarBlockEntity blockEntity = blockEntityClass.newInstance();
			blockEntity.setBlock(this);
			return blockEntity;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Class<? extends StarBlockEntity> getBlockEntityClass() {
		return blockEntityClass;
	}
}
