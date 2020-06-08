package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.configuration.IMockitoConfiguration;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  private Service mockService;

  @InjectMocks
  private TwitterController mockController;

  @Test
  public void postTweet() {

    TwitterController spyController = Mockito.spy(mockController);
    doReturn(new Tweet()).when(spyController).makeTweet(anyString(),any(String[].class));
    when(mockService.postTweet(any())).thenReturn(new Tweet());
    String[] args = new String[3];
    args[1] = "hello";
    args[2] = "-1:1";
    assertNotNull(spyController.postTweet(args));
  }

  @Test
  public void showTweet() {
    when(mockService.showTweet(anyString(),any(String[].class))).thenReturn(new Tweet());
    String[] args = new String[3];
    args[1] = "hello";
    args[2] = "-1:1";
    assertNotNull(mockController.showTweet(args));
  }

  @Test
  public void deleteTweet() {
    String[] args = new String[2];
    args[1] = "1123";
    when(mockService.deleteTweets(any(String[].class))).thenReturn(new ArrayList<Tweet>());
    assertNotNull(mockController.deleteTweet(args));
  }
}