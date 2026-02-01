package com.guillaume009.tearsreminder;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.config.ConfigManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Instant;

public class TearsReminderInfobox extends InfoBox
{
    //private final TearsStateStore store;
    //private final InfoBoxManager infoBoxManager;
    //private final ConfigManager configManager;
    //private Instant created = Instant.now();

    public TearsReminderInfobox(BufferedImage img, TearsReminderPlugin plugin)
    {
        super(img, plugin);
        setTooltip("Time to drink the tears of Guthix");
    }

    @Override
    public String getText() {
        return "ToG";
    }

    @Override
    public Color getTextColor() {
        return Color.GREEN;
    }

    @Override
    public boolean render()
    {
        return true;
    }
}
