package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_355 355. 设计推特
 * @Description: https://leetcode-cn.com/problems/design-twitter/
 * @date 2022/4/5 12:16
 */
public class task_355 {

}
class Twitter {
    LinkedList<Tweet> tweets;
    Map<Integer,HashSet<Integer>> followee;

    public Twitter() {
        tweets = new LinkedList<>(  );
        followee = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet( userId, tweetId, System.currentTimeMillis() );
        HashSet<Integer> set = followee.getOrDefault( userId, new HashSet<>() );
        set.add( userId );
        followee.put( userId, set );
        tweets.offer( tweet );
    }

    public List<Integer> getNewsFeed( int userId) {
        HashSet<Integer> set = followee.getOrDefault( userId, new HashSet<>() );
        set.add( userId );
        followee.put( userId, set );
        List<Integer> res = new ArrayList<>();
        int limit =10;
        for (int i = tweets.size() - 1; i >= 0 && limit>0; i--) {
            Tweet tweet = tweets.get( i );
            if (set.contains( tweet.userId )) {
                res.add( tweet.tweetId );
                limit--;
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> set = followee.getOrDefault( followerId, new HashSet<>() );
        set.add( followeeId );
        followee.put( followerId, set );
    }

    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> set = followee.getOrDefault( followerId, new HashSet<>() );
        set.remove( followeeId );
        followee.put( followerId, set );
    }
    class Tweet{
        int userId;
        int tweetId;
        Long timestamp;

        public Tweet( int userId, int tweetId, long currentTimeMillis ) {
            this.timestamp = currentTimeMillis;
            this.userId = userId;
            this.tweetId = tweetId;
        }
    }
}