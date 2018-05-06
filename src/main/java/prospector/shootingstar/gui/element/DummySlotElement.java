package prospector.shootingstar.gui.element;

import prospector.mechconstruct.gui.SlotType;

public class DummySlotElement extends Element {
	SlotType type;

	public DummySlotElement(SlotType type, int x, int y) {
		super(x, y, type.getSprite());
		this.type = type;
	}

	public SlotType getType() {
		return type;
	}
}