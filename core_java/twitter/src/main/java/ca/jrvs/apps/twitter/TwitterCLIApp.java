package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
public class TwitterCLIApp {

    private Controller controller;
    protected final Logger logger = LoggerFactory.getLogger(TwitterDAO.class);

    @Autowired
    public TwitterCLIApp(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {

        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
                ACCESS_TOKEN, TOKEN_SECRET);
        CrdDao dao = new TwitterDAO(httpHelper);
        Service service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp twitterCLIApp = new TwitterCLIApp(controller);


        twitterCLIApp.run(args);
    }

    public void run(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: TwitterCLI post|show|delete [options]");
        }

        String command = args[0].toLowerCase();

        if (command.equals("post")) {
            Tweet createdTweet = controller.postTweet(args);
        } else if (command.equals("show")) {
            Tweet showedTweet = controller.showTweet(args);
        } else if (command.equals("delete")) {
            List<Tweet> deletedTweets = new ArrayList<Tweet>();
            deletedTweets = controller.deleteTweet(args);
        } else {
            throw new IllegalArgumentException("Usage: TwitterCLI post|show|delete [options]");
        }
    }

    //utility function
    protected static String toJson(Object object, boolean prettyJson, boolean includeNullValues)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        if (!includeNullValues) {
            objectMapper.setSerializationInclusion(Include.NON_NULL);
        }

        if (prettyJson) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return objectMapper.writeValueAsString(object);
    }

}
