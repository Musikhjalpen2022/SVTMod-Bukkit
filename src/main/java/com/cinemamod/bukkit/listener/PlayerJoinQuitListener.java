package com.cinemamod.bukkit.listener;

import com.cinemamod.bukkit.CinemaModPlugin;
import com.cinemamod.bukkit.theater.Theater;
import com.cinemamod.bukkit.theater.screen.Screen;
import com.cinemamod.bukkit.util.NetworkUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerJoinQuitListener implements Listener {

    private CinemaModPlugin cinemaModPlugin;

    public PlayerJoinQuitListener(CinemaModPlugin cinemaModPlugin) {
        this.cinemaModPlugin = cinemaModPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        cinemaModPlugin.getPlayerDataManager().getData(player.getUniqueId());
        cinemaModPlugin.getJoinSetUpHandler().onPlayerJoin(player.getUniqueId());

    }

    public static void handleJoin(CinemaModPlugin cinemaModPlugin, Player player) {

        NetworkUtil.sendRegisterServicesPacket(cinemaModPlugin, player);

        List<Screen> screens = cinemaModPlugin.getTheaterManager().getTheaters()
                .stream()
                .map(Theater::getScreen)
                .collect(Collectors.toList());

        NetworkUtil.sendScreensPacket(cinemaModPlugin, player, screens);

        cinemaModPlugin.getTheaterManager().getTheaters().forEach(t -> t.sendUpdatePreviewScreensPacket(player));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        cinemaModPlugin.getPlayerDataManager().unload(player.getUniqueId());
        cinemaModPlugin.getJoinSetUpHandler().onPlayerQuit(player.getUniqueId());
    }

}
