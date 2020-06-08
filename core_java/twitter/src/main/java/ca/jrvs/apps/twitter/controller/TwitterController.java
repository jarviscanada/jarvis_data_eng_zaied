package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.*;
import java.util.*;

public class TwitterController implements Controller {


  private Service service;

  public TwitterController(Service service) {
    this.service = service;
  }

  public Tweet postTweet(String[] args)
  {
    if(args.length!= 3)
    {
      throw new IllegalArgumentException("Usage: TwitterCLIApp post text latitude:longitude");
    }
    String text = args[1];
    String[] corrdinates = args[2].split(":");
    return service.postTweet(makeTweet(text,corrdinates));
  }

  public Tweet showTweet(String[] args)
  {
    if(args.length < 2 || args.length > 3)
    {
      throw new IllegalArgumentException("Usage: TwitterCLI delete [id1,id2,..]");
    }

    String tweet_id = args[1];
    String[] printFields = null;
    if(args.length == 3)
    {
      printFields = args[2].split(",");

    }
    return service.showTweet(tweet_id,printFields);
  }

  public List<Tweet> deleteTweet(String[] args)
  {
    if(args.length != 2)
    {
      throw new IllegalArgumentException("Usage: TwitterCLIApp show tweet_id [field1,fields2]");
    }
    String[] deleteTweetids = args[1].split(",");
    return service.deleteTweets(deleteTweetids);
  }

  //utility function
  protected Tweet makeTweet(String text, String[] coordinates)
  {
    Double latitude = Double.parseDouble(coordinates[0]);
    Double longitude = Double.parseDouble(coordinates[1]);
    Tweet srcTweet = new Tweet() ;
    srcTweet.setText(text);
    List<Double> tweetCoordinate = new ArrayList<Double>();
    tweetCoordinate.add(latitude);
    tweetCoordinate.add(longitude);
    Coordinates currentCoordinate = new Coordinates();
    currentCoordinate.setCoordinates(tweetCoordinate);
    srcTweet.setCoordinates(currentCoordinate);
    return srcTweet;
  }
}
