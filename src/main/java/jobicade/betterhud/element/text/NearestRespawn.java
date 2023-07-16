package jobicade.betterhud.element.text;

import java.util.Arrays;
import java.util.List;

import jobicade.betterhud.BetterHud;
import jobicade.betterhud.element.settings.Setting;
import jobicade.betterhud.element.settings.SettingBoolean;
import jobicade.betterhud.geom.Direction;
import net.minecraft.client.Minecraft;

public class NearestRespawn extends TextElement {

    @Override
    public void loadDefaults() {
        super.loadDefaults();

        position.setPreset(Direction.NORTH_WEST);
    }

    public NearestRespawn() {
        super("nearestRespawn");
    }

    @Override
    protected void addSettings(List<Setting<?>> settings) {
        super.addSettings(settings);
    }

    @Override
    protected List<String> getText() {
        return Arrays.asList("Respawn: " + BetterHud.renderRespawnLocation);
    }
}