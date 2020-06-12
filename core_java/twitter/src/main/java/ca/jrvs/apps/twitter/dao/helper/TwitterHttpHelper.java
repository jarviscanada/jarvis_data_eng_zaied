package ca.jrvs.apps.twitter.dao.helper;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;

@org.springframework.stereotype.Component
public class TwitterHttpHelper implements HttpHelper {

    /**
     * Dependencies are specified as private member variables
     */
    private OAuthConsumer consumer;
    private HttpClient httpClient = HttpClientBuilder.create().build();
    ;
    protected final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);

    /**
     * Constructor
     * Setup dependencies using secrets
     *
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param tokenSecret
     */


    @Autowired
    public TwitterHttpHelper(@Value("${consumerKey}") String consumerKey, @Value("${consumerSecret}")
            String consumerSecret, @Value("${accessToken}") String accessToken, @Value("${tokenSecret}") String tokenSecret) {
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
    }

    //public TwitterHttpHelper(){
    //consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
    //consumer.setTokenWithSecret(accessToken,tokenSecret);
    //}

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
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException", ex);
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("could not get post response", ex);
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
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException", ex);
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException("could not get post response", ex);
        }
        return response;
    }

}
