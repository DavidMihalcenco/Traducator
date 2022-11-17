public class Definition {
    private String dict;
    private String dictType;
    private Integer year;
    private String[] text;

    public Definition(String dict, String dictType, Integer year, String[] text) {
        this.dict = dict;
        this.dictType = dictType;
        this.year = year;
        this.text = text;
    }

    public String getDict() {
        return dict;
    }

    public String getDictType() {
        return dictType;
    }

    public Integer getYear() {
        return year;
    }

    public String[] getText() {
        return text;
    }

}
