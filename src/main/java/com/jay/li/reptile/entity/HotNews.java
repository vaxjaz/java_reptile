package com.jay.li.reptile.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotNews {

    private String title;

    private String url;

    private String profileInfos;

    private String source;

}
