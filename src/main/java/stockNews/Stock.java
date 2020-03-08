package stockNews;

import java.io.Serializable;
import java.util.List;

public class Stock implements Serializable {
    private String name;
    private List<String> newsKeywords;


    public Stock(String name, List<String> newsKeywords) {
        this.name = name;
        this.newsKeywords = newsKeywords;
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
