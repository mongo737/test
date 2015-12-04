package user.mongo.Imageget;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
 //将mongodb查询出的图片文件流写出工具类
public class ShowImageUtil {

    static MongodServiceImpl mongoDAO = new MongoService("shopin_db_img", "img");
    // 设定输出的类型
    private static final String GIF = "image/gif;charset=GB2312";
    private static final String JPG = "image/jpeg;charset=GB2312";

    public static void getJPGImage(HttpServletRequest request,
            HttpServletResponse response, String filename) {
        System.out.println("-------------jpg");
        InputStream imageIn = null;
        OutputStream output = null;
        try {
            response.setContentType(JPG);// 设定输出的类型
            // 得到输入流
            imageIn = mongoDAO.getFileInputStream(filename);
            // 得到输出流
            output = response.getOutputStream();
            // 得到输入的编码器，将文件流进行jpg格式编码
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
            // 得到编码后的图片对象
            BufferedImage image = decoder.decodeAsBufferedImage();
            // 得到输出的编码器
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
            encoder.encode(image);// 对图片进行输出编码
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (imageIn != null) {
                    imageIn.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getGIFImage(HttpServletRequest request,
            HttpServletResponse response, String filename) {
        System.out.println("-------------gif");
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream output = null;
        try {
            is = mongoDAO.getFileInputStream(filename);
            filename = mongoDAO.findFileByName(filename).getId() + "";
            output = response.getOutputStream();// 得到输出流
            response.setContentType(GIF);// 设定输出的类型
            bis = new BufferedInputStream(is);// 输入缓冲流
            bos = new BufferedOutputStream(output);// 输出缓冲流
            byte data[] = new byte[4096];// 缓冲字节数
            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.flush();// 清空输出缓冲流
                    bos.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
