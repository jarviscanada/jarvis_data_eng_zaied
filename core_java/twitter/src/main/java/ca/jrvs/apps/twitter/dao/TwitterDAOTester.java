package ca.jrvs.apps.twitter.dao;

import static ca.jrvs.apps.twitter.dao.TwitterDAO.toJson;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import ca.jrvs.apps.twitter.model.*;

public class TwitterDAOTester {

  public static void main(String[] args) throws JsonProcessingException {
    TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(System.getenv("consumerKey"),System.getenv("consumerSecret")
        ,System.getenv("accessToken"), System.getenv("tokenSecret"));
    TwitterDAO twitterDAO = new TwitterDAO(twitterHttpHelper);
    Tweet srcTweet = new Tweet();
    srcTweet.setText("hello repeat");
    Coordinates coordinates = new Coordinates();
    List<Double> l = new ArrayList();
    l.add(50.0);
    l.add(50.0);
    coordinates.setCoordinates(l);
    srcTweet.setCoordinates(coordinates);
    Tweet tweet = twitterDAO.create(srcTweet);
    //Tweet tweet = twitterDAO.findById("210462857140252672");
    System.out.println(TwitterDAO.toJson(tweet,true,false));
  }

}
