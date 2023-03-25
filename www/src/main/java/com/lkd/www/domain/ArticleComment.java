package com.lkd.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(name = "article_comment", indexes ={
        @Index(columnList = "content"),
        @Index(columnList = "regUser"),
        @Index(columnList = "regDate")
})
@EntityListeners(AuditingEntityListener.class) // **JpaConfig.auditorAware 이용하려면 꼭 추가해야함.
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter @ManyToOne(optional = false) private Article article;
    @Setter @Column(nullable = false, length = 500) private String content;

    @CreatedDate @Column(nullable = false) private LocalDateTime regDate;
    @CreatedBy @Column(nullable = false, length = 100) private String regUser;
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modDate;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modUser;

    protected ArticleComment() {}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
