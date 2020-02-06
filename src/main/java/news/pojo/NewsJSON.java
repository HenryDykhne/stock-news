
package news.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The type News json.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "totalResults",
    "articles"
})
public class NewsJSON {



    @JsonProperty("status")
    private String status;
    @JsonProperty("totalResults")
    private Integer totalResults;
    @JsonProperty("articles")
    private List<Article> articles;

//    /**
//     * Instantiates a new News json.
//     *
//     * @param status       the status
//     * @param totalResults the total results
//     * @param articles     the articles
//     */
//    public NewsJSON(String status, Integer totalResults, List<Article> articles) {
//        super();
//        this.status = status;
//        this.totalResults = totalResults;
//        this.articles = articles;
//    }

    /**
     * Gets status.
     *
     * @return the status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets total results.
     *
     * @return the total results
     */
    @JsonProperty("totalResults")
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * Sets total results.
     *
     * @param totalResults the total results
     */
    @JsonProperty("totalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets articles.
     *
     * @return the articles
     */
    @JsonProperty("articles")
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * Sets articles.
     *
     * @param articles the articles
     */
    @JsonProperty("articles")
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
