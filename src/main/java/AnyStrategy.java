import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnyStrategy implements SearchStrategy {

    @Override
    public Set<Integer> search(String[] searchQuery, Map<String, Set<Integer>> occurrences) {
        Set<Integer> matchedIndexes = new HashSet<>();

        if(searchQuery.length == 1) {
            return occurrences.getOrDefault(searchQuery[0], matchedIndexes);
        } else {
            for (String key : occurrences.keySet()) {
                for (String query : searchQuery) {
                    if (key.equals(query)) {
                        matchedIndexes.addAll(occurrences.get(key));
                    }
                }
            }
            return matchedIndexes;
        }
    }
}
