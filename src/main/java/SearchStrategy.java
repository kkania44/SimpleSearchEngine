import java.util.Map;
import java.util.Set;

public interface SearchStrategy {

    Set<Integer> search(String[] searchQuery, Map<String, Set<Integer>> occurrences);

}
