import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {

        List<String> fileData = SearchEngine.getData("test.txt");

        Map<String, Set<Integer>> occurrences = SearchEngine.mapData(fileData);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        for (Map.Entry<String, Set<Integer>> entry : occurrences.entrySet()) {
//            System.out.println(entry.getKey() + " -> " +entry.getValue());
//        }

        int option = 99999;
        while (option != 0) {
            SearchEngine.displayMenu();

            option = SearchEngine.typeOption();

            switch (option) {
                case 1:
                    System.out.println("Choose strategy: ");
                    SearchStrategy strategy = selectSearchStrategy(reader.readLine().toUpperCase());
                    String[] search = SearchEngine.getSearchQuery();
                    Set<Integer> match = strategy.search(search, occurrences);
                    SearchEngine.printSearchResult(match, fileData);
//                    Set<Integer> found = SearchEngine.search(search, occurrences);
//                    SearchEngine.printSearchResult(found, fileData);
                    break;
                case 2:
                    SearchEngine.displayPeople(fileData);
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    public static SearchStrategy selectSearchStrategy(String strategy) {
        switch (strategy) {
            case "ALL":
                return new AllStrategy();
            case "ANY":
                return new AnyStrategy();
            case "NONE":
                return new NoneStrategy();
            default:
                throw new IllegalArgumentException(String.format("Strategy \'%s\' not recognized", strategy));
        }
    }

}

