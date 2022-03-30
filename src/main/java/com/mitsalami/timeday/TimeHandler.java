package com.mitsalami.timeday;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class TimeHandler {

    private Boolean voteActive = false;
    private int yesVote;
    private int noVote;
    private int voteThreshold;
    private World world;

    public void playerVoted(Boolean vote, World world){
        if(!voteActive){
            startVote();
            this.world = world;
            yesVote++;
            checkVote();
        }else{
            yesVote++;
            checkVote();
        }
    }

    private void checkVote() {
        if(yesVote >= voteThreshold){
            world.setTime(0);
            resetVote();
        }
    }

    private void resetVote() {
        voteActive = false;
        yesVote = 0;
        noVote = 0;
        voteThreshold = 0;
    }

    private void startVote(){
        voteActive = true;
        int onlinePlayer = Bukkit.getOnlinePlayers().size();
        System.out.println(onlinePlayer);

        voteThreshold = onlinePlayer / 2;

        Bukkit.broadcastMessage("A Vote has been started!");
        Bukkit.broadcastMessage("Vote yes by typing in chat /voteday");
        Bukkit.broadcastMessage(yesVote + "have voted yes. We need " + voteThreshold + "votes.");
    }

    public Boolean getVoteActive() {
        return voteActive;
    }
}
