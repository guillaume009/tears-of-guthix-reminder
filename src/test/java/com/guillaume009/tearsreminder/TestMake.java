package com.guillaume009.tearsreminder;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

import java.nio.file.Files;
import java.nio.file.Path;

public class TestMake
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(TearsReminderPlugin.class);
        RuneLite.main(args);
    }
}