package stockNews;

import java.io.Serializable;
import java.util.List;

public class Stock implements Serializable {
    private String name;
    private List<String> newsKeywords;


    public Stock(String name, List<String> newsKeywords) {
        setName(name);
        setNewsKeywords(newsKeywords);
    }


    public List<String> getNewsKeywords() {
        return newsKeywords;
    }

    public void setNewsKeywords(List<String> newsKeywords) throws InvalidStockException {
        if (newsKeywords.size() == 0) {
            throw new InvalidStockException("Stocks must have at least one news keyword.");
        }
        this.newsKeywords = newsKeywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidStockException {
        if (name.length() == 0) {
            throw new InvalidStockException("Stocks must have a non empty name.");
        }
        this.name = name;
    }
}
