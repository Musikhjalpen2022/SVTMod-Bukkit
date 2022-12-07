package com.cinemamod.bukkit.service;

import com.cinemamod.bukkit.buffer.PacketByteBufReimpl;
import com.cinemamod.bukkit.buffer.PacketByteBufSerializable;
import org.apache.commons.lang.NotImplementedException;

public enum VideoServiceType implements PacketByteBufSerializable<VideoServiceType> {

    YOUTUBE(
            "https://cinemamod-static.ewr1.vultrobjects.com/service/v1/youtube.html",
            "th_volume(%d);",
            "th_video('%s', %b);",
            "th_seek(%d);",
            "https://www.youtube.com/watch?v=%s"),
    TWITCH(
            "https://cinemamod-static.ewr1.vultrobjects.com/service/v1/twitch.html",
            "th_volume(%d);",
            "th_video('%s');",
            "",
            "https://www.twitch.tv/%s"),
    FILE(
            "https://cinemamod-static.ewr1.vultrobjects.com/service/v1/file.html",
            "th_volume(%d);",
            "th_video('%s');",
            "th_seek(%d);",
            "%s"),
    HLS(
            "https://cinemamod-static.ewr1.vultrobjects.com/service/v1/hls.html",
            "th_volume(%d);",
            "th_video('%s');",
            "",
            "%s"),

    // document.querySelectorAll('[data-rt="top-area-play-button"]')[0]?.click();
    SVT(
            "https://www.svtplay.se/%s",
            """
                        player = document.getElementsByClassName("_video-player__video_qoxkq_1")[0];
                        player.volume = %f;""",
            """
                    btn = document.querySelectorAll('[data-rt="top-area-play-button"]')[0];
                    if (btn) {
                        btn.click();
                        document.getElementsByClassName("_video-player__video_qoxkq_1")[0]?.setAttribute("loop", "loop");
                    } else {
                        document.querySelectorAll('[data-rt="video-player-splash-play"]')[0]?.click();
                        var player = document.getElementsByClassName("_video-player_qoxkq_1 _video-player--16-9_qoxkq_1")[0];
                        element = document.getElementsByClassName("_video-player_qoxkq_1 _video-player--16-9_qoxkq_1")[0];
                        element.parentNode.removeChild(element);
                        document.body.appendChild(player);
                        element = document.getElementById("__next");
                        element.parentNode.removeChild(element);
                    }
                    navigator.mediaSession.setActionHandler('pause', function() {});
                    document.getElementsByClassName("_video-player__video_qoxkq_1")[0]?.play();"""
            /*"""
                    """*/,
            "",
            "https://www.svtplay.se/%s"
    );

    private String serviceUrl;
    private String setVolumeJs;
    private String startJs;
    private String seekJs;
    private String originUrlFormat;

    VideoServiceType(String serviceUrl, String setVolumeJs, String startJs, String seekJs, String originUrlFormat) {
        this.serviceUrl = serviceUrl;
        this.setVolumeJs = setVolumeJs;
        this.startJs = startJs;
        this.seekJs = seekJs;
        this.originUrlFormat = originUrlFormat;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getSetVolumeJs() {
        return setVolumeJs;
    }

    public String getStartJs() {
        return startJs;
    }

    public String getSeekJs() {
        return seekJs;
    }

    public String getOriginUrl(String id) {
        return String.format(originUrlFormat, id);
    }

    @Override
    public VideoServiceType fromBytes(PacketByteBufReimpl buf) {
        throw new NotImplementedException("Not implemented on server");
    }

    @Override
    public void toBytes(PacketByteBufReimpl buf) {
        buf.writeString(name());
        buf.writeString(serviceUrl);
        buf.writeString(setVolumeJs);
        buf.writeString(startJs);
        buf.writeString(seekJs);
    }

}
