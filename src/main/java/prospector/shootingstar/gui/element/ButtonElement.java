package prospector.shootingstar.gui.element;

import prospector.mechconstruct.gui.blueprint.Sprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ButtonElement extends Element {
	private Sprite.Button buttonSprite;

	public ButtonElement(int x, int y, Sprite.Button buttonSprite) {
		super(x, y, buttonSprite.getNormal());
		this.buttonSprite = buttonSprite;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientCalls() {
		super.clientCalls();
		this.addUpdateAction((gui, element) -> {
			if (isHovering) {
				element.container.setSprite(0, buttonSprite.getHovered());
			} else {
				element.container.setSprite(0, buttonSprite.getNormal());
			}
		});
	}
}
