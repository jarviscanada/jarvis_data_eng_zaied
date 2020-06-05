package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import ca.jrvs.apps.twitter.model.*;

public class TwitterDAOIntTest {

  private CrdDao dao;

  @Before
  public void setUp() throws Exception {

    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,
        ACCESS_TOKEN,TOKEN_SECRET);
    dao = new TwitterDAO(httpHelper);
  }

  @Test
  public void create() {

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

    Tweet resTweet = null;
    try {
      resTweet = (Tweet) dao.create(srcTweet);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }

    assertEquals(text, resTweet.getText());
    assertNotNull(resTweet.getCoordinates());
    assertEquals(2,resTweet.getCoordinates().getCoordinates().size());
    assertEquals(lat,resTweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lon,resTweet.getCoordinates().getCoordinates().get(1));
    assertTrue(htag.contains(resTweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void findById() {
    String actualID = "210462857140252672";
    Tweet resTweet = (Tweet) dao.findById(actualID);
    assertNotNull(resTweet);
    assertEquals(actualID, resTweet.getId_str());
  }

  @Test
  public void deleteById() {

    String text = "@someone sometext " + " " + System.currentTimeMillis();
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

    Tweet resTweet = null;
    try {
      resTweet = (Tweet) dao.create(srcTweet);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    Tweet resTweet2 = (Tweet) dao.deleteById(resTweet.getId_str());
    assertNotNull(resTweet2);
    assertEquals(resTweet.getId_str(), resTweet2.getId_str());
  }
}