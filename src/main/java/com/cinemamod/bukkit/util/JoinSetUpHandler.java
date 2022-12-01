package com.cinemamod.bukkit.util;

import com.cinemamod.bukkit.CinemaModPlugin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class JoinSetUpHandler {

    private final static int TIMEOUT = 30;

    private final Map<UUID, LocalDateTime> playersMissingScreens;

    public JoinSetUpHandler() {
        playersMissingScreens = new HashMap<>();
    }

    public boolean hasScreen(UUID playerId) {
        if (playersMissingScreens.containsKey(playerId)) {
            if (playersMissingScreens.get(playerId).until(LocalDateTime.now(), ChronoUnit.SECONDS) > TIMEOUT) {
                playersMissingScreens.remove(playerId);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public void onPlayerJoin(UUID playerId) {
        System.out.println("This player needs screens: " + playerId.toString());
        playersMissingScreens.put(playerId, LocalDateTime.now());
    }

    public void onPlayerQuit(UUID playerId) {
        playersMissingScreens.remove(playerId);
    }

    public void onPlayerReceivedScreen(UUID playerId) {
        System.out.println("This player got screens: " + playerId.toString());
        playersMissingScreens.remove(playerId);
    }


}
