package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.util.Collection;
import java.util.Set;
import jdk.nashorn.api.scripting.JSObject;

public class Tweet {

  ObjectMapper mapper = new ObjectMapper();
  com.fasterxml.jackson.databind.node.ObjectNode tweet = mapper.createObjectNode();
  com.fasterxml.jackson.databind.node.ObjectNode res = tweet.put("id",1);
  String json;

  {
    try {
      json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tweet);
      System.out.println(json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Tweet tweet = new Tweet();
  }
}
