package com.jay.li.reptile.service;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.List;

@Data
@Gecco(matchUrl = "https://www.baidu.com/s?rtt=1&bsst=1&cl=2&tn=news&rsv_dl=ns_pc&word={word}", pipelines = "BaiduNewsHandler")
public class BaiduNewsService implements HtmlBean {

    @RequestParameter("word")
    private String word;

    @HtmlField(cssPath = "div.result h3 a")
    @Href("href")
    private List<String> hrefs;

    @HtmlField(cssPath = "div.result h3 a")
    @Text
    private List<String> titles;

    /**
     * 来源
     */
    @HtmlField(cssPath = "div.result p")
    private List<String> source;

    /**
     * 概要信息
     */
    @Text
    @HtmlField(cssPath = "div.c-summary,c-row")
    private List<String> profileInfos;
}
