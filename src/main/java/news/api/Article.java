
package news.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The type Article.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "source",
    "author",
    "title",
    "description",
    "url",
    "urlToImage",
    "publishedAt",
    "content"
})
public class Article {

    @JsonProperty("source")
    private Source source;
    @JsonProperty("author")
    private Object author;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("url")
    private String url;
    @JsonProperty("urlToImage")
    private String urlToImage;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("content")
    private String content;

    /**
     * Instantiates a new Article.
     * I know this constructor is massive but this is nescesary to convert the json to a POJO
     *
     * @param source      the source
     * @param author      the author
     * @param title       the title
     * @param description the description
     * @param url         the url
     * @param urlToImage  the url to the ascociated image
     * @param publishedAt the published at
     * @param content     the content
     */
    //CHECKSTYLE:OFF
    public Article(Source source, Object author, String title, String description,
                   String url, String urlToImage, String publishedAt, String content) {
     //CHECKSTYLE:ON

        super();
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    /**
     * Sets source.
     *
     * @param source the source
     */
    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    @JsonProperty("author")
    public Object getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    @JsonProperty("author")
    public void setAuthor(Object author) {
        this.author = author;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets url to image.
     *
     * @return the url to image
     */
    @JsonProperty("urlToImage")
    public String getUrlToImage() {
        return urlToImage;
    }

    /**
     * Sets url to image.
     *
     * @param urlToImage the url to image
     */
    @JsonProperty("urlToImage")
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    /**
     * Gets published at.
     *
     * @return the published at
     */
    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets published at.
     *
     * @param publishedAt the published at
     */
    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

}
