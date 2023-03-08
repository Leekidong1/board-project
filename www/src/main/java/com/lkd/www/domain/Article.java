package com.lkd.www.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String content;
    private String hashtag;

    private LocalDateTime regDate;
    private String regUser;
    private LocalDateTime modDate;
    private String modUser;
}
