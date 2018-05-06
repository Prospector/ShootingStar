package prospector.shootingstar.base.fluid;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.model.ModelCompound;

public class StarBlockFluid extends BlockFluidClassic {
	protected String modId;
	protected String name;

	public StarBlockFluid(StarFluid fluid, Material material) {
		super(fluid, material);
		this.modId = fluid.modId;
		this.name = fluid.name;
		setRegistryName(modId, "fluid." + name);
		ShootingStar.registerModel(new ModelCompound(modId, this, "fluid").setFileName("fluids").setVariant(name));
	}
}
