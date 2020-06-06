package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  TwitterDAO mockDAO;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() {

    TwitterService spyService = Mockito.spy(service);
    when(mockDAO.create(any())).thenReturn(new Tweet());
    doNothing().when(spyService).validatePostTweet(any());
    assertNotNull(spyService.postTweet(any()));
  }

  @Test
  public void showTweet() {

    TwitterService spyService = Mockito.spy(service);
    when(mockDAO.findById(anyString())).thenReturn(new Tweet());
    doNothing().when(spyService).validateId(any());
    assertNotNull(spyService.showTweet(anyString(),any()));
  }

  @Test
  public void deleteTweets() {

    TwitterService spyService = Mockito.spy(service);
    when(mockDAO.deleteById(anyString())).thenReturn(new Tweet());
    doNothing().when(spyService).validateId(anyString());
    String[] testArr = new String[1];
    testArr[0] = "11111";
    assertNotNull(spyService.deleteTweets(testArr));
  }
}