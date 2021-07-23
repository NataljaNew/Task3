import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("people.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List <Person> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader( new FileReader(file))){
            br.readLine();
            String line;
            while ((line = br.readLine())!=null) {
                String[] elements = line.split(",");
                if (elements.length >=2) {
                    list.add(new Person(Integer.parseInt(elements[0].trim()),elements[1].trim(),elements[2].trim()));
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List <String> names = list.stream()
                .map (n -> n.getName()) // String vardai
                .sorted() // rusiuojam vardus
                .distinct() // paliekam tik unikalius vardus
                .collect(Collectors.toList());
        names.forEach(System.out::println);
    }
}

