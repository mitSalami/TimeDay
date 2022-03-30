package com.mitsalami.timeday;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class TimeHandler{

    private Boolean voteActive = false;
    private int yesVote;
    private int noVote;
    private int voteThreshold;
    private World world;
    BossBar progressbar = Bukkit.createBossBar("VoteProgress", BarColor.BLUE, BarStyle.SEGMENTED_10);

    public World getWorld() {
        return this.world;
    }

    public void playerVoted(Boolean vote, World world){
        if(!voteActive){
            startVote();
            this.world = world;
            yesVote++;

            //Add Progressbar
            for(Player people: Bukkit.getOnlinePlayers()){  //Loops threw all player
                if(people.getWorld() == world){             //Checks if they are in the same world as the vote
                    progressbar.addPlayer(people);          //Adds them to the progressbar
                }
            }
            progressbar.setVisible(true);                   //makes the progressbar vissible

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
        }else{
            progressbar.setProgress(yesVote / voteThreshold);
            progressbar.setTitle("Yes: " + yesVote + " No: " + noVote);
        }
    }

    private void resetVote() {
        voteActive = false;
        yesVote = 0;
        noVote = 0;
        voteThreshold = 0;
        progressbar.setVisible(false);
    }

    private void startVote(){
        voteActive = true;
        int onlinePlayer = Bukkit.getOnlinePlayers().size();
        System.out.println(onlinePlayer);

        voteThreshold = (int) (onlinePlayer / 2f);

        Bukkit.broadcastMessage("A Vote has been started!");
    }

    public Boolean getVoteActive() {
        return voteActive;
    }
}
