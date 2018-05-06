package prospector.shootingstar.gui.element;

import prospector.mechconstruct.gui.MechGui;
import prospector.mechconstruct.gui.blueprint.Sprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FakeSlot extends Element {
	public FakeSlot(int x, int y) {
		super(x, y, Sprite.FAKE_SLOT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void draw(MechGui gui) {
		super.draw(gui);
	}
}