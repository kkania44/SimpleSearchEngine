import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void displayMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    public static int typeOption() {
        while (true) {
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Type a number");
            }
        }
    }

    public static List<String> getData(String path) {
        List<String> fileData = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(path))) {
            fileData = fileReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }

    public static Map<String, Set<Integer>> mapData(List<String> data) {
        Map<String, Set<Integer>> occurrences = new HashMap<>();
        for (int index = 0; index < data.size(); index++) {
            String line = data.get(index).toLowerCase();
            String[] splitLine = line.split("\\s+");
            for (String word : splitLine) {
                if (occurrences.containsKey(word)) {
                    Set<Integer> existingPos = occurrences.get(word);
                    existingPos.add(index);
                } else {
                    Set<Integer> positions = new HashSet<>();
                    positions.add(index);
                    occurrences.put(word, positions);
                }
            }
        }
        return occurrences;
    }

    public static void displayPeople(List<String> people) {
        for (String person : people) {
            System.out.println(person);
        }
    }

    public static String[] getSearchQuery() throws IOException {
        System.out.println("Enter a name or email to search all suitable people.");
        return reader.readLine().toLowerCase().split("\\s+");
    }


    public static Set<Integer> search(String searchQuery, Map<String, Set<Integer>> data) throws IOException {
        if (data.containsKey(searchQuery)) {
            return data.get(searchQuery);
        } else {
            return new HashSet<>();
        }
    }

    public static void printSearchResult(Set<Integer> indexes, List<String> people) {
        if(indexes.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            int numberOfFoundPeople = indexes.size();
            System.out.println(numberOfFoundPeople + " persons found:");
            for (Integer i : indexes) {
                System.out.println(people.get(i));
            }
        }
    }
}
