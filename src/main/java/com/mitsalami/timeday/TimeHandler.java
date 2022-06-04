package com.mitsalami.timeday;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class TimeHandler {

    //Get Variables from config
    Integer timeDay = TimeDay.getPlugin().getConfig().getInt("timeDay");
    String voteStartMessage = TimeDay.getPlugin().getConfig().getString("voteStartMessage");
    String voteStartMessage2 = TimeDay.getPlugin().getConfig().getString("voteStartMessage2");
    String voteSuccessfulMessage = TimeDay.getPlugin().getConfig().getString("voteSuccessfulMessage");
    Boolean progressBarEnabled = TimeDay.getPlugin().getConfig().getBoolean("progressBarEnabled");

    private Boolean voteActive = false;
    private int yesVote;
    private int noVote;
    private float voteThreshold;
    public HashSet<Player> playerVotes = new HashSet<Player>();
    private World world;
     BossBar progressbar = Bukkit.createBossBar("VoteProgress", BarColor.BLUE, BarStyle.SEGMENTED_10);
     private Plugin plugin;

    public TimeHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    public TimeHandler(){

    }

    public World getWorld() {
        return this.world;
    }

    public void playerStartedVote(Boolean vote, World world, Player p) {
        if (!voteActive) {
            startVote();
            this.world = world;
            yesVote++;

            //Add Progressbar
            for (Player people : Bukkit.getOnlinePlayers()) {  //Loops threw all player
                if (people.getWorld() == world) {             //Checks if they are in the same world as the vote
                    progressbar.addPlayer(people);          //Adds them to the progressbar
                }
            }
            if (progressBarEnabled) {
                progressbar.setVisible(true);                   //makes the progressbar visible
            }
            playerVotes.add(p);
            checkVote();
        } else {
            return;
        }
    }

    public void playerVote(Player p, World w, boolean v) {
        if (w.equals(world)) {
            if (v) {
                this.world = w;
                yesVote++;
                checkVote();
            } else {
                this.world = w;
                noVote++;
                checkVote();
            }
        }
    }

    private void checkVote() {          //function to check if the vote is successful
        if (yesVote >= voteThreshold) {
            for (Player people : Bukkit.getOnlinePlayers()) {
                if (people.getWorld().equals(world)) {
                    people.sendMessage(voteSuccessfulMessage);
                }
            }
            this.world.setTime(timeDay);
            resetVote();
        } else {
            progressbar.setProgress(yesVote / voteThreshold);
            progressbar.setTitle("Yes: " + yesVote + " No: " + noVote);
        }
    }

    private void resetVote() {
        yesVote = 0;
        noVote = 0;
        voteThreshold = 0;
        for (Player people : Bukkit.getOnlinePlayers()) {  //Loops threw all player
            if (people.getWorld() == world) {             //Checks if they are in the same world as the vote
                progressbar.removePlayer(people);          //Removes them from the progressbar
            }
        }
        progressbar.removeAll();
        playerVotes.clear();
        voteActive = false;
    }

    private void startVote() {
        voteActive = true;
        int onlinePlayer = Bukkit.getOnlinePlayers().size();

        if(onlinePlayer > 2 || onlinePlayer == 1) {
            voteThreshold = onlinePlayer / 2f;
        }else{
            voteThreshold = 2;          //when 2 players are online both have to vote yes.
        }
        for (Player people : Bukkit.getOnlinePlayers()) {
            if (people.getWorld().equals(world)) {
                people.sendMessage(voteStartMessage);
                people.sendMessage(voteStartMessage2);

            }
        }
    }

    public Boolean getVoteActive() {
        return voteActive;
    }
}
