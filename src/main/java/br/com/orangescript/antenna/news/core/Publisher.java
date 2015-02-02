package br.com.orangescript.antenna.news.core;

import java.util.List;

import javax.xml.transform.Source;

import br.com.orangescript.antenna.news.beans.News;

public interface Publisher {
    public List<News> getNews(List<Source> sources);
}
