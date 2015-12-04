package user.mongo.info;

import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class test {

	public static void main(String[] args) {
		Mongo mongo=	monodb.getInstance();
		DB db=mongo.getDB("users");
		DBCollection coll = db.getCollection("userCollection");

		//��ȡ�����б� ��
		Set<String> colls = db.getCollectionNames();
		for(String s : colls){
		 System.out.println(s);
		}
//		��Ӽ��� { "_id" : "4b94b8607128b09ba1e04698" ,
		/* "name" : "MongoDB" ,
		"type" : "database" ,
		"count" : 1 ,
		"info" : { "x" : 203 , "y" : 102}}*/
//		BasicDBObject doc = new BasicDBObject();
//		doc.put("name", "MongoDB");
//		doc.put("type", "database");
//		doc.put("count", 1);
//
//		BasicDBObject info = new BasicDBObject();
//		info.put("x", 203);
//		info.put("y", 102);
//		doc.put("info", info);
//		coll.insert(doc);
		//ģʽ����  "i" :1
//		for(int i = 0; i < 4; i++){
//			 coll.insert(new BasicDBObject().append("i", i));
//			}
		BasicDBObject query = new BasicDBObject();
		query.put("i", 3);
		DBCursor  cur = coll.find(query);
		while(cur.hasNext()){
		 System.out.println(cur.next());
		}
		/* ��ѯ����
		DBCursor cur = coll.find();
		while(cur.hasNext()) {
		  System.out.println(cur.next());
		} */
//		coll.createIndex(new BasicDBObject("i", 1));  //������
		List<DBObject> list = coll.getIndexInfo();
		for(DBObject o : list){
		 System.out.println("��ȡ����"+o);
		}
		System.out.println(coll.getCount());
		//��ѯ��һ��
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);

		//MongoDB���?��		���?����com.mongodb.MongoAdmin���ж��塣		��A����ȡ��ݿ��б�
//		MongoAdmin admin = new MongoAdmin();
//		for(String s : admin.getDatabaseNames()){
//		 System.out.println(s);    		}


		DBObject user = new BasicDBObject();
		user.put("userTable", "�û���4");
		DBObject o = coll.findOne(user);
		System.out.println("�¼�"+o);
//		coll.remove(o);

	}

}
