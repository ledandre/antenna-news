package br.com.orangescript.antenna.news.beans;

public enum Source {

    UOL("http://rss.uol.com.br/feed/noticias.xml"),
    UOL_ESPORTE("http://esporte.uol.com.br/ultimas/index.xml");

    private String link;

    private Source(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }
}
