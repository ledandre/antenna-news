package br.com.orangescript.antenna.news.core.publisher;

import java.io.IOException;
import java.util.List;

import br.com.orangescript.antenna.news.beans.News;
import br.com.orangescript.antenna.news.core.Source;
import br.com.orangescript.restcube.exceptions.BadRequestException;

public interface Publisher {
    public List<News> getNews(List<Source> sources) throws IllegalStateException, BadRequestException, IOException;
}
