package com.cinemamod.bukkit.util;

import com.cinemamod.bukkit.CinemaModPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class JoinSetUpHandler {

    private final Set<UUID> playersMissingScreens;

    public JoinSetUpHandler() {
        playersMissingScreens = new HashSet<>();
    }

    public boolean hasScreen(UUID playerId) {
        return !playersMissingScreens.contains(playerId);
    }

    public void onPlayerJoin(UUID playerId) {
        System.out.println("This player needs screens: " + playerId.toString());
        playersMissingScreens.add(playerId);
    }

    public void onPlayerQuit(UUID playerId) {
        playersMissingScreens.remove(playerId);
    }

    public void onPlayerReceivedScreen(UUID playerId) {
        System.out.println("This player got screens: " + playerId.toString());
        playersMissingScreens.remove(playerId);
    }


}
