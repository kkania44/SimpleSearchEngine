import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class AllStrategy implements SearchStrategy {


    @Override
    public Set<Integer> search(String[] searchQuery, Map<String, Set<Integer>> occurrences) {
        Set<Integer> finalResult = new HashSet<>();

        if (searchQuery.length == 1) {
            return occurrences.getOrDefault(searchQuery[0], finalResult);
        } else {
            if (occurrences.keySet().containsAll(Arrays.asList(searchQuery))) {
                Map<String, Set<Integer>> matches = new HashMap<>();
                for (String sq : searchQuery) {
                    matches.put(sq, occurrences.get(sq));
                }

                Map<Integer, Integer> countedOccurrences = countOccurrencesOfMatchingWords(matches);
                finalResult = getMatchingIndexes(countedOccurrences, finalResult, searchQuery);
            }
        }
        return finalResult;
    }

    private static Map<Integer, Integer> countOccurrencesOfMatchingWords(Map<String, Set<Integer>> matches) {
        Map<Integer, Integer> countOccurring = new HashMap<>();
        for (Set<Integer> si : matches.values()) {
            for(Integer i : si) {
                Integer val = countOccurring.get(i);
                if(val == null) {
                    countOccurring.put(i, 1);
                } else {
                    countOccurring.put(i, ++val);
                }
            }
        }
        return countOccurring;
    }

    private static Set<Integer> getMatchingIndexes(Map<Integer, Integer> countedOccurrences, Set<Integer> matchingIndexes, String[] searchQuery) {
        for (Map.Entry<Integer, Integer> entry : countedOccurrences.entrySet()) {
            if (entry.getValue() == searchQuery.length) {
                matchingIndexes.add(entry.getKey());
            }
        }
        return matchingIndexes;
    }
}
