import java.util.Map;
import java.util.Set;

public class SearchSelector {

    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public Set<Integer> searchQuery(String[] query, Map<String, Set<Integer>> occurrences) {
        return strategy.search(query, occurrences);
    }

}
