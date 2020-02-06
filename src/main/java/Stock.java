

import java.util.List;

/**
 * The type Stock.
 */
public class Stock {
    private String name;
    private String acronym;
    private List<String> newsKeywords;

    /**
     * Instantiates a new Stock.
     *
     * @param name         the name
     * @param acronym      the acronym
     * @param newsKeywords the news keywords
     */
    public Stock(String name, String acronym, List<String> newsKeywords) {
        this.name = name;
        this.acronym = acronym;
        this.newsKeywords = newsKeywords;
    }

    /**
     * Gets acronym.
     *
     * @return the acronym
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Sets acronym.
     *
     * @param acronym the acronym
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    /**
     * Gets news keywords.
     *
     * @return the news keywords
     */
    public List<String> getNewsKeywords() {
        return newsKeywords;
    }

    /**
     * Sets news keywords.
     *
     * @param newsKeywords the news keywords
     */
    public void setNewsKeywords(List<String> newsKeywords) {
        this.newsKeywords = newsKeywords;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
