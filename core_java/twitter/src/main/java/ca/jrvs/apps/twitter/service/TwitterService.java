package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.io.IOException;

public class TwitterService implements Service {

  private CrdDao dao;

  public TwitterService(CrdDao dao)
  {
    this.dao = dao;
  }

  public Tweet postTweet(Tweet tweet)
  {
    validatePostTweet(tweet);
    Tweet validatedTweet = null;
    try {
      validatedTweet = (Tweet) dao.create(tweet);
    } catch (IOException e) {
      throw new RuntimeException("Tweet could not be validated", e);
    }
    return validatedTweet;
  }

  public Tweet showTweet(String id)
  {
    validateId(id);
    Tweet validatedTweet =  validatedTweet = (Tweet) dao.findById(id);
    return validatedTweet;
  }

  public Tweet deleteTweet(String id)
  {
    validateId(id);
    Tweet validatedTweet  = validatedTweet = (Tweet) dao.deleteById(id);
    return validatedTweet;
  }

  //utility functions
  private void validatePostTweet(Tweet tweet)
  {
    String text = tweet.getText();
    Double latitude = tweet.getCoordinates().getCoordinates().get(0);
    Double longitude = tweet.getCoordinates().getCoordinates().get(1);

    if(text.length() > 140)
    {
      throw new RuntimeException("Tweet text size too large (need to be <= 140)");
    }
    if(latitude < -90.0d || latitude > 90.0d)
    {
      throw new RuntimeException("latitude out of bound (need to be >= -90.0 and <= 90.0)");
    }
    if(longitude < -180.0d || longitude > 180.0d)
    {
      throw new RuntimeException("longitude out of bound (need to be >= -180.0 and <= 180.0)");
    }
  }

  private void validateId(String id)
  {
    if(!id.matches("\\d+"))
    {
      throw new RuntimeException("id contains non digit characters)");
    }
    compareMinMax(id);
  }

  private void compareMinMax(String id)
  {
    String sign = ((Character)(id.charAt(0))).toString();
    String lMin = "9223372036854775808";
    String lMax = "9223372036854775807";
    int length = id.length();
    if(length > 19)
    {
      throw new RuntimeException("id out of bound");
    }
    if(sign.equals("-"))
    {
      compareMinMaxHelper(id,lMin);
    }
    else
    {
      compareMinMaxHelper(id,lMax);
    }
  }

  private void compareMinMaxHelper(String id, String lMin)
  {
    for(int i=0;i<id.length();i++)
    {
      int idDig = id.charAt(i)-'0';
      int lminDig = lMin.charAt(i) - '0';
      if(idDig<lminDig)
      {
        return;
      }
      if(idDig>lminDig)
      {
        throw new RuntimeException("id out of specified range");
      }
    }
  }
}
