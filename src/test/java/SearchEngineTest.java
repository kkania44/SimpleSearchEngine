import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {

    @Test
    void shouldMapOneLine() {
    // given
    List<String> data = Arrays.asList("One test line");
    Map<String, Set<Integer>> expectedMap = Map.of(
                            "one", Set.of(0),
                            "test", Set.of(0),
                            "line", Set.of(0));


    //when
    Map<String, Set<Integer>> actualMap = SearchEngine.mapData(data);

    // then
    assertEquals(expectedMap, actualMap);
    }

    @Test
    void shouldMapOneWordTwice(){
        List<String> data = Arrays.asList("First test line", "Second test line2");
        Map<String, Set<Integer>> expectedMap = Map.of(
                            "first", Set.of(0),
                            "test", Set.of(0,1),
                            "line", Set.of(0),
                            "second", Set.of(1),
                            "line2", Set.of(1));

        // when
        Map<String, Set<Integer>> actualMap = SearchEngine.mapData(data);

        // then
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void shouldNotFoundAnyMatches() throws IOException {
        // given
        List<String> data = Arrays.asList("Will not match", "Cannot do match");
        Map<String, Set<Integer>> map = SearchEngine.mapData(data);

        // when
        Set<Integer> foundIndexes = SearchEngine.search("stawka", map);

        // then
        assertEquals(0, foundIndexes.size());
    }

    @Test
    void shouldFoundOneMatch() throws IOException {
        // given
        List<String> data = Arrays.asList("Line without match", "Line with supermatch");
        Map<String, Set<Integer>> map = SearchEngine.mapData(data);

        // when
        Set<Integer> foundIndexes = SearchEngine.search("with", map);

        // then
        assertFalse(foundIndexes.isEmpty());
        assertEquals(Set.of(1), foundIndexes);
    }

    @Test
    void contains() {
        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
        boolean flag = map.keySet().containsAll(Arrays.asList("one", "three"));

        assertTrue(flag);
    }

}
