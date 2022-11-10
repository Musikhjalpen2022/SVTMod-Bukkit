package com.cinemamod.bukkit.service.infofetcher;

import com.cinemamod.bukkit.service.VideoServiceType;
import com.cinemamod.bukkit.storage.VideoInfo;
import java.util.concurrent.CompletableFuture;

public class SVTVideoInfoFetcher extends VideoInfoFetcher {

    String streamId;

    public SVTVideoInfoFetcher(String streamId) {
        super("cinemamod.request.svt");
        this.streamId = streamId;
    }

    @Override
    public CompletableFuture<VideoInfo> fetch() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new VideoInfo(VideoServiceType.SVT,
                        streamId,
                        streamId,
                        "",
                        "",
                        0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
