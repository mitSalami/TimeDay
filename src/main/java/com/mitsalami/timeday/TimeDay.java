package com.mitsalami.timeday;

import com.mitsalami.timeday.commands.VoteDayCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimeDay extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("voteDay").setExecutor(new VoteDayCommand());

    }

    @Override
    public void onDisable() {

    }
}
