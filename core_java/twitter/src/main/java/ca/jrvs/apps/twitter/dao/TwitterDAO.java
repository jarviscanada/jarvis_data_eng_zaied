package ca.jrvs.apps.twitter.dao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import javax.validation.constraints.Null;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.lang.Object.*;
import ca.jrvs.apps.twitter.model.*;

public class TwitterDAO implements CrdDao<Tweet, String> {

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy.json";
  private static final PercentEscaper escaper = new PercentEscaper("",false);

  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  public TwitterDAO(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {

    URI uri = null;

    try {

      uri = getPostUri(entity);
    }catch (NullPointerException e)
    {
     e.printStackTrace();
    }

    HttpResponse httpResponse = httpHelper.httpPost(uri);
    Tweet tweet = this.parseResponseBody(httpResponse,HTTP_OK);
    return tweet;
  }
  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {

    URI uri = null;

    try{
      uri = getShowUri(s);
    }catch (NullPointerException e)
    {
      e.printStackTrace();
    }

    HttpResponse httpResponse = httpHelper.httpGet(uri);
    Tweet tweet = this.parseResponseBody(httpResponse,HTTP_OK);
    return tweet;
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    URI uri = null;
    try{
      uri = getDeleteUri(s);
    }catch (NullPointerException e)
    {
      e.printStackTrace();
    }

    HttpResponse httpResponse = httpHelper.httpPost(uri);
    Tweet tweet = parseResponseBody(httpResponse,HTTP_OK);
    return tweet;
  }

  //utility functions

  private URI getShowUri(String s)
  {
    URI uri = URI.create(API_BASE_URI+SHOW_PATH+QUERY_SYM+"id"+EQUAL+s);
    return uri;
  }

  private URI getDeleteUri(String s)
  {
    URI uri = URI.create(API_BASE_URI+DELETE_PATH+QUERY_SYM+"id"+EQUAL+s);
    return uri;
  }

  private URI getPostUri(Tweet entity)
  {
    URI uri = URI.create(API_BASE_URI+POST_PATH+QUERY_SYM+"status"+EQUAL+escaper.escape(entity.getText())+
        AMPERSAND+"long"+ EQUAL+ escaper.escape(String.valueOf(entity.getCoordinates().getCoordinates().get(0))) +AMPERSAND+"lat"+
        EQUAL+ escaper.escape(String.valueOf(entity.getCoordinates().getCoordinates().get(1))));
    return uri;
  }

  private Tweet parseResponseBody(HttpResponse httpResponse, Integer HTTP_OK)
  {
    Tweet tweet = null;

    int status = httpResponse.getStatusLine().getStatusCode();
    if(status != HTTP_OK)
    {
      throw new RuntimeException("Incorrect Status Code"+status);
    }
    if(httpResponse.getEntity() == null)
    {
      throw new RuntimeException("No response");
    }

    String str;
    try {
      str = EntityUtils.toString(httpResponse.getEntity());
      //System.out.println("Response:: "+str);
    } catch (IOException ioException) {
      throw new RuntimeException("Could not convert to string");
    }

    ObjectMapper m = new ObjectMapper();
    m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    try {
      tweet = (Tweet) m.readValue(str, Tweet.class);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return tweet;
  }
}
