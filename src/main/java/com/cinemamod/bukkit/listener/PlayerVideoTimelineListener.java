package com.cinemamod.bukkit.listener;

import com.cinemamod.bukkit.CinemaModPlugin;
import com.cinemamod.bukkit.theater.StaticTheater;
import com.cinemamod.bukkit.theater.Theater;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerVideoTimelineListener implements Listener {

    private CinemaModPlugin cinemaModPlugin;

    public PlayerVideoTimelineListener(CinemaModPlugin cinemaModPlugin) {
        this.cinemaModPlugin = cinemaModPlugin;
    }

}
