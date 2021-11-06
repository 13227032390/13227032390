package com.yyf.online_xdclass.service;

import com.yyf.online_xdclass.model.entity.Video;
import com.yyf.online_xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    List<Video> videoList();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
