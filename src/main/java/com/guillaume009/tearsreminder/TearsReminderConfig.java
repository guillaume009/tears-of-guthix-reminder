package com.guillaume009.tearsreminder;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(TearsReminderConfig.GROUP)
public interface TearsReminderConfig extends Config
{
    String GROUP = "tearsreminder";

    @ConfigItem(
        keyName = "needsReminder",
        name = "[DEBUG] Needs reminder",
        description = "Default value for whether the tears reminder should be shown on login",
        position = 1
    )
    default boolean needsReminder()
    {
        return false;
    }
}
