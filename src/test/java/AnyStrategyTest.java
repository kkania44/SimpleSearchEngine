import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AnyStrategyTest {

    @Test
     void shouldFindMatch() {
        SearchStrategy s = new AnyStrategy();

        List<String> list = Arrays.asList("one three", "three", "one two four", "two four", "two three");

        Map<String, Set<Integer>> map = SearchEngine.mapData(list);

        String[] search = "one four".split(" ");

        System.out.println(s.search(search, map));
    }

}