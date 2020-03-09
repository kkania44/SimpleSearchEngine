import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


class AllStrategyTest {

    @Test
    void shouldFindMatches() {
        SearchStrategy s = new AllStrategy();

        List<String> list = Arrays.asList("One two three", "Two three four", "One five six", "Four Seven eight");

        String[] searchQuery = "three four five".split("\\s+");

        Map<String, Set<Integer>> map = SearchEngine.mapData(list);

        Set<Integer> result = s.search(searchQuery, map);

        System.out.println(result);

    }

}