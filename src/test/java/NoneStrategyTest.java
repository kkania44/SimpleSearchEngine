import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class NoneStrategyTest {

    private Map<String, Set<Integer>> occurrences;
    private final SearchStrategy searchStrategy = new NoneStrategy();

    @BeforeEach
    void setUp() {
        occurrences = SearchEngine.mapData(Arrays.asList("One two three", "Two three four", "One five six", "Four Seven eight"));
    }

    @Test
    void shouldFindOneMatchingLine() {
        // given
        String[] search = "three four seven".split("\\s+");
        Set<Integer> expected = Set.of(2);
        int sizeEx = expected.size();

        // when
        Set<Integer> actual = searchStrategy.search(search, occurrences);
        int sizeAc = actual.size();

        // then
        assertEquals(sizeEx, sizeAc);
        assertTrue(actual.containsAll(expected));
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