package prospector.shootingstar.base.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class StarFluid extends Fluid {
	protected String modId;
	protected String name;

	public StarFluid(String modId, String name, int density, int viscosity, int temperature, int luminosity) {
		super(name, new ResourceLocation(modId, "fluid/" + name + "/" + name + "_still"), new ResourceLocation(modId, "fluid/" + name + "/" + name + "_flowing"));
		this.modId = modId;
		this.name = name;
		setDensity(density);
		setViscosity(viscosity);
		setTemperature(temperature);
		setGaseous(getDensity() < 0);
	}

	public StarFluid(String modId, String name, int density, int viscosity, int temperature) {
		this(modId, name, density, viscosity, temperature, 0);
	}

	public StarFluid(String modId, String name) {
		this(modId, name, 1000, 1000, 300);
	}

	@Override
	public String getUnlocalizedName() {
		return "fluid." + modId + "." + name;
	}
}
