package user.mongo.info;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Tweet implements DBObject {
    private String  userId;
    private String  message;

	public boolean containsField(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsKey(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object get(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPartialObject() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void markAsPartialObject() {
		// TODO Auto-generated method stub

	}

	public Object put(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public void putAll(DBObject arg0) {
		// TODO Auto-generated method stub

	}

	public void putAll(Map arg0) {
		// TODO Auto-generated method stub

	}

	public Object removeField(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map toMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Tweet myTweet = new Tweet();
		myTweet.put("user", myTweet.userId);
		myTweet.put("message", myTweet.message);
		myTweet.put("date", new Date());
		//��ݿ�洢 java����
		Mongo mongo=	monodb.getInstance();
		DB db=mongo.getDB("users");
		DBCollection coll = db.getCollection("userCollection");
        coll.insert(myTweet);

//		coll.setObjectClass();
		coll.setObjectClass(Tweet.class);
		Tweet getTweet = (Tweet)coll.findOne();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
