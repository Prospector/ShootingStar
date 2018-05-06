package prospector.shootingstar.gui.element;

import prospector.mechconstruct.mod.MechConstruct;
import prospector.mechconstruct.gui.MechGui;
import prospector.mechconstruct.gui.blueprint.IBlueprintProvider;
import prospector.mechconstruct.gui.blueprint.ISprite;
import prospector.mechconstruct.gui.blueprint.OffsetSprite;
import prospector.mechconstruct.gui.blueprint.SpriteContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class Element {

	public int x;
	public int y;
	public boolean isHovering = false;
	public boolean isDragging = false;
	public boolean isPressing = false;
	public boolean isReleasing = false;
	public boolean startPressLast = false;
	public boolean isHoveringLast = false;
	public boolean isDraggingLast = false;
	public boolean isPressingLast = false;
	public boolean isReleasingLast = false;
	public List<Element.Action> hoverActions = new ArrayList<>();
	public List<Element.Action> dragActions = new ArrayList<>();
	public List<Element.Action> startPressActions = new ArrayList<>();
	public List<Element.Action> pressActions = new ArrayList<>();
	public List<Element.Action> releaseActions = new ArrayList<>();
	public SpriteContainer container;
	public List<UpdateAction> updateActions = new ArrayList<>();
	public List<UpdateAction> buttonUpdate = new ArrayList<>();
	private int width;
	private int height;

	public Element(int x, int y, SpriteContainer container) {
		this.container = container;
		this.x = x;
		this.y = y;
	}

	public Element(int x, int y, ISprite... sprites) {
		this.container = new SpriteContainer();
		for (ISprite sprite : sprites) {
			container.addSprite(sprite);
		}
		this.x = x;
		this.y = y;
	}

	public Element(int x, int y, int width, int height) {
		this.container = new SpriteContainer();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Element(int x, int y, int width, int height, SpriteContainer container) {
		this.container = container;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Element(int x, int y, int width, int height, ISprite... sprites) {
		this.container = new SpriteContainer();
		for (ISprite sprite : sprites) {
			container.addSprite(sprite);
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@SideOnly(Side.CLIENT)
	public void clientCalls() {

	}

	public SpriteContainer getSpriteContainer() {
		return container;
	}

	@SideOnly(Side.CLIENT)
	public void adjustDimensions(IBlueprintProvider provider) {
		if (container.offsetSprites != null) {
			for (OffsetSprite offsetSprite : container.offsetSprites) {
				if (offsetSprite.getSprite().getSprite(provider).width + offsetSprite.getOffsetX(provider) > this.width) {
					this.width = offsetSprite.getSprite().getSprite(provider).width + offsetSprite.getOffsetX(provider);
				}
				if (offsetSprite.getSprite().getSprite(provider).height + offsetSprite.getOffsetY(provider) > this.height) {
					this.height = offsetSprite.getSprite().getSprite(provider).height + offsetSprite.getOffsetY(provider);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void draw(MechGui gui) {
		for (OffsetSprite sprite : getSpriteContainer().offsetSprites) {
			MechConstruct.proxy.getGuiAssembler().drawSprite(gui, sprite.getSprite(), x + sprite.getOffsetX(gui.provider), y + sprite.getOffsetY(gui.provider));
		}
	}

	@SideOnly(Side.CLIENT)
	public void drawTooltip(MechGui gui, int mouseX, int mouseY) {

	}

	@SideOnly(Side.CLIENT)
	public void renderUpdate(MechGui gui) {
		isHoveringLast = isHovering;
		isPressingLast = isPressing;
		isDraggingLast = isDragging;
		isReleasingLast = isReleasing;
	}

	@SideOnly(Side.CLIENT)
	public void update(MechGui gui) {
		for (UpdateAction action : updateActions) {
			action.update(gui, this);
		}
	}

	@SideOnly(Side.CLIENT)
	public Element addUpdateAction(UpdateAction action) {
		updateActions.add(action);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public Element setWidth(int width) {
		this.width = width;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public Element setHeight(int height) {
		this.height = height;
		return this;
	}

	public int getX() {
		return x;
	}

	public Element setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public Element setY(int y) {
		this.y = y;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public int getWidth(IBlueprintProvider provider) {
		adjustDimensions(provider);
		return width;
	}

	@SideOnly(Side.CLIENT)
	public int getHeight(IBlueprintProvider provider) {
		adjustDimensions(provider);
		return height;
	}

	@SideOnly(Side.CLIENT)
	public Element addHoverAction(Element.Action action) {
		this.hoverActions.add(action);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public Element addDragAction(Element.Action action) {
		this.dragActions.add(action);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public Element addStartPressAction(Element.Action action) {
		this.startPressActions.add(action);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public Element addPressAction(Element.Action action) {
		this.pressActions.add(action);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public Element addReleaseAction(Element.Action action) {
		this.releaseActions.add(action);
		return this;
	}

	@SideOnly(Side.CLIENT)
	public void onHover(IBlueprintProvider provider, MechGui gui, int mouseX, int mouseY) {
		for (Element.Action action : hoverActions) {
			action.execute(this, gui, provider, mouseX, mouseY);
		}
	}

	@SideOnly(Side.CLIENT)
	public void onDrag(IBlueprintProvider provider, MechGui gui, int mouseX, int mouseY) {
		for (Element.Action action : dragActions) {
			action.execute(this, gui, provider, mouseX, mouseY);
		}
	}

	@SideOnly(Side.CLIENT)
	public void onStartPress(IBlueprintProvider provider, MechGui gui, int mouseX, int mouseY) {
		for (Element.Action action : startPressActions) {
			action.execute(this, gui, provider, mouseX, mouseY);
		}
	}

	@SideOnly(Side.CLIENT)
	public void onRelease(IBlueprintProvider provider, MechGui gui, int mouseX, int mouseY) {
		for (Element.Action action : releaseActions) {
			action.execute(this, gui, provider, mouseX, mouseY);
		}
		if (isPressing) {
			for (Element.Action action : pressActions) {
				action.execute(this, gui, provider, mouseX, mouseY);
			}
			if (!pressActions.isEmpty()) {
				Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public interface Action {
		void execute(Element element, MechGui gui, IBlueprintProvider provider, int mouseX, int mouseY);
	}

	@SideOnly(Side.CLIENT)
	public interface UpdateAction {
		void update(MechGui gui, Element element);
	}
}
