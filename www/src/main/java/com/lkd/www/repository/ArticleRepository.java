package com.lkd.www.repository;

import com.lkd.www.domain.Article;
import com.lkd.www.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // Article 객체 안에 모든 필드들이 검색가능함.
        QuerydslBinderCustomizer<QArticle> {
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true); // 모든 필드말고 선택 필드 검색 옵션 true 변경
        bindings.including(root.title, root.content, root.hashtag, root.regDate, root.regUser);// 검색필드 설정하기
//      bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${value}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${value}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.regDate).first(DateTimeExpression::eq);
        bindings.bind(root.regUser).first(StringExpression::containsIgnoreCase);
    }

}
