package com.mitsalami.timeday.commands;

import com.mitsalami.timeday.TimeHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteDayCommand extends TimeHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            playerVoted(true, player.getWorld());
        }
        return true;
    }
}