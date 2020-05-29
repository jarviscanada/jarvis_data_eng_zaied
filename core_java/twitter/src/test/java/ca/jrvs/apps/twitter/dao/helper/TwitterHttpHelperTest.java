package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import com.google.gdata.util.common.base.PercentEscaper;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.junit.Test;

public class TwitterHttpHelperTest {

  String CONSUMER_KEY = System.getenv("consumerKey");
  String CONSUMER_SECRET = System.getenv("consumerSecret");
  String ACCESS_TOKEN = System.getenv("accessToken");
  String TOKEN_SECRET = System.getenv("tokenSecret");
  TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET, ACCESS_TOKEN,TOKEN_SECRET);

  @Test
  public void httpPost() {

    String status = "today is good day";
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    HttpResponse postResponse = twitterHttpHelper.httpPost(URI.create(
    "https://api.twitter.com/1.1/statuses/update.json?status="+percentEscaper.escape(status)));

  }

  @Test
  public void httpGet() {

    HttpResponse getResponse = twitterHttpHelper.httpGet(URI.create(
      "https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672"));
  }
}