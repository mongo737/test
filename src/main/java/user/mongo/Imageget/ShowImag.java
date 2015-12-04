package user.mongo.Imageget;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/** 处理图片显示请求servlet
 * @author lqw
 */
public class ShowImag extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        String filename = request.getParameter("filename");

        if(filename.toLowerCase().endsWith(".gif")){
            ShowImageUtil.getGIFImage(request, response, filename);
        }

        if(filename.toLowerCase().endsWith(".jpg")){
            ShowImageUtil.getJPGImage(request, response, filename);
        }
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        doGet(request, response);
    }
}
