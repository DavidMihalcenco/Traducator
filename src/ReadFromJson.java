import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ReadFromJson {

    public ReadFromJson() {
    }

    Dictionary readAllFiles() throws FileNotFoundException {

        File folder = new File("C:\\Users\\david.mihalcenco\\IdeaProjects\\tema2.1\\citire");
        File[] listOfFiles = folder.listFiles();
        Gson gson = new Gson();
        String subString;
        Map<String, Word[]> hashMap = new HashMap<>(); // creez un hashmap

        for (File file : listOfFiles) {
            FileReader fileReader = new FileReader(file);
            Word[] word = gson.fromJson(fileReader , Word[].class);

            if (file.isFile()) {

                int iend = file.getName().indexOf("_"); // delimitatorul dintre cuvinte

                if (iend != -1)
                {
                    subString= file.getName().substring(0 , iend);
                    hashMap.put(subString, word); // iau primul cuvant din denumire adica fr sau ro
                }
            }
        }
        Dictionary dictionary = new Dictionary(hashMap);
        return dictionary;
    }
}
