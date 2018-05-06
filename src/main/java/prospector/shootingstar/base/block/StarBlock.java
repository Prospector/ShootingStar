package prospector.shootingstar.base.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.model.ModelCompound;

public class StarBlock extends Block {
	protected String modId;
	protected String name;

	public StarBlock(String modId, String name, Material materialIn) {
		super(materialIn);
		this.modId = modId;
		this.name = name;
		setRegistryName(modId, name);
		registerModel();
	}

	public StarBlock(String modId, String name) {
		this(modId, name, Material.ROCK);
	}

	@Override
	public String getUnlocalizedName() {
		return "block." + modId + "." + name;
	}

	public String getModId() {
		return modId;
	}

	public String getName() {
		return name;
	}

	public void registerModel() {
		ShootingStar.registerModel(new ModelCompound(modId, this, "block").setInvVariant("normal"));
	}
}
