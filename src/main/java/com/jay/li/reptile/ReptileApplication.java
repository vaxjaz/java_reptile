package com.jay.li.reptile;

import com.geccocrawler.gecco.GeccoEngine;
import com.jay.li.reptile.entity.HotNews;
import com.jay.li.reptile.service.BaiduNewsHandler;
import com.jay.li.reptile.util.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping
@Slf4j
public class ReptileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReptileApplication.class, args);
    }

    @GetMapping("/hot/news/{keyword}")
    public List<HotNews> hotNews(@PathVariable("keyword") String keyword) {
        String encodeKeyword = "";
        try {
            encodeKeyword = URLEncoder.encode(keyword, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("查询百度编码失败 e={}", e.getMessage());
        }
        GeccoEngine.create()
                .classpath("com.jay.li.reptile.service")
                .start("https://www.baidu.com/s?rtt=1&bsst=1&cl=2&tn=news&rsv_dl=ns_pc&word=" + encodeKeyword)
                .thread(2)
                .interval(1000)
                .mobile(false)
                // start异步执行
//                .start()
                .run();
        String key = DateTimeUtils.nowDayStr() + keyword;
        List<HotNews> hotNews = BaiduNewsHandler.map.get(key);
        BaiduNewsHandler.map.remove(key);
        return hotNews;
    }
}
