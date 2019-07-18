package com.jay.li.reptile.service;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.jay.li.reptile.entity.HotNews;
import com.jay.li.reptile.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@PipelineName(value = "BaiduNewsHandler")
public class BaiduNewsHandler implements Pipeline<BaiduNewsService> {

    public static final Map<String, List<HotNews>> map = new ConcurrentHashMap<>();

    @Override
    public void process(BaiduNewsService baiduNewsClient) {
        List<String> hrefs = baiduNewsClient.getHrefs();
        List<String> titles = baiduNewsClient.getTitles();
        List<String> profileInfos = baiduNewsClient.getProfileInfos();
        List<String> source = baiduNewsClient.getSource();
        List<HotNews> hotNews = new ArrayList<>(10);
        int size = hrefs.size() > 10 ? 10 : hrefs.size();
        for (int i = 0; i < size; i++) {
            hotNews.add(HotNews.builder()
                    .title(titles.get(i))
                    .profileInfos(profileInfos.get(i))
                    .source(source.get(i))
                    .url(hrefs.get(i))
                    .build());
        }
        map.put(DateTimeUtils.nowDayStr() + baiduNewsClient.getWord(), hotNews);
    }
}