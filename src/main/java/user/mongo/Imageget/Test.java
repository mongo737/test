package user.mongo.Imageget;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//读写图片文件测试代码
public class Test {

    static MongodServiceImpl mongoDAO = new MongoService("shopin_db_img", "img");
    static String basepath = Test.class.getResource("/").getPath();
    public static void main(String[] args) {

        saveFile();
//         findFile("20100103.jpg");
//         mongoDAO.removeFile(new ObjectId("4b457ba50851085297b6ec13"));
//         mongoDAO.removeAllFile();
        for (GridFSDBFile str : mongoDAO.getAllFiles()) {
            System.out.println("-----------" + str);
        }
    }

    // 保存图片
    public static void saveFile() {

        String filePath = basepath + "file/" + "186.jpg";
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("文件存在");
            try {
                mongoDAO.saveFile(file, "20100103.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 查找图片
    public static void findFile(String filename) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("filename", filename);
            GridFSDBFile file = mongoDAO.findFirstFile(map);
            System.out.println("-------file------" + file.getId());

            // 将查询出的对象写成图片文件
            file.writeTo(basepath + "file/fromDB.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<GridFSDBFile> list = mongoDAO.findFilesByName(filename);
        System.out.println(list.size() + "-------list---" + list);
    }
}
