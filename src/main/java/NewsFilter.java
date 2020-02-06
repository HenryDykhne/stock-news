import news.pojo.Article;
import news.pojo.NewsJSON;

import java.io.IOException;
import java.util.Collection;

/**
 * The type News filter.
 */
public final class NewsFilter {

    private NewsFilter() {
        //not to be instantiated
    }

    /**
     * filter news article [ ].
     *
     * @param newsJSON   the news json
     * @param blacklists the blacklists
     * @return the article [ ]
     */
    public static Article[] filterNews(NewsJSON newsJSON, Collection<Blacklist> blacklists) {
//        blacklists
//        List<Article> result = newsJSON.getArticles().stream() // convert list to stream
//                .filter(article -> !article.getUrl().equalsIgnoreCase(blacklists.))
//                .collect(Collectors.toList());

        return null;
    }
}
