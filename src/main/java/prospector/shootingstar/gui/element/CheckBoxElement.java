package prospector.shootingstar.gui.element;

import prospector.mechconstruct.mod.MechConstruct;
import prospector.mechconstruct.gui.MechGui;
import prospector.mechconstruct.gui.blueprint.IBlueprintProvider;
import prospector.mechconstruct.gui.blueprint.Sprite;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxElement extends Element {
	public String label;
	public int labelColor;
	public boolean isTicked;
	public List<Action> tickedActions = new ArrayList<>();
	public List<Action> untickedActions = new ArrayList<>();
	private Sprite.CheckBox checkBoxSprite;

	public CheckBoxElement(String label, int labelColor, int x, int y, boolean isTicked, Sprite.CheckBox checkBoxSprite) {
		super(x, y, checkBoxSprite.getNormal());
		this.checkBoxSprite = checkBoxSprite;
		this.isTicked = isTicked;
		this.label = label;
		this.labelColor = labelColor;
		if (isTicked) {
			container.setSprite(0, checkBoxSprite.getTicked());
		} else {
			container.setSprite(0, checkBoxSprite.getNormal());
		}
	}

	public CheckBoxElement(int x, int y, boolean isTicked, Sprite.CheckBox checkBoxSprite) {
		this("", 0x0, x, y, isTicked, checkBoxSprite);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void draw(MechGui gui) {
		super.draw(gui);
		MechConstruct.proxy.getGuiAssembler().drawString(gui, label, x + checkBoxSprite.getNormal().width + 5, ((y + getHeight(gui.provider) / 2) - (gui.mc.fontRenderer.FONT_HEIGHT / 2)), labelColor);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void clientCalls() {
		super.clientCalls();
		this.addPressAction((element, gui, provider, mouseX, mouseY) -> {
			this.isTicked = !this.isTicked;
			if (this.isTicked) {
				element.container.setSprite(0, checkBoxSprite.getTicked());
				for (Action action : tickedActions) {
					action.execute(element, gui, provider, mouseX, mouseY);
				}
			} else {
				element.container.setSprite(0, checkBoxSprite.getNormal());
				for (Action action : untickedActions) {
					action.execute(element, gui, provider, mouseX, mouseY);
				}
			}
		});
	}

	public boolean isTicked() {
		return isTicked;
	}

	public void setTicked(boolean ticked) {
		isTicked = ticked;
		if (isTicked) {
			container.setSprite(0, checkBoxSprite.getTicked());
		} else {
			container.setSprite(0, checkBoxSprite.getNormal());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getWidth(IBlueprintProvider provider) {
		return checkBoxSprite.getNormal().width + Minecraft.getMinecraft().fontRenderer.getStringWidth(label) + 5;
	}

	public Element addTickedAction(Action action) {
		tickedActions.add(action);
		return this;
	}

	public Element addUntickedAction(Action action) {
		untickedActions.add(action);
		return this;
	}
}
