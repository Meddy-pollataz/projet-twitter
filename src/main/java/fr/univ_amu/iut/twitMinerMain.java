package fr.univ_amu.iut;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class twitMinerMain {
    public static void main(String[] args) throws TwitterException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("ySwTYsdD7bYdnFCOkXVzteQhQ")
                .setOAuthConsumerSecret("mW3xbo8Kio85inH5uSID9OMcBYePrqTLXELcyWnnzqKMZLnlVg")
                .setOAuthAccessToken("2455455259-7jtT59T7gDLZvWxb01IfihbsLd0XW3XjVd3M7NT")
                .setOAuthAccessTokenSecret("g2IQtfhE0x3Y6DbIqBcdbhkmlFmP0QcfAvgVc6rIWY0FU");

        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        twitter4j.Twitter twitter = tf.getInstance();

        List<Status> status = twitter.getHomeTimeline();
        for (Status s : status) {
            System.out.println(s.getUser().getName() + "  " + s.getText());
        }
    }
}
