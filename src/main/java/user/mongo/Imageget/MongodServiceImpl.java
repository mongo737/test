package user.mongo.Imageget;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.ObjectId;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
  //1对mongodb操作接口
public interface MongodServiceImpl {

    public abstract DBCollection getCollection();

    /**  对mongodb操作接口
     * 将Map转化成DBObject
     * @param map
     * @return
     */
    public abstract DBObject map2Obj(Map<String, Object> map);

    /**
     * 根据数据集合的Map,插入数据
     * map的key对应数据库中的DBCollection的key值
     * @param map
     */
    public abstract void insert(Map<String, Object> map);

    /**
     * 根据List<Map<String,Object>>结构的数据集合,插入数据
     * @param list
     */
    public abstract void insertBatch(List<Map<String, Object>> list);

    /**
     * 按照条件参数集合map,删除数据
     * @param map
     */
    public abstract void delete(Map<String, Object> map);

    /**
     * 按照多种条件的并集,批量删除数据
     * @param list
     */
    public abstract void deleteBatch(List<Map<String, Object>> list);

    /**
     * 得到Collection()总的记录数
     * @return
     */
    public abstract long getCollectionCount();

    public abstract long getCount(Map<String, Object> map);

    @SuppressWarnings("unchecked")
    public abstract List<Map<String, Object>> find(Map<String, Object> map);

    /**
     * 根据whereFields参数,更新setFields值
     * @param setFields
     * @param whereFields
     */
    public abstract void update(Map<String, Object> setFields,
            Map<String, Object> whereFields);

    @SuppressWarnings("unchecked")
    public abstract List<Map<String, Object>> findAll();

    /**
     * 根据ID找到唯一数据
     * 有1个id字段标记
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public abstract Map<String, Object> getById(String id);

    /**
     * 将文件流保存到数据库
     * @param in
     * @param filename
     */
    public abstract void saveFile(InputStream in, String filename);

    /**
     * 把文件保存到数据库
     * @param file
     * @param fileName
     */
    public abstract void saveFile(File file, String filename);

    public abstract void saveFile(byte[] bytes, String filename);

    /**
     * 根据文件名称，在数据库中查找文件
     * @param fileName
     * @return
     */
    public abstract List<GridFSDBFile> findFilesByName(String fileName);

    public abstract GridFSDBFile findFileByName(String filename);

    /**
     * 获得数据库中文件的inputstream流
     * @param filename
     * @return
     */
    public abstract InputStream getFileInputStream(String filename);

    /**
     * 根据文件名，获取数据库中第一个数据
     * @param map
     * @return
     */
    public abstract GridFSDBFile findFirstFile(Map<String, Object> map);

    /**
     * 获取所有数据库名称
     * @return
     */
    public List<String> getAllDBNames();

    /**
     * 获取文件总记录数
     * @return
     */
    public long getFileCount();

    /**
     * 获取所有文件列表
     * @return
     */
    public List<GridFSDBFile> getAllFiles();

    /**
     * 根据文件名删除文件
     * @param filename
     */
    public void removeFile(String filename);

    /**
     * 根据dbobject删除文件
     * @param query
     */
    public void removeFile(BasicDBObject query);

    /**
     * 根据id删除文件
     * @param id
     */
    public void removeFile(ObjectId id);

    /**
     * 删除所有文件
     */
    public void removeAllFile();

    public abstract GridFS getGridFS();

    public abstract String getDbName();

    public abstract void setDbName(String dbName);

    public abstract String getCollName();

    public abstract void setCollName(String collName);

    public abstract DB getDb();

    public abstract void setDb(DB db);

}
