import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Dictionary {
    private Map<String, Word[]> dictionaries;

    public Dictionary(Map<String, Word[]> dictionaries) {
        this.dictionaries = dictionaries;
    }

    public Map<String, Word[]> getDictionaries() {
        return dictionaries;
    }

    boolean addWord(Word word, String language) {

        Word[] words = dictionaries.get(language); // salvez cuvintele din dictionarul cerut
        ArrayList<Word> arraylist = new ArrayList<>(Arrays.asList(words)); //transform din list in array list

        for (Word dictionary1 : words) { // controlez daca exista deja cuvantul in dictionar
            if (word.getWord().equals(dictionary1.getWord())) {
                System.out.println("Cuvantul deja exista");
                return false;
            }
        }

        arraylist.add(word); // adaug cuvantul

        Word[] words1 = new Word[arraylist.size()]; // transform din arraylist in list
        words1 = arraylist.toArray(words1);

        dictionaries.put(language, words1); // inlocuiesc cu noua lista

        return true;
    }

    boolean removeWord(String word, String language) {

        Word[] words = dictionaries.get(language);

        ArrayList<Word> arraylist = new ArrayList<>(Arrays.asList(words));

        for (Word word1 : words) { // controlez daca exista deja cuvantul in dictionar
            if (word1.getWord().equals(word)) {
                arraylist.remove(word1);

                Word[] words1 = new Word[arraylist.size()]; // transform din arraylist in list
                words1 = arraylist.toArray(words1);

                dictionaries.put(language, words1); // inlocuiesc cu noua lista
                return true;
            }
        }
        System.out.println("Cuvantul nu exista");
        return false;
    }

    boolean addDefinition(String word, String language, Definition definition) {

        Word[] words = dictionaries.get(language);

        for (Word word1 : words) { // controlez daca definitia deja exista
            if (word1.getWord().equals(word)) {
                for (Definition definition1 : word1.getDefinitions()) {
                    if (definition1.getDict().equals(definition.getDict())) {
                        System.out.println("Definitia exista");
                        return false;
                    }
                }
            }

        }

        for (Word word1 : words) { // daca nu exista definitia o adaug
            if (word1.getWord().equals(word)) {
                ArrayList<Definition> arraylist1 = new ArrayList<>((Arrays.asList(word1.getDefinitions())));
                arraylist1.add(definition); // adaug definitia

                Definition[] definitions1 = new Definition[arraylist1.size()]; // transform din arraylist in list
                definitions1 = arraylist1.toArray(definitions1);

                word1.setDefinitions(definitions1);

                dictionaries.put(language, words); // inlocuiesc cu noua lista

            }
        }
        return true;
    }


    boolean removeDefinition(String word, String language, String dictionary){
        Word[] words = dictionaries.get(language);

        for(Word word1 : words) {
            if(word1.getWord().equals(word)){
                for(Definition definition1 : word1.getDefinitions()){
                    if(definition1.getDict().equals(dictionary)){
                        ArrayList<Definition> arraylist1 = new ArrayList<>((Arrays.asList(word1.getDefinitions())));
                        //salvez definitiile in arraylist1 pentru a putea folosi remove
                        arraylist1.remove(definition1);

                        Definition[] definitions1 = new Definition[arraylist1.size()]; // transform din arraylist
                        // in list
                        definitions1 = arraylist1.toArray(definitions1);

                        word1.setDefinitions(definitions1);

                        dictionaries.put(language,words); // inlocuiesc cu noua lista
                        return true;
                    }
                }
            }
        }
        System.out.println("Definitia nu exista");
        return false;
    }

    String translateWord(String word, String fromLanguage, String toLanguage){
        Word[] words1 = dictionaries.get(fromLanguage);
        Word[] words = dictionaries.get(toLanguage);

        for(Word word1 :words1){
            if(word1.getWord().equals(word)){
                for(Word word2 : words){
                    if(word1.getWord_en().equals(word2.getWord_en())){
                        return word2.getWord() + " "; // + " " pentru a avea spatii intre cuvinte
                    }
                }
            }
        }
        return word + " "; // daca nu are traducere intorc cuvantul fara traducere
    }

    String translateSentence(String sentence, String fromLanguage, String toLanguage){

        String last1 = "";
        String[] words3 = sentence.split(" ");

        for (String word : words3){
            String last = translateWord(word,fromLanguage,toLanguage); // traduc fiecare cuvant din propozitia data
            last1 = last1 + last; // fiecare cuvant tradus il adaug la propozitia last1
        }

        return last1;
    }

    ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage){

        Word[] words = dictionaries.get(toLanguage);
        ArrayList<String> rs = new ArrayList<>();

        String dicts = "synonyms"; // dicts este dictionarul de sinonime, adica voi compara denumirea
        // definitiei cu dicts
        int i;
        int nr = 0;

        String v1 = translateSentence(sentence,fromLanguage,toLanguage); /*traduc propozitia pentru a cauta direct in
        dictionarul in care trebuie sa traduc */

        String[] words2 = v1.split(" ");

        for(i = 0 ; i< words2.length ; i++){
            for(Word word1 : words){
                if(word1.getWord().equals(words2[i])){
                    for(Definition definition : word1.getDefinitions()){
                        if(definition.getDictType().equals(dicts)){
                            for(String synonyms : definition.getText()){
                                words2[i] = synonyms;
                                String propozition = String.join(" ",words2);
                                rs.add(propozition); // adaug propozitia cu sinonim in lista
                                nr++;
                                if(nr == 3){
                                    return rs; // fac de 3 ori pentru a avea 3 propozitii
                                }
                            }
                        }
                    }
                }
            }
        }
        return rs;
    }

    ArrayList<Definition> getDefinitionsForWord(String word, String language){

        Word[] words = dictionaries.get(language); // extrag dictionarul din care trebuie sa i-au definitiile
        ArrayList<Definition> rs = new ArrayList<>();

        for(Word word1 : words){
            if(word1.getWord().equals(word)){
                for(Definition definition : word1.getDefinitions()){
                    rs.add(definition); // adaug definitia in lista de definitii
                }
            }
        }
        rs.sort(Comparator.comparing(Definition::getYear)); // sortez dupa an definitiile
        return rs;
    }

    void exportDictionary(String language) throws IOException {

        GsonBuilder gsonBuilder = new GsonBuilder(); // folosesc GsonBuilder pentru a infrumuse
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        Word[] words = dictionaries.get(language);

        for(Word word : words){

            ArrayList<Definition> definitions = getDefinitionsForWord(word.getWord(), language); // sortez definitiile
            Definition[] definitions1 = new Definition[definitions.size()]; // transform din arraylist in list
            definitions1 = definitions.toArray(definitions1);
            word.setDefinitions(definitions1); // setez definitiile noi

            dictionaries.put(language, words); // schim cuvintele din dictionar cu cele noi sortate pentru definitie
        }

        Writer fileWriter = new FileWriter("C:\\Users\\david.mihalcenco\\IdeaProjects\\" +
                "tema2.1\\src\\teste\\exfinal.json");

        Word[] words1 = dictionaries.get(language); //extrag cuvintele din dictionarul care a fost cerut

        ArrayList<Word> arraylist1 = new ArrayList<>(Arrays.asList(words1)); // creez un arraylist pentru cuvinte
        arraylist1.sort(Comparator.comparing(Word::getWord)); // sortez dupa nume
        gson.toJson(arraylist1,fileWriter); // scriu in fisier

        fileWriter.close();

    }
}
