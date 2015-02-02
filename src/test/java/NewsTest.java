import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.com.orangescript.antenna.news.beans.News;
import br.com.orangescript.antenna.news.core.Source;
import br.com.orangescript.antenna.news.core.publisher.Publisher;
import br.com.orangescript.antenna.news.core.publisher.PublisherService;
import br.com.orangescript.restcube.exceptions.BadRequestException;


public class NewsTest {

	public static void main(String[] args) throws IllegalStateException, BadRequestException, IOException {
		Publisher publisher = new PublisherService();
		List<News> newsList = publisher.getNews(Arrays.asList(Source.UOL));

		for (News news : newsList) {
			System.out.println(">> " + news.getHeadLine());
			System.out.println(news.getImage());
			System.out.println(news.getSource());
			System.out.println("=================================================\n");
		}
	}
}