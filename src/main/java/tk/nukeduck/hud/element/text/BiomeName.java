package tk.nukeduck.hud.element.text;

import static tk.nukeduck.hud.BetterHud.MC;

import java.util.Arrays;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import tk.nukeduck.hud.util.Direction;
import tk.nukeduck.hud.util.Direction.Options;

public class BiomeName extends TextElement {
	@Override
	public void loadDefaults() {
		super.loadDefaults();
		position.setPreset(Direction.NORTH);
		settings.priority.set(-1);
	}

	public BiomeName() {
		super("biome", Options.TOP_BOTTOM);
	}

	@Override
	protected List<String> getText() {
		BlockPos pos = new BlockPos((int)MC.player.posX, 0, (int)MC.player.posZ);

		return Arrays.asList(getLocalizedName() + ": " + MC.world.getBiomeForCoordsBody(pos).getBiomeName());
	}
}
