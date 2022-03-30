package com.mitsalami.timeday.commands;

import com.mitsalami.timeday.TimeHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand extends TimeHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){

            if(!getVoteActive()){
                player.sendMessage("No Vote active!");
                return true;
            }

            if(!playerVotes.contains(player)){
                playerVotes.add(player);
            }else{return false;}
            if (args[0].equalsIgnoreCase("yes")) {
                playerVote(player, player.getWorld(), true);
                playerVotes.add(player);
                return true;
            }else{
                playerVote(player, player.getWorld(), false);
                playerVotes.add(player);
                return true;
            }
        }
        return false;
    }
}
