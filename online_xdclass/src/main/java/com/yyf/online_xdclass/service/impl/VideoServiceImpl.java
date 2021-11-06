package com.yyf.online_xdclass.service.impl;

import com.yyf.online_xdclass.config.CacheKeyManager;
import com.yyf.online_xdclass.model.entity.Video;
import com.yyf.online_xdclass.model.entity.VideoBanner;
import com.yyf.online_xdclass.mapper.VideoMapper;
import com.yyf.online_xdclass.service.VideoService;
import com.yyf.online_xdclass.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> videoList() {
        Object cacheObject = null;
        try {
            cacheObject = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST, () -> {
                List<Video> videoList = videoMapper.videoList();
                System.out.println("从数据库中找视频列表");
                return videoList;
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (cacheObject instanceof List) {
            List<Video> videoList = (List<Video>)cacheObject;
            return videoList;
        }
        // 可返回兜底数据，业务系统降级
        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {
        try {
           Object cacheObject =  baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, () -> {
                List<VideoBanner> bannerList = videoMapper.bannerList();
               System.out.println("从数据库中找轮播图列表");
                return bannerList;
            });

            if (cacheObject instanceof List) {
                List<VideoBanner> bannerList = (List<VideoBanner>)cacheObject;
                return bannerList;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Video findDetailById(int videoId) {
        //单独构建一个缓存key，每个视频的key是不一样的
        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL,videoId);

        try{

            Object cacheObject = baseCache.getOneHourCache().get( videoCacheKey, ()->{

                // 需要使用mybaits关联复杂查询
                Video video = videoMapper.findDetailById(videoId);

                return video;

            });

            if(cacheObject instanceof Video){

                Video video = (Video)cacheObject;
                return video;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
