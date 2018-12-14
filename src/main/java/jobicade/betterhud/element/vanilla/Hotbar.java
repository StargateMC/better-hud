package jobicade.betterhud.element.vanilla;

import static jobicade.betterhud.BetterHud.MC;
import static jobicade.betterhud.BetterHud.WIDGETS;

import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.Event;
import jobicade.betterhud.element.settings.DirectionOptions;
import jobicade.betterhud.element.settings.SettingPosition;
import jobicade.betterhud.geom.Rect;
import jobicade.betterhud.geom.Direction;
import jobicade.betterhud.render.GlMode;
import jobicade.betterhud.render.TextureMode;
import jobicade.betterhud.util.GlUtil;

public class Hotbar extends OverrideElement {
	public Hotbar() {
		super("hotbar", new SettingPosition(DirectionOptions.TOP_BOTTOM, DirectionOptions.NONE));
		position.setEdge(true).setPostSpacer(2);
	}

	@Override
	public void loadDefaults() {
		super.loadDefaults();
		position.setPreset(Direction.SOUTH);
	}

	@Override
	protected ElementType getType() {
		return ElementType.HOTBAR;
	}

	@Override
	public boolean shouldRender(Event event) {
		return !GuiIngameForge.renderHotbar && super.shouldRender(event);
	}

	@Override
	protected Rect render(Event event) {
		GlMode.push(new TextureMode(WIDGETS));
		Rect barTexture = new Rect(182, 22);
		Rect bounds = position.applyTo(new Rect(barTexture));

		GlUtil.drawTexturedModalRect(bounds.getPosition(), barTexture);

		Rect slot = bounds.grow(-3).withWidth(16);

		float partialTicks = getPartialTicks(event);
		for(int i = 0; i < 9; i++, slot = slot.translate(Direction.EAST.scale(20))) {
			GlUtil.renderHotbarItem(slot, MC.player.inventory.mainInventory.get(i), partialTicks);

			if(i == MC.player.inventory.currentItem) {
				MC.getTextureManager().bindTexture(WIDGETS);
				GlUtil.drawTexturedModalRect(slot.grow(4), new Rect(0, 22, 24, 24));
			}
		}

		GlMode.pop();
		return bounds;
	}
}
