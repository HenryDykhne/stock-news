package stockNews.news;

import stockNews.Blacklist;
import stockNews.news.newsPojo.Article;
import stockNews.news.newsPojo.NewsJSON;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;


public final class NewsFilter {

    private NewsFilter() {
        //not to be instantiated
    }

    public static Map<Article, Boolean> filterNews(NewsJSON newsJSON, Collection<Blacklist> blacklists) {
        //filtering nonactive blacklists for convenience
        Collection<String> activeMegaList = new HashSet<>();
        for (Blacklist blacklist : blacklists) {
            if (blacklist.isActive()) {
                activeMegaList.addAll(blacklist.getRestrictedText());
            }
        }

        //marking all articles that are trustworthy
        Map<Article, Boolean> filteredArticles = new HashMap<>();
        for (Article article : newsJSON.getArticles()) {
            filteredArticles.put(article, true);
            for (String restriction : activeMegaList) {
                if (article.getUrl().toLowerCase().contains(restriction.toLowerCase())) {
                    filteredArticles.replace(article, false);
                }
            }
        }

        return filteredArticles;
    }
}
