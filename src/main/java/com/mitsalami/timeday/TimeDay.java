package com.mitsalami.timeday;

import com.mitsalami.timeday.commands.VoteCommand;
import com.mitsalami.timeday.commands.VoteDayCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimeDay extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;
        TimeHandler timeHandler = new TimeHandler();

        //Register Commands
        this.getCommand("voteDay").setExecutor(new VoteDayCommand(timeHandler));
        this.getCommand("vote").setExecutor(new VoteCommand(timeHandler));

        //Get Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
