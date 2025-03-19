package org.skypro.skyshop.search;


import java.util.UUID;

public interface Searchable {
    UUID getID();

    String getSearchTerm();

    String getSearchContent();



    default String getStringPresentation() {
        return getSearchTerm() + " - " + getSearchContent();
    }


}
