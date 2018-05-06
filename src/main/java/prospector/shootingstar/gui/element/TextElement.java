package prospector.shootingstar.gui.element;

import prospector.mechconstruct.mod.MechConstruct;
import prospector.mechconstruct.gui.MechGui;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TextElement extends Element {
	protected String text;
	protected int color;
	protected boolean centered = false;
	protected int clipTo = -1;
	protected boolean translate = false;

	public TextElement(String text, int color, int x, int y) {
		super(x, y);
		this.text = text;
		this.color = color;
	}

	public TextElement(String text, int color, int x, int y, int clipTo) {
		this(text, color, x, y);
		this.clipTo = clipTo;
	}

	public TextElement(String text, int color, int x, int y, boolean centered) {
		this(text, color, x, y);
		this.centered = centered;
	}

	public TextElement(String text, int color, int y, boolean centered) {
		this(text, color, -1, y);
		this.centered = centered;
	}

	public TextElement(String text, int color, int x, int y, boolean centered, int clipTo) {
		this(text, color, x, y, centered);
		this.clipTo = clipTo;
	}

	public TextElement(String text, int color, int y, boolean centered, int clipTo) {
		this(text, color, y, centered);
		this.clipTo = clipTo;
	}

	public TextElement(String text, boolean translate, int color, int x, int y) {
		super(x, y);
		this.translate = translate;
		this.text = text;
		this.color = color;
	}

	public TextElement(String text, boolean translate, int color, int x, int y, int clipTo) {
		this(text, translate, color, x, y);
		this.clipTo = clipTo;
	}

	public TextElement(String text, boolean translate, int color, int x, int y, boolean centered) {
		this(text, translate, color, x, y);
		this.centered = centered;
	}

	public TextElement(String text, boolean translate, int color, int y, boolean centered) {
		this(text, translate, color, -1, y);
		this.centered = centered;
	}

	public TextElement(String text, boolean translate, int color, int x, int y, boolean centered, int clipTo) {
		this(text, translate, color, x, y, centered);
		this.clipTo = clipTo;
	}

	public TextElement(String text, boolean translate, int color, int y, boolean centered, int clipTo) {
		this(text, translate, color, y, centered);
		this.clipTo = clipTo;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void draw(MechGui gui) {
		String string = translate ? I18n.format(text) : text;
		if (clipTo > -1 && gui.mc.fontRenderer.getStringWidth(string) > clipTo) {
			string = gui.mc.fontRenderer.trimStringToWidth(string, clipTo - gui.mc.fontRenderer.getStringWidth("..."));
			string = string + "...";
		}
		if (centered) {
			if (x > -1) {
				MechConstruct.proxy.getGuiAssembler().drawCenteredString(gui, string, x, y, color);
			} else {
				MechConstruct.proxy.getGuiAssembler().drawCenteredString(gui, string, y, color);
			}
		} else if (x > -1) {
			MechConstruct.proxy.getGuiAssembler().drawString(gui, string, x, y, color);
		}

	}
}