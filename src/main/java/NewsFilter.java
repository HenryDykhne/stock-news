import news.pojo.Article;
import news.pojo.NewsJSON;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public final class NewsFilter {

    private NewsFilter() {
        //not to be instantiated
    }

    public static List<Article> filterNews(NewsJSON newsJSON, Collection<Blacklist> blacklists) {
        //filtering nonactive blacklists for convenience
        Collection<String> activeMegaList = new HashSet<>();
        for (Blacklist blacklist : blacklists) {
            if (blacklist.isActive()) {
                activeMegaList.addAll(Arrays.asList(blacklist.getRestrictedText()));
            }
        }

        //collecting all articles that do NOT contain any restricted words from the active blacklists
        List<Article> filteredArticles = new ArrayList<>();
        for (Article article : newsJSON.getArticles()) {
            filteredArticles.add(article);
            for (String restriction : activeMegaList) {
                if (article.getUrl().contains(restriction)) {
                    filteredArticles.remove(article);
                }
            }
        }

        return filteredArticles;
    }
}
