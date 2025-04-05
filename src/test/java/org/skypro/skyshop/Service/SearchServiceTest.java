package org.skypro.skyshop.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Searchable;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    private List<Searchable> testData;

    @BeforeEach
    void SetUp() {
        UUID appleID = UUID.randomUUID();
        UUID orangeID = UUID.randomUUID();
        UUID melonArticleID = UUID.randomUUID();

        testData = Arrays.asList(
            new SimpleProduct(appleID, "Яблоко", 115),
            new DiscountedProduct(orangeID, "Апельсин", 95, 5),
            new Article(melonArticleID, "Дыня1", "Сочная и вкусная")
        );
    }

    @Test
    void search_WhenStorageEmpty_ShouldReturnEmptyList() {

        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("Яблоко");

        assertTrue(results.isEmpty());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void search_WhenNoMatches_ShouldReturnEmptyList() {

        when(storageService.getAllSearchables()).thenReturn(testData);

        Collection<SearchResult> results = searchService.search("банан");

        assertTrue(results.isEmpty());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void search_WhenSingleMatch_ShouldReturnOneResult() {

        when(storageService.getAllSearchables()).thenReturn(testData);

        Collection<SearchResult> results = searchService.search("апельсин");

        assertEquals(1, results.size());
        SearchResult result = results.iterator().next();
        assertEquals("Апельсин", result.getName());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void search_ShouldBeCaseInsentive() {

        when(storageService.getAllSearchables()).thenReturn(testData);

        Collection<SearchResult> resultsLower = searchService.search("яблоко");
        Collection<SearchResult> resultsUpper = searchService.search("ЯБЛОКО");
        Collection<SearchResult> resultsMixed = searchService.search("ЯблОко");

        assertEquals(1, resultsLower.size());
        assertEquals(1, resultsUpper.size());
        assertEquals(1, resultsMixed.size());
        assertEquals("Яблоко", resultsLower.iterator().next().getName());
        verify(storageService, times(3)).getAllSearchables();
    }

    @Test
    void search_WhenEmptyPattern_ShouldReturnAllItems() {

        when(storageService.getAllSearchables()).thenReturn(testData);

        Collection<SearchResult> results = searchService.search("");

        assertEquals(testData.size(), results.size());
        verify(storageService, times(1)).getAllSearchables();
    }

}
