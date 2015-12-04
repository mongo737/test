package user.mongo.Imageget;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.ObjectId;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * User: lqw
 * Date: 2010-1-6
 * Time: 11:25:08
 */
public class MongoService implements MongodServiceImpl {

    private String dbName;
    private String collName;
    private DB db;

    public MongoService(String dbName, String collName) {
        this.dbName = dbName;
        this.collName = collName;
        db = MongoDBConfig.getDBByName(this.dbName);
    }

    /* (non-Javadoc)
     * @see util.MongodService#getCollection()
     */
    public DBCollection getCollection() {
        return db.getCollection(this.collName);
    }

    /* (non-Javadoc)
     * @see util.MongodService#map2Obj(java.util.Map)
     */
    public DBObject map2Obj(Map<String, Object> map) {
        DBObject obj = new BasicDBObject();
        obj.putAll(map);
        return obj;
    }

    /* (non-Javadoc)
     * @see util.MongodService#insert(java.util.Map)
     */
    public void insert(Map<String, Object> map) {
        DBObject obj = new BasicDBObject();
        obj.putAll(map);
        getCollection().insert(obj);
    }

    /* (non-Javadoc)
     * @see util.MongodService#insertBatch(java.util.List)
     */
    public void insertBatch(List<Map<String, Object>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
//      DBObject[] objs = new DBObject[list.size()];
        List<DBObject> listDB=new ArrayList<DBObject>();
        for (int i = 0; i < list.size(); i++) {
            DBObject dbObject = map2Obj(list.get(i));
            listDB.add(dbObject);
        }
        getCollection().insert(listDB);
    }

    /* (non-Javadoc)
     * @see util.MongodService#delete(java.util.Map)
     */
    public void delete(Map<String, Object> map) {
        DBObject obj = map2Obj(map);
        getCollection().remove(obj);
    }

    /* (non-Javadoc)
     * @see util.MongodService#deleteBatch(java.util.List)
     */
    public void deleteBatch(List<Map<String, Object>> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            getCollection().remove(map2Obj(list.get(i)));
        }
    }

    /* (non-Javadoc)
     * @see util.MongodService#getCount()
     */
    public long getCollectionCount() {
        return getCollection().getCount();
    }

    /* (non-Javadoc)
     * @see util.MongodService#getCount(java.util.Map)
     */
    public long getCount(Map<String, Object> map) {
        return getCollection().getCount(map2Obj(map));
    }

    /* (non-Javadoc)
     * @see util.MongodService#find(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> find(Map<String, Object> map) {
        DBCursor cur = getCollection().find(map2Obj(map));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (cur == null) {
            return list;
        }
        for (; cur.hasNext();) {
            DBObject obj = cur.next();
            Map<String, Object> maps = obj.toMap();
            list.add(maps);
        }
        return list;
    }

    /* (non-Javadoc)
     * @see util.MongodService#update(java.util.Map, java.util.Map)
     */
    public void update(Map<String, Object> setFields,
            Map<String, Object> whereFields) {
        DBObject obj1 = map2Obj(setFields);
        DBObject obj2 = map2Obj(whereFields);
        getCollection().updateMulti(obj1, obj2);
    }

    /* (non-Javadoc)
     * @see util.MongodService#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findAll() {
        DBCursor cur = getCollection().find();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (cur == null) {
            return list;
        }
        for (; cur.hasNext();) {
            DBObject obj = cur.next();
            Map<String, Object> map = obj.toMap();
            list.add(map);
        }
        return list;
    }

    /* (non-Javadoc)
     * @see util.MongodService#getById(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getById(String id) {
        DBObject obj = new BasicDBObject();
        obj.put("id", new Long(id));
        DBObject result = getCollection().findOne(obj);
        return result.toMap();
    }

    /* (non-Javadoc)
     * @see util.MongodService#saveFile(java.io.InputStream, java.lang.String)
     */
    public void saveFile(InputStream in, String filename) {
        GridFS f = new GridFS(db);
        GridFSFile mongofile=f.createFile(in, filename);
        mongofile.put("filename", filename);
        mongofile.save();
    }

    /* (non-Javadoc)
     * @see util.MongodService#saveFile(java.io.File, java.lang.String)
     */
    public void saveFile(File file,String filename) {
        GridFS f = getGridFS();
        try {
            GridFSFile mongofile = f.createFile(file);
            mongofile.put("filename", filename);
            mongofile.put("uploadDate", new Date());
            System.out.println("--------"+new Date());
            mongofile.put("contentType","JPG");
            mongofile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see util.MongodService#saveFile(byte[], java.lang.String)
     */
    public void saveFile(byte[] bytes,String filename){
        GridFS gridFS=getGridFS();
        GridFSFile gridFSFile=gridFS.createFile(bytes);
        gridFSFile.put("filename", filename);
        gridFSFile.save();
    }

    /* (non-Javadoc)
     * @see util.MongodService#findFilesByName(java.lang.String)
     */
    public List<GridFSDBFile> findFilesByName(String fileName) {
        GridFS f = getGridFS();
        List<GridFSDBFile> list = f.find(fileName);
        return list;
    }

    /* (non-Javadoc)
     * @see util.MongodService#findFileByName(java.lang.String)
     */
    public GridFSDBFile findFileByName(String filename){
        return getGridFS().findOne(filename);
    }
    /* (non-Javadoc)
     * @see util.MongodService#getFileInputStream(java.lang.String)
     */
    public InputStream getFileInputStream(String filename){
        return getGridFS().findOne(filename).getInputStream();
    }

    /* (non-Javadoc)
     * @see util.MongodService#findFirstFile(java.util.Map)
     */
    public GridFSDBFile findFirstFile(Map<String, Object> map){
        return getGridFS().findOne(map2Obj(map));
    }

    /* (non-Javadoc)
     * @see util.MongodService#getGridFS()
     */
    public GridFS getGridFS(){
        GridFS gridFS = new GridFS(db);
        return gridFS;
    }

    /* (non-Javadoc)
     * @see util.MongodService#getDbName()
     */
    public String getDbName() {
        return dbName;
    }

    /* (non-Javadoc)
     * @see util.MongodService#setDbName(java.lang.String)
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /* (non-Javadoc)
     * @see util.MongodService#getCollName()
     */
    public String getCollName() {
        return collName;
    }

    /* (non-Javadoc)
     * @see util.MongodService#setCollName(java.lang.String)
     */
    public void setCollName(String collName) {
        this.collName = collName;
    }

    /* (non-Javadoc)
     * @see util.MongodService#getDb()
     */
    public DB getDb() {
        return MongoDBConfig.getDBByName(this.dbName);
    }
      //文件总数
    public long getFileCount(){
        return getGridFS().getFileList().count();
    }

    public List<GridFSDBFile> getAllFiles(){
        return getGridFS().find(new BasicDBObject());
    }

    public void removeFile(String filename){
        getGridFS().remove(filename);
    }

    public void removeFile(BasicDBObject query){
        getGridFS().remove(query);
    }

    public void removeFile(ObjectId id){
        getGridFS().remove(id);
    }

    public void removeAllFile(){
        getGridFS().remove(new BasicDBObject());
    }

    /* (non-Javadoc)
     * @see util.MongodService#setDb(com.mongodb.DB)
     */
    public void setDb(DB db) {
        this.db = db;
    }

    public List<String> getAllDBNames() {
        return MongoDBConfig.getDBNames();
    }
}
