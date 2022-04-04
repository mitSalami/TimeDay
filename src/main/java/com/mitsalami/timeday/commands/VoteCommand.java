package com.mitsalami.timeday.commands;

import com.mitsalami.timeday.TimeDay;
import com.mitsalami.timeday.TimeHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand extends TimeHandler implements CommandExecutor {

    private String noVoteActive = TimeDay.getPlugin().getConfig().getString("noVoteActive");
    String alreadyVoted = TimeDay.getPlugin().getConfig().getString("alreadyVoted");
    private TimeHandler timeHandler;

    public VoteCommand(TimeHandler timeHandler) {
        this.timeHandler = timeHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {

            if (!timeHandler.getVoteActive()) {           //Checks if a vote is active
                player.sendMessage(noVoteActive);
                return true;
            }

            if (!timeHandler.playerVotes.contains(player)) {      //checks if the player has already voted
                timeHandler.playerVotes.add(player);
            } else {
                player.sendMessage(alreadyVoted);
                return false;
            }

            if (args.length <= 0) {   //check if there are any arguments
                return false;
            }
            if (args[0].equalsIgnoreCase("yes")) {              //player voted yes
                timeHandler.playerVote(player, player.getWorld(), true);
                timeHandler.playerVotes.add(player);
                return true;
            } else if (args[0].equalsIgnoreCase("no")) {           //player voted no
                timeHandler.playerVote(player, player.getWorld(), true);
                timeHandler.playerVotes.add(player);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
