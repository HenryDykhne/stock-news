
package news.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The type Source.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name"
})
public class Source {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

//    /**
//     *
//     * @param name source name
//     * @param id source id
//     */
//    public Source(String id, String name) {
//        super();
//        this.id = id;
//        this.name = name;
//    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
