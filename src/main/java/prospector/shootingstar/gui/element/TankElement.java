package prospector.shootingstar.gui.element;

import prospector.mechconstruct.mod.MechConstruct;
import prospector.mechconstruct.gui.MechGui;
import prospector.mechconstruct.gui.blueprint.Sprite;
import prospector.mechconstruct.util.Tank;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TankElement extends Element {
	public static int xOverlayOffset = 3;
	public static int yOverlayOffset = 3;
	public Tank tank;
	String tankId;

	public TankElement(int x, int y, String tankId) {
		super(x, y, Sprite.TANK_BACKGROUND);
		this.tankId = tankId;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawTooltip(MechGui gui, int mouseX, int mouseY) {
		super.drawTooltip(gui, mouseX, mouseY);
		if (tank.getFluidAmount() > 0) {
			MechConstruct.proxy.getGuiAssembler().drawPercentTooltip(gui, mouseX, mouseY, tank.getFluidAmount(), tank.getCapacity(), I18n.format("gui.mechconstruct.milli_buckets_unit") + " " + tank.getFluid().getLocalizedName(), I18n.format("gui.mechconstruct.full"));
		} else {
			MechConstruct.proxy.getGuiAssembler().drawSimpleTooltip(gui, mouseX, mouseY, TextFormatting.GOLD + I18n.format("gui.mechconstruct.empty_tank"));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void draw(MechGui gui) {
		super.draw(gui);
		this.tank = gui.provider.getFluidInventory().getTank(tankId);
		if (tank.getFluidAmount() > 0 && tank.getFluid() != null) {
			MechConstruct.proxy.getGuiAssembler().drawFluid(gui, tank.getFluid(), x + xOverlayOffset + 1, y + yOverlayOffset + 1, 14, Sprite.TANK_OVERLAY.height - 2, tank.getCapacity());
		}
		MechConstruct.proxy.getGuiAssembler().drawSprite(gui, Sprite.TANK_OVERLAY, x + xOverlayOffset, y + yOverlayOffset);
	}
}
