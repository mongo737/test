package user.mongo.info;

import java.net.UnknownHostException;


public class Mono {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mongo mongo;
		try {
			mongo = new Mongo("127.0.0.1", 27017);//Ĭ��test�û�
			DBAddress dbAddress = new DBAddress("localhost","fans");
            mongo = new Mongo(dbAddress);
//
//            db.addUser("fans", "123".toCharArray());
//            System.out.println(db.authenticate("fans", "123".toCharArray()));

//            Mongo db = new Mongo("mydb");
//            Mongo db1 = new Mongo("localhost", "mydb");
//            Mongo db2 = new Mongo("localhost", 27017, "mydb");
//            List<String> colls = db.getDatabaseNames();
//            System.out.println(colls.get(1));
//            for(String s : colls){
//             System.out.println(s);}
//
            /////////////////////////
            DBObject user = new BasicDBObject();
            user.put("name", "liu");
            user.put("password", "123");
              //���û�ж�Ӧ��users��ݿ⣬��ݿ��Ϊ�˴���һ��
            DB db = mongo.getDB("users");
            //���û�ж�Ӧ��userCollection����ݿ�ᴴ��һ��
    DBCollection coll = db.getCollection("userCollection");
            DBObject object = new BasicDBObject();
            object.put("username", "fans");
            object.put("user", user);
            coll.insert(object);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
