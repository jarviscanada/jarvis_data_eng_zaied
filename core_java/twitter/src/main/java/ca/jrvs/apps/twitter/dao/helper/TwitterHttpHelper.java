package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient = HttpClientBuilder.create().build();;

  /**Constructor
   * Setup dependencies using secrets
   *
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret){
    consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
    consumer.setTokenWithSecret(accessToken,tokenSecret);
  }

  /**
   * Execute a HTTP Post call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpPost(URI uri) {
    HttpPost request = new HttpPost(uri);
    try {
      consumer.sign(request);
    } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
      e.printStackTrace();
    }
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    try {
      System.out.println(EntityUtils.toString((response.getEntity())));
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return response;
  }

  /**
   * Execute a HTTP Get call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpGet(URI uri) {
    HttpGet request = new HttpGet(uri);
    try {
      consumer.sign(request);
    } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
      e.printStackTrace();
    }
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    try {
      System.out.println(EntityUtils.toString((response.getEntity())));
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return response;
  }



  public static void main(String[] args) {

    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    String status = "today is good day";
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET, ACCESS_TOKEN,TOKEN_SECRET);
    //HttpResponse postResponse = twitterHttpHelper.httpPost(URI.create(
        //"https://api.twitter.com/1.1/statuses/update.json?status="+percentEscaper.escape(status)));
    HttpResponse getResponse = twitterHttpHelper.httpGet(URI.create(
        "https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672"));

  }


}
