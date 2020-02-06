import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;

import java.util.List;

public class Stock {
    private String name;
    private String acronym;
    private List<String> newsKeywords;

    public Stock(String name, String acronym, List<String> newsKeywords) {
        this.name = name;
        this.acronym = acronym;
        this.newsKeywords = newsKeywords;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public List<String> getNewsKeywords() {
        return newsKeywords;
    }

    public void setNewsKeywords(List<String> newsKeywords) {
        this.newsKeywords = newsKeywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
