public class Word {
    private String word;
    private String word_en;
    private String noun;
    private String[] singular;
    private String[] plural;
    private Definition[] definitions;

    public Word(String word, String word_en, String noun, String[] singular, String[] plural, Definition[] definitions) {
        this.word = word;
        this.word_en = word_en;
        this.noun = noun;
        this.singular = singular;
        this.plural = plural;
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public String getWord_en() {
        return word_en;
    }

    public String getNoun() {
        return noun;
    }

    public String[] getSingular() {
        return singular;
    }

    public String[] getPlural() {
        return plural;
    }

    public Definition[] getDefinitions() {
        return definitions;
    }

    public void setDefinitions(Definition[] definitions) {
        this.definitions = definitions;
    }
}
