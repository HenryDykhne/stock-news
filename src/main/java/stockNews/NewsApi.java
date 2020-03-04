package stockNews;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import stockNews.newsPojo.NewsJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.util.List;

public class NewsApi {
    private static final String API_KEY = "603e0c6a34604b9fb43fa35f6148167c";
    private static final int OK = 200;

    private URL buildURL(List<String> searchTerms) throws URISyntaxException, MalformedURLException {
        String search = StringUtils.join(searchTerms, " OR ");

        URIBuilder builder = new URIBuilder("https://newsapi.org/v2/everything");
        if (search.length() > 0) {
            builder.addParameter("q", search);
        }
        builder.addParameter("apiKey", API_KEY);
        return builder.build().toURL();
    }

    public String getNewsInfo(List<String> searchTerms) throws IOException, URISyntaxException {
        StringBuilder content = new StringBuilder();

        URL url = buildURL(searchTerms);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        int status = conn.getResponseCode();
        in.close();
        conn.disconnect();
        if (status == OK) {
            return content.toString();
        } else {
            return null;
        }
    }

    public NewsJSON mapNewsJSONToPoJo(String newsJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<NewsJSON> typeMap = new TypeReference<>() { };

        return mapper.readValue(newsJSON, typeMap);
    }
}
