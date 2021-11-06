package com.yyf.online_xdclass.mapper;

import com.yyf.online_xdclass.model.entity.Video;
import com.yyf.online_xdclass.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    /**
     * 查询全部视频列表
     * @return
     */
    List<Video> videoList();

    /**
     * 首页轮播图列表
     * @return
     */
    List<VideoBanner> bannerList();

    /**
     * 查询视频详情
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("video_id") int videoId);

    /**
     * 简单查询视频详情
     * @param videoId
     * @return
     */
    Video findById(@Param("video_id") int videoId);
}