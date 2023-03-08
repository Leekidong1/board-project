package com.lkd.www.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article;
    private String content;

    private LocalDateTime regDate;
    private String regUser;
    private LocalDateTime modDate;
    private String modUser;
}
