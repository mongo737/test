package user.mongo.info;

import java.util.Set;

public class Te {
	/**
	 键值数据库monodb
	 */
	public static void main(String[] args) {
		Mongo mongo;
		try {
			//1.连接数据库 两种方法
			mongo = new Mongo("127.0.0.1", 27017);//默认用户
//			DBAddress dbAddress = new DBAddress("localhost", "fans");  //本地fans用户
//			mongo = new Mongo(dbAddress);

			//2.为users数据库 增加一个用户,密码需转换成字符数据
			/*DB db1 = mongo.getDB("users");
			db1.addUser("fans", "123".toCharArray());
			System.out.println(db1.authenticate("fans", "123".toCharArray()));

			//3.何存入对象
			DBObject user = new BasicDBObject();
			user.put("name", "fansof");
			user.put("password", "123");
			//如果没有对应的users数据库，数据库会为此创建一个
			 DB db2 = mongo.getDB("users");
			//如果没有对应的userCollection，数据库会创建一个
			DBCollection coll = db2.getCollection("userCollection");
			DBObject object = new BasicDBObject();
			object.put("username", "fans");
			object.put("user", user);
			coll.insert(object);*/

		//	4  查询  users数据库find()，find( DBObject ref )，find( DBObject ref , DBObject keys )
			DB db = mongo.getDB("users");
			DBCollection coll = db.getCollection("userCollection");
			Set<String> colls = db.getCollectionNames();
			for (String s : colls) {
			System.out.println(s); }
//			DBCursor cur = coll.find();
//			while(cur.hasNext()) {
//			  System.out.println(cur.next());
//			}
			//5 条件查询find( DBObject ref )%where username='fans'
//			DBObject query = new BasicDBObject();
//			query.put("username", "fans");
//			DBCursor cur = coll.find(query);
//			6.查询一个字段的值  select user from ..
//			DBObject query = new BasicDBObject();
//			query.put("user", 1);   //只查询 一个字段名：user
//			DBCursor cur = coll.find(new BasicDBObject(),query);
			//7.分页查询
			DBObject	query = new BasicDBObject();
			query.put("i", new BasicDBObject("$gt", 1).append("$lte", 3));  //  1 < i <= 3
			DBCursor cur = coll.find(query);

			while(cur.hasNext()) {
			   System.out.println(cur.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
