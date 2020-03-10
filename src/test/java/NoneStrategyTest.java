import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class NoneStrategyTest {

    private Map<String, Set<Integer>> occurrences;
    private final SearchStrategy searchStrategy = new NoneStrategy();

    @BeforeEach
    void setUp() {
        occurrences = SearchEngine.mapData(Arrays.asList("One two three", "Two three four", "One five six", "four seven eight"));
    }

    @Test
    void shouldFindOneMatchingLine() {
        // given
        String[] search1 = "three four seven".split("\\s+");
        String[] search2 = "one eight".split("\\s+");
        Set<Integer> expected1 = Set.of(2);
        Set<Integer> expected2 = Set.of(1);

        // when
        Set<Integer> actual1 = searchStrategy.search(search1, occurrences);
        Set<Integer> actual2 = searchStrategy.search(search2, occurrences);

        // then
        assertEquals(expected1.size(), actual1.size());
        assertEquals(expected2.size(), actual2.size());
        assertTrue(actual1.containsAll(expected1));
        assertTrue(actual2.containsAll(expected2));

    }

    @Test
    void shouldFindTwoMatchingLines() {
        String[] search3 = "four seven".split("\\s+");
        Set<Integer> expected3 = Set.of(0,2);

        Set<Integer> actual3 = searchStrategy.search(search3, occurrences);

        assertEquals(expected3.size(), actual3.size());
        assertTrue(actual3.containsAll(expected3));
    }

    @Test
    void shouldFindNoMatchingLine() {
        // when
        String[] search = "one four".split("\\s+");
        Set<Integer> expected = Collections.emptySet();

        // when
        Set<Integer> actual = searchStrategy.search(search, occurrences);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void shouldMatchAllLines() {
        // when
        String[] search = "nine ten eleven".split("\\s+");
        Set<Integer> expected = Set.of(0,1,2,3);

        // when
        Set<Integer> actual = searchStrategy.search(search, occurrences);

        // then
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }

}