import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NoneStrategy implements SearchStrategy {

    Logger logger = Logger.getLogger(NoneStrategy.class.getName());

    @Override
    public Set<Integer> search(String[] searchQuery, Map<String, Set<Integer>> occurrences) {
        Set<Integer> matchedIndexes = new HashSet<>();
        occurrences.values().forEach(matchedIndexes::addAll);

        logger.setLevel(Level.INFO);

        if(searchQuery.length == 1) {
            matchedIndexes.removeAll(occurrences.get(searchQuery[0]));
        } else {
            for (String search : searchQuery) {
                if (occurrences.containsKey(search)) {
                    Set<Integer> toRemove = occurrences.get(search);
                    toRemove.retainAll(matchedIndexes);
//                    logger.info(toRemove.toString());
                    matchedIndexes.removeAll(toRemove);
//                    logger.info(matchedIndexes.toString());
                    }
                }
            }

        return matchedIndexes;

    }
}
