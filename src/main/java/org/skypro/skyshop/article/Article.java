package org.skypro.skyshop.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.UUID;

public final class Article implements Searchable {
    
    private final String articleName;
    private final String articleText;
    private final UUID id;

    public Article(UUID id, String articleName, String articleText) {
        this.id = id;
        this.articleName = articleName;
        this.articleText = articleText;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleText() {
        return articleText;
    }

    @Override
    public String toString() {
        return articleName + "\n" + articleText;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getSearchContent() {
        return "ARTICLE";
    }

    @Override
    public int hashCode() {
        return articleName.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return articleName.equals(article.articleName);
    }

    @Override
    public UUID getID() {
        return id;
    }
    


}


