package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import org.junit.Before;
import org.junit.Test;
import ca.jrvs.apps.twitter.*;

public class TwitterServiceIntTest {

  private CrdDao dao;
  private Service service;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,
        ACCESS_TOKEN,TOKEN_SECRET);
    dao = new TwitterDAO(httpHelper);
    service = new TwitterService(dao);
  }

  @Test
  public void postTweet() {
    String htag = "#abc";
    String text = "@someone sometext " + htag + " " + System.currentTimeMillis();
    Double lat = -1d;
    Double lon = 1d;
    Tweet srcTweet = new Tweet();
    srcTweet.setText(text);
    Coordinates coordinates = new Coordinates();
    List<Double> c = new ArrayList<Double>();
    c.add(lat);
    c.add(lon);
    coordinates.setCoordinates(c);
    srcTweet.setCoordinates(coordinates);
    assertNotNull(service.postTweet(srcTweet));
  }

  @Test
  public void showTweet() {
    String id="210462857140252672";
    String[] printFields = new String[1];
    printFields[0] = "created_at";
    assertNotNull(service.showTweet(id,printFields));
  }

  @Test
  public void deleteTweets() {
    String htag = "#abc";
    String text = "@someone sometext " + htag + " " + System.currentTimeMillis();
    Double lat = -1d;
    Double lon = 1d;
    Tweet srcTweet = new Tweet();
    srcTweet.setText(text);
    Coordinates coordinates = new Coordinates();
    List<Double> c = new ArrayList<Double>();
    c.add(lat);
    c.add(lon);
    coordinates.setCoordinates(c);
    srcTweet.setCoordinates(coordinates);
    Tweet res = service.postTweet(srcTweet);
    String[] deleteIds=new String[1];
    deleteIds[0] = res.getId_str(); //valid id to delete, first create
    assertNotNull(service.deleteTweets(deleteIds));
  }
}