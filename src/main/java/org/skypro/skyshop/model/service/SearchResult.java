package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = Objects.requireNonNull(id, "ID не может быть null");
        this.name = Objects.requireNonNull(name, "Название не может быть null");
        this.contentType = Objects.requireNonNull(contentType, "Тип контента не может быть null");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(
                searchable.getID().toString(),
                searchable.getSearchTerm(),
                searchable.getSearchContent()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return id.equals(that.id) && name.equals(that.name) && contentType.equals(that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType);
    }

    @Override
    public String toString() {
        return "SearchResult{" + "id=" + id + '\''+
                ", name=" + name + '\'' + ", contentType=" + contentType + '\'' + '}';
    }

}
