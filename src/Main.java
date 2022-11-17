import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        Dictionary dictionary ;
        ReadFromJson readFromJson =  new ReadFromJson();
        dictionary = readFromJson.readAllFiles();
        Gson gson = new Gson();

        System.out.println();
        System.out.println("AddWord caz cand cuvantul exista : ");
        FileReader fileReaderex1 = new FileReader("C:\\Users\\david.mihalcenco\\IdeaProjects\\" +
                "tema2.1\\src\\teste\\ex1.json");
        Word cuvant = gson.fromJson(fileReaderex1 , Word.class);
        dictionary.addWord(cuvant ,"ro");
        for(Word word1 : dictionary.getDictionaries().get("ro")){
            System.out.println(word1.getWord());
        }

        System.out.println();
        System.out.println("AddWord caz cand se adauga cuvantul : ");
        FileReader fileReader11 = new FileReader("C:\\Users\\david.mihalcenco\\IdeaProjects\\" +
                "tema2.1\\src\\teste\\ex11.json");
        Word cuvant1 = gson.fromJson(fileReader11 , Word.class);
        dictionary.addWord(cuvant1 ,"ro");
        for(Word word1 : dictionary.getDictionaries().get("ro")){
            System.out.println(word1.getWord());
        }

        System.out.println();
        System.out.println("RemoveWord caz cand cuvantul nu exista : ");
        String cuvant2 = "acolo";
        dictionary.removeWord(cuvant2,"ro");
        for(Word word1 : dictionary.getDictionaries().get("ro")){
            System.out.println(word1.getWord());
        }

        System.out.println();
        System.out.println("RemoveWord caz cand cuvantul exista : ");
        String cuvant22 = "arici";
        dictionary.removeWord(cuvant22,"ro");
        for(Word word1 : dictionary.getDictionaries().get("ro")){
            System.out.println(word1.getWord());
        }

        System.out.println();
        FileReader fileReader1 = new FileReader("C:\\Users\\david.mihalcenco\\IdeaProjects\\" +
                "tema2.1\\src\\teste\\definition.json");
        System.out.println("AddDefinition caz cand definitia exista : ");
        String cuvant3 = "câine";
        Definition definition = gson.fromJson(fileReader1 , Definition.class);
        dictionary.addDefinition(cuvant3,"ro",definition);
        for(Word word2 : dictionary.getDictionaries().get("ro")){
            if(word2.getWord().equals(cuvant3)){

                for(Definition definition1 : word2.getDefinitions()){

                    System.out.println(definition1.getDict());
                }
            }
        }

        System.out.println();
        FileReader fileReaderex4 = new FileReader("C:\\Users\\david.mihalcenco\\IdeaProjects\\" +
                "tema2.1\\src\\teste\\definition1.json");
        System.out.println("AddDefinition caz cand definitia se adauga : ");
        Definition definition3 = gson.fromJson(fileReaderex4 , Definition.class);
        dictionary.addDefinition(cuvant3,"ro",definition3);
        for(Word word2 : dictionary.getDictionaries().get("ro")){
            if(word2.getWord().equals(cuvant3)){

                for(Definition definition1 : word2.getDefinitions()){

                    System.out.println(definition1.getDict());
                }
            }
        }

        System.out.println();
        System.out.println("RemoveDefinition caz cand definitia nu exista : ");
        String dictionar1 = "Dictionar explicativ";
        dictionary.removeDefinition(cuvant3,"ro",dictionar1);
        for(Word word2 : dictionary.getDictionaries().get("ro")){
            if(word2.getWord().equals(cuvant3)){

                for(Definition definition1 : word2.getDefinitions()){

                    System.out.println(definition1.getDict());
                }
            }
        }

        System.out.println();
        System.out.println("RemoveDefinition caz cand definitia s-a sters : ");
        String dictionar2 = "Dicționar universal";
        dictionary.removeDefinition(cuvant3,"ro",dictionar2);
        for(Word word2 : dictionary.getDictionaries().get("ro")){
            if(word2.getWord().equals(cuvant3)){

                for(Definition definition1 : word2.getDefinitions()){

                    System.out.println(definition1.getDict());
                }
            }
        }

        System.out.println();
        System.out.println("TranslateWord caz cand cuvantul nu are traducere : ");
        String cuvant4 = "Aici";
        String rs1 = dictionary.translateWord(cuvant4,"ro","fr");
        System.out.println(rs1);

        System.out.println();
        System.out.println("TranslateWord caz cand cuvantul are traducere : ");
        String cuvant44 = "pisică";
        String rs2 = dictionary.translateWord(cuvant44,"ro","fr");
        System.out.println(rs2);

        System.out.println();
        System.out.println("TranslateSentence : ");
        String propozitia1 = "pisică merge pisică";
        String rs3 = dictionary.translateSentence(propozitia1,"ro","fr");
        System.out.println(rs3);

        System.out.println();
        System.out.println("TranslateSentence : ");
        String propozitia2 = "Acolo este o pisică";
        String rs4 = dictionary.translateSentence(propozitia2,"ro","fr");
        System.out.println(rs4);

        System.out.println();
        System.out.println("TranslateSentences : ");
        ArrayList<String> rs5 = dictionary.translateSentences(propozitia1,"ro","fr");
        System.out.println(rs5);

        System.out.println();
        System.out.println("TranslateSentences : ");
        ArrayList<String> rs6 = dictionary.translateSentences(propozitia2,"ro","fr");
        System.out.println(rs6);

        System.out.println();
        System.out.println("GetDefinitionsForWord : ");
        String cuvant5 = "câine";
        ArrayList<Definition> rs7 = dictionary.getDefinitionsForWord(cuvant5,"ro");
        for(Definition definition1 : rs7){
            System.out.println(definition1.getYear());
        }

        System.out.println();
        System.out.println("GetDefinitionsForWord : ");
        String cuvant55 = "pisică";
        ArrayList<Definition> rs8 = dictionary.getDefinitionsForWord(cuvant55,"ro");
        for(Definition definition1 : rs8){
            System.out.println(definition1.getYear());
        }

        dictionary.exportDictionary("ro");

    }
}
