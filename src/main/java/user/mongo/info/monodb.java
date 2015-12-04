package user.mongo.info;

import java.net.UnknownHostException;

public class monodb {
	   private static Mongo mon=null;
	   private monodb(){}
	   public synchronized  static Mongo getInstance(){
	        if(mon==null){
	        	try {
					mon=new  Mongo("127.0.0.1", 27017);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MongoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	         return mon;
	    }
	   public DB getdb(String dbname){
		   return  mon.getDB(dbname);
	   }
}
