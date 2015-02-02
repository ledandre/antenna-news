package br.com.orangescript.antenna.news.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.digester.rss.Channel;
import org.apache.commons.digester.rss.Item;

import br.com.orangescript.antenna.news.beans.News;

public class NewsBuilder {

    private static final Pattern IMAGE_LINK_PATTERN = Pattern.compile("(http://)(.)+(.gif|.jpeg|.jpg|.png)");

    public static List<News> build(Channel rssChannel) {
        List<News> newsList = new ArrayList<News>();

        for (Item rssItem : rssChannel.getItems()) {
            News news = build(rssItem);

            if (news != null) {
                news.setSource(rssChannel.getTitle());
                newsList.add(news);
            }
        }

        return newsList;
    }

    private static News build(Item rssItem) {
        if (rssItem == null || rssItem.getTitle() == null)
            return null;

        News news = new News();
        news.setHeadLine(rssItem.getTitle());

        buildImageLink(rssItem.getDescription(), news);

        return news;
    }

    private static void buildImageLink(String rssItemDescription, News news) {
        Matcher matcher = IMAGE_LINK_PATTERN.matcher(rssItemDescription);
        if (matcher.find())
            news.setImage(matcher.group());
    }
}
