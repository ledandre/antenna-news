package br.com.orangescript.antenna.news.core.publisher;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.rss.Channel;
import org.apache.commons.digester.rss.RSSDigester;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import br.com.orangescript.antenna.news.beans.News;
import br.com.orangescript.antenna.news.core.NewsBuilder;
import br.com.orangescript.antenna.news.core.Source;
import br.com.orangescript.restcube.core.RestCube;
import br.com.orangescript.restcube.exceptions.BadRequestException;
import br.com.orangescript.restcube.request.ContentType;

public class PublisherService implements Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherService.class);

    @Override
    public List<News> getNews(List<Source> sources) throws IllegalStateException, BadRequestException, IOException {
        List<News> news = new ArrayList<News>();

        for (Source source : sources) {
            news.addAll(doRequest(source.getLink()));
        }

        return news;
    }

    private List<News> doRequest(String rssUrl) throws BadRequestException, IllegalStateException, IOException {
        LOGGER.info("Getting news from {}", rssUrl);
        HttpResponse newsXML = 
                new RestCube()
                    .to(rssUrl)
                    .as(ContentType.CONTENT_TYPE_FORM_URLENCODED)
                    .get();

        return parseXML(newsXML.getEntity().getContent());
    }

    private List<News> parseXML(InputStream weatherForecastXML) {
        List<News> forecastList = new ArrayList<News>();

        RSSDigester rssDigester = new RSSDigester();

        try {
            Channel rssChannel = (Channel) rssDigester.parse(weatherForecastXML);

            return NewsBuilder.build(rssChannel);

        } catch (IOException | SAXException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }

        return forecastList;
    }
}