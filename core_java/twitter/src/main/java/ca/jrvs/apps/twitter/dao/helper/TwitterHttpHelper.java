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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient = HttpClientBuilder.create().build();
  protected final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);

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
      logger.error(e.getMessage(),e);
      throw new RuntimeException("OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException",e)

    }
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
    } catch (IOException ioException) {
      logger.error(ioException.getMessage(),ioException);
      throw new RuntimeException("no response",ioException);
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
      logger.error(e.getMessage(),e);
      throw new RuntimeException("OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException",e)
    }
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
    } catch (IOException ioException) {
      logger.error(ioException.getMessage(),ioException);
      throw new RuntimeException("no response",ioException);
    }
    return response;
  }

}
