package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.model.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

    private CrdDao dao;
    protected final Logger logger = LoggerFactory.getLogger(TwitterDAO.class);

    @Autowired
    public TwitterService(CrdDao dao) {
        this.dao = dao;
    }

    public Tweet postTweet(Tweet tweet) {
        validatePostTweet(tweet);
        Tweet validatedTweet = null;
        try {
            validatedTweet = (Tweet) dao.create(tweet);
        } catch (IOException ex) {
            logger.error("Tweet could not be validated posTweet TwitterService", ex);
            throw new IllegalArgumentException("Tweet could not be validated", ex);
        }
        return validatedTweet;
    }

    public Tweet showTweet(String id, String[] printFields) {
        validateId(id);
        Tweet validatedTweet = (Tweet) dao.findById(id);
        return filterFields(printFields, validatedTweet);
    }

    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> deletedTweets = new ArrayList<Tweet>();
        int idNum = ids.length;
        for (int i = 0; i < idNum; i++) {
            validateId(ids[i]);
            deletedTweets.add((Tweet) dao.deleteById(ids[i]));
        }
        return deletedTweets;
    }

    //utility functions
    protected void validatePostTweet(Tweet tweet) {
        String text = tweet.getText();
        Double latitude = tweet.getCoordinates().getCoordinates().get(0);
        Double longitude = tweet.getCoordinates().getCoordinates().get(1);

        if (text.length() > 140) {
            throw new IllegalArgumentException("Tweet text size too large (need to be <= 140)");
        }
        if (latitude < -90.0d || latitude > 90.0d) {
            throw new IllegalArgumentException("latitude out of bound (need to be >= -90.0 and <= 90.0)");
        }
        if (longitude < -180.0d || longitude > 180.0d) {
            throw new IllegalArgumentException("longitude out of bound (need to be >= -180.0 and <= 180.0)");
        }
    }

    protected void validateId(String id) {
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("id contains non digit characters)");
        }
        compareMinMax(id);
    }

    private void compareMinMax(String id) {
        String sign = ((Character) (id.charAt(0))).toString();
        String lMin = "9223372036854775808";
        String lMax = "9223372036854775807";
        int length = id.length();
        if (length > 19) {
            throw new IllegalArgumentException("id out of bound");
        }
        if (sign.equals("-")) {
            compareMinMaxHelper(id, lMin);
        } else {
            compareMinMaxHelper(id, lMax);
        }
    }

    private void compareMinMaxHelper(String id, String lMin) {
        for (int i = 0; i < id.length(); i++) {
            int idDig = id.charAt(i) - '0';
            int lminDig = lMin.charAt(i) - '0';
            if (idDig < lminDig) {
                return;
            }
            if (idDig > lminDig) {
                throw new IllegalArgumentException("id out of specified range");
            }
        }
    }

    private Tweet filterFields(String[] printFields, Tweet srcTweet) {
        if (printFields == null) return srcTweet;
        HashSet<String> hm = new HashSet<String>();
        for (String printField : printFields) {
            hm.add(printField);
        }
        if (!hm.contains("created_at")) {
            srcTweet.setCreated_at(null);
        }
        if (!hm.contains("id")) {
            srcTweet.setId((Long) (null));
        }
        if (!hm.contains("id_str")) {
            srcTweet.setId_str(null);
        }
        if (!hm.contains("text")) {
            srcTweet.setText(null);
        }
        if (!hm.contains("entities")) {
            srcTweet.setEntities(null);
        }
        if (!hm.contains("coordinates")) {
            srcTweet.setCoordinates(null);
        }
        if (!hm.contains("retweet_count")) {
            srcTweet.setRetweet_count(null);
        }
        if (!hm.contains("favorite_count")) {
            srcTweet.setFavorite_count(null);
        }
        if (!hm.contains("retweeted")) {
            Boolean b = null;
            srcTweet.setRetweeted(b);
        }
        if (!hm.contains("favorited")) {
            Boolean b = null;
            srcTweet.setFavorited(b);
        }
        return srcTweet;
    }
}
