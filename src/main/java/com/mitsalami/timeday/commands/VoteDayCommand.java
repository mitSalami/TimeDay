package com.mitsalami.timeday.commands;

import com.mitsalami.timeday.TimeDay;
import com.mitsalami.timeday.TimeHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteDayCommand extends TimeHandler implements CommandExecutor {

    private TimeHandler timeHandler;

    String voteInOtherWorld = TimeDay.getPlugin().getConfig().getString("voteInOtherWorld");
    String alreadyVoted = TimeDay.getPlugin().getConfig().getString("alreadyVoted");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (getWorld() == player.getWorld() || getWorld() == null) {
                if (timeHandler.playerVotes.contains(player)) {
                    player.sendMessage(alreadyVoted);
                } else {

                    timeHandler.playerStartedVote(true, player.getWorld(), player);
                }
            } else {
                player.sendMessage(voteInOtherWorld);
            }
        }
        return true;
    }

    public VoteDayCommand(TimeHandler t) {
        this.timeHandler = t;
    }
}
