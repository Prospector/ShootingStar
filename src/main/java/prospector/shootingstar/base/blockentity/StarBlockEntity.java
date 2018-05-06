package prospector.shootingstar.base.blockentity;

import net.minecraft.tileentity.TileEntity;
import prospector.shootingstar.base.block.StarBlockWithEntity;

public class StarBlockEntity extends TileEntity {
	protected StarBlockWithEntity block;

	public StarBlockEntity() {
	}

	public StarBlockWithEntity getBlock() {
		return block;
	}

	public void setBlock(StarBlockWithEntity block) {
		this.block = block;
	}
}
