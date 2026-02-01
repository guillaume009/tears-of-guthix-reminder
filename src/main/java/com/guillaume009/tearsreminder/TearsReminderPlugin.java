package com.guillaume009.tearsreminder;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.ChatMessageType;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.config.ConfigManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
@PluginDescriptor(name = "Tears of Guthix reminder")
public class TearsReminderPlugin extends Plugin
{
    private static final Logger log = LoggerFactory.getLogger(TearsReminderPlugin.class);
    private static final String TEARS_MESSAGE = "You are eligible to drink from the Tears of Guthix";
    private static final String CAVE_MESSAGE = "Your stories have entertained me. I will let you into the cave for a short time.";

    @Inject
    private Client client;

    @Inject
    private InfoBoxManager infoBoxManager;

    @Inject
    private ConfigManager configManager;

    @Inject private TearsReminderConfig config;

    @Inject
    private ItemManager itemManager;

    private TearsReminderInfobox currentBox;
    @Override
    protected void startUp() {
        boolean needsReminder = config.needsReminder();
        log.info("TearsReminderPlugin started. Using config={}", needsReminder);

        if (needsReminder)
        {
            addReminderBox();
        }
    }

    @Override
    protected void shutDown() {
        removeReminderBox();
    }

    @Subscribe
    public void onChatMessage(ChatMessage event)
    {
        if (event.getType() != ChatMessageType.GAMEMESSAGE && event.getType() != ChatMessageType.DIALOG)
        {
            return;
        }

        String msg = event.getMessage();
        if (msg == null)
        {
            return;
        }
        log.info("Detected message={}",msg);
        if (msg.contains(CAVE_MESSAGE))
        {
            if (configManager != null && config != null)
            {
                configManager.setConfiguration(TearsReminderConfig.GROUP, "needsReminder", false);
            }
            removeReminderBox();
            log.info("Detected cave message; cleared Tears reminder state.");
            return;
        }

        if (msg.contains(TEARS_MESSAGE))
        {
            if (configManager != null && config != null)
            {
                configManager.setConfiguration(TearsReminderConfig.GROUP, "needsReminder", true);
            }
            addReminderBox();
            log.info("Detected Tears of Guthix eligibility message; reminder stored and shown.");
        }
    }

    private void addReminderBox()
    {
        if (currentBox != null)
        {
            return;
        }
        BufferedImage img = itemManager.getImage(ItemID.TOG_BOWL);
        currentBox = new TearsReminderInfobox(img, this);
        infoBoxManager.addInfoBox(currentBox);
    }

    private void removeReminderBox()
    {
        if (currentBox != null)
        {
            // clear persisted state
            if (configManager != null && config != null)
            {
                configManager.setConfiguration(TearsReminderConfig.GROUP, "needsReminder", false);
            }
            infoBoxManager.removeInfoBox(currentBox);
            currentBox = null;
        }
    }

    @Provides
    TearsReminderConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(TearsReminderConfig.class);
    }
}
