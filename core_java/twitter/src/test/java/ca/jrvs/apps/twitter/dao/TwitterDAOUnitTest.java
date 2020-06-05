package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDAOUnitTest {

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDAO dao;

  String jsoStr = "{\n"
      + "           \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\"\n}";


  @Test
  public void create() {

    String htag = "#abc";
    String text = "@someone sometext " + htag + " " + System.currentTimeMillis();
    Double lat = -1d;
    Double lon = 1d;

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);

    //building tweet to post
    Tweet srcTweet = new Tweet();
    srcTweet.setText(text);
    List<Double> coordinates = new ArrayList<Double>();
    coordinates.add(lat);
    coordinates.add(lon);
    Coordinates tweetCoordinates = new Coordinates();
    tweetCoordinates.setCoordinates(coordinates);
    srcTweet.setCoordinates(tweetCoordinates);

    //mock parse response body
    ObjectMapper m = new ObjectMapper();
    m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    Tweet expectedTweet = null;
    try {
      expectedTweet = (Tweet) m.readValue(jsoStr,Tweet.class);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }

    TwitterDAO spyDAO = Mockito.spy(dao);
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());

    assertNotNull(spyDAO.create(srcTweet));
    assertNotNull(spyDAO.create(srcTweet).getCreated_at());
  }

  @Test
  public void findById() {

    //mock parse response body
    ObjectMapper m = new ObjectMapper();
    m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    Tweet expectedTweet = null;
    try {
      expectedTweet = (Tweet) m.readValue(jsoStr,Tweet.class);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }

    TwitterDAO spyDAO = Mockito.spy(dao);
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());

    assertNotNull(spyDAO.findById(anyString()));
    assertNotNull(spyDAO.findById(anyString()).getCreated_at());
  }

  @Test
  public void deleteById() {

    //mock parse response body
    ObjectMapper m = new ObjectMapper();
    m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    Tweet expectedTweet = null;
    try {
      expectedTweet = (Tweet) m.readValue(jsoStr,Tweet.class);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }

    TwitterDAO spyDAO = Mockito.spy(dao);
    doReturn(expectedTweet).when(spyDAO).parseResponseBody(any(),anyInt());

    assertNotNull(spyDAO.deleteById(anyString()));
    assertNotNull(spyDAO.deleteById(anyString()).getCreated_at());
  }
}