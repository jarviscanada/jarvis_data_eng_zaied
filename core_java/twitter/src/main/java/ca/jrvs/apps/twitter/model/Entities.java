package ca.jrvs.apps.twitter.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hashtags",
    "usermentions",
})

public class Entities {

  @JsonProperty("hashtags")
  private List<Hashtag> hashtags;

  @JsonProperty("usermentions")
  private List<UserMention> usermentions;

  @JsonProperty("hashtags")
  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  @JsonProperty("hashtags")
  public void setHashtags(List<Hashtag> hashtags) {
    this.hashtags = hashtags;
  }

  @JsonProperty("usermentions")
  public List<UserMention> getUsermentions() {
    return usermentions;
  }

  @JsonProperty("usermentions")
  public void setUsermentions(List<UserMention> usermentions) {
    this.usermentions = usermentions;
  }
}
