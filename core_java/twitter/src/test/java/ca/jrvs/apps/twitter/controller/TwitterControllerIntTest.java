package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TwitterControllerIntTest {

    private CrdDao dao;
    private Service service;
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
                ACCESS_TOKEN, TOKEN_SECRET);
        dao = new TwitterDAO(httpHelper);
        service = new TwitterService(dao);
        controller = new TwitterController(service);
    }

    @Test
    public void postTweet() {

        String[] srcArg = new String[3];
        srcArg[1] = "any text";
        srcArg[2] = "-1d:1d";
        assertNotNull(controller.postTweet(srcArg));
    }

    @Test
    public void showTweet() {

        String[] srcArg = new String[2];
        srcArg[1] = "1122";
        assertNotNull(controller.showTweet(srcArg));
    }

    @Test
    public void deleteTweet() {
        String[] srcArgPost = new String[3];
        srcArgPost[1] = "some text";
        srcArgPost[2] = "-1d:1d";
        Tweet res = controller.postTweet(srcArgPost);
        String[] srcArg = new String[2];
        srcArg[1] = res.getId_str();
        assertNotNull(controller.deleteTweet(srcArg));
    }
}