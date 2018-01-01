package prospector.shootingstar.base.item;

import net.minecraft.item.Item;
import prospector.shootingstar.ShootingStar;
import prospector.shootingstar.model.ModelCompound;

public class StarItem extends Item {
	protected String modId;
	protected String name;

	public StarItem(String modId, String name) {
		this.modId = modId;
		this.name = name;
		this.setRegistryName(modId, name);
		this.setUnlocalizedName(modId + "." + name);
		ShootingStar.registerModel(new ModelCompound(modId, this));
	}

	public String getModId() {
		return modId;
	}

	public String getName() {
		return name;
	}
}
