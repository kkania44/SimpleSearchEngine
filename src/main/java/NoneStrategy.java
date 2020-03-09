import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NoneStrategy implements SearchStrategy {

    @Override
    public Set<Integer> search(String[] searchQuery, Map<String, Set<Integer>> occurrences) {
        Set<Integer> matchedIndexes = new HashSet<>();
        occurrences.values().forEach(matchedIndexes::addAll);

        if(searchQuery.length == 1) {
            matchedIndexes.removeAll(occurrences.get(searchQuery[0]));
        } else {
            for (String search : searchQuery) {
                if (occurrences.containsKey(search)) {
                    Set<Integer> toRemove = occurrences.get(search);
                    toRemove.retainAll(matchedIndexes);
                    matchedIndexes.removeAll(toRemove);
                    }
                }
            }

        return matchedIndexes;

    }
}
