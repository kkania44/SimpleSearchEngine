import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


class AllStrategyTest {

    @Test
    void shouldFindMatches() {
        SearchStrategy s = new AllStrategy();

        List<String> list = Arrays.asList("one TWO three", "three Three one six", "Six", "one four siX");

        String[] searchQuery = "one six".split(" ");

        Map<String, Set<Integer>> map = SearchEngine.mapData(list);

        Set<Integer> result = s.search(searchQuery, map);

        System.out.println(result);

    }

}