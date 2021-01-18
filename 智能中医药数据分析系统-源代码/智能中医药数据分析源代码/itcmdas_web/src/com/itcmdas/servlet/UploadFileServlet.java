package com.itcmdas.servlet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadFileServlet", urlPatterns = {"/UploadFileServlet"})
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是普通表单还是带文件的表单
        if (!ServletFileUpload.isMultipartContent(req)) {
            return; //如果是普通表单终止方法运行
        }

        //创建上传文件的保存路径，建议在WEB-INF路径下，安全，用户无法直接访问上传的文件
        String uploadPath = this.getServletContext().getRealPath("/upload");

        File uploadFile = new File(uploadPath);
        //如果不存在，则新建一个目录
        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }

        //缓存，临时文件
        //临时路径，假如文件超过了预期的大小，我们就把他放到一个临时文件中，过几天自动删除，或者提醒用户转存为永久
        String tempPath = this.getServletContext().getRealPath("/temp");
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();  //创建临时目录
        }

        /**
         * ServletFileUpload 负责处理上传的文件数据，并将表单中每一项封装成一个FileItem对象
         * 在使用 ServletFileUpload 对象解析请求时，需要用到 DiskFileItemFactory 对象
         * 于是，在进行解析工作前先构建出 DiskFileItemFactory 对象
         * 通过 ServletFileUpload 构造方法或 setter方法，为 ServletFileUpload 对象设置 DiskFileItemFactory 属性
         */

        //处理上传的文件，一般都需要通过流来获取，我们可以使用request.getInputStream()原生态流的文件上传流获取，但是十分麻烦
        //一般使用Apache的文件上传组件来实现，common-fileupload，它需要依赖于commos-io组件

        try {
            //1.创建 DiskFileItemFactory 对象，处理文件上传路径或者限制大小
            DiskFileItemFactory factory = getDiskFileItemFactory(uploadFile);
            //2.获取 ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);
            //3.处理上传的文件
            String url = null;
            url = uploadParseRequest(upload, req, uploadPath);

            //防止乱码
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=UTF-8");

            System.out.println(url);

            int pos=0;
            char[] chars=url.toCharArray();
            for(int i=0;i<url.length();i++){
                if(chars[i]=='/'){
                    pos=i;break;
                }
            }

            req.getSession().setAttribute("url", url);
            req.getRequestDispatcher("tongueAnalysis.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建 DiskFileItemFactory 对象，处理文件上传路径或者限制大小
     *
     * @param file
     * @return
     */
    public static DiskFileItemFactory getDiskFileItemFactory(File file) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置一个缓冲区，当上传文件大于这个缓冲区的时候，把他放到临时文件中
        factory.setSizeThreshold(10 * 1024 * 1024);   //缓存区大小为10M
        factory.setRepository(file);         //临时文件目录，需要一个File

        return factory;
    }

    /**
     * 获取 ServletFileUpload
     *
     * @param factory
     * @return
     */
    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);

        //监听文件上传精度
        upload.setProgressListener(new ProgressListener() {
            @Override
            //pBytesRead：已经读取到的文件大小
            //pContentLenght：文件大小
            public void update(long pBytesRead, long pContentLenght, int pItem) {
//                System.out.println("总大小：" + pContentLenght + "，已上传：" + pBytesRead*1.0/pContentLenght);
            }
        });

        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(10 * 1024 * 1024);   //10M
        //设置总共能够上传文件的大小
        upload.setSizeMax(100 * 1024 * 1024);   //100M

        return upload;
    }

    /**
     * 处理上传的文件
     *
     * @param upload
     * @param request
     * @param uploadPath
     * @return
     * @throws Exception
     */
    public static String uploadParseRequest(ServletFileUpload upload, HttpServletRequest request, String uploadPath) throws Exception {
        String msg = "";

        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //判断上传的表单是否带文件
            if (fileItem.isFormField()) {
                //getFieldName 获取前端表单控件的name
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                System.out.println(name + ": " + value);
            } else {   //带文件
                //======================处理文件=========================//
                String uploadFileName = fileItem.getName();
                //文件不合法
                if (uploadFileName.trim().equals("") || uploadFileName == null) {
                    continue;
                }
                //获取上传的文件名 例如：test/img1.png
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //后缀
                String fileSuffixName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);


                //======================存放文件=========================//

                //使用UUID（唯一识别的通用码），保证文件名唯一
                // UUID.randomUUID() 随机生成一个通用码
                String uuidPath = UUID.randomUUID().toString();

                String realPath = uploadPath + "/" + uuidPath;
                //给每个文件创建一个文件夹
                File realPathFile = new File(realPath);
//                realPathFile.setWritable(true,false);

                if (!realPathFile.exists()) realPathFile.mkdir();

                //======================文件传输=========================//
                //获取文件上传的流
                InputStream inputStream = fileItem.getInputStream();

                //目录是唯一通用码，而目录中的文件名仍不变
                FileOutputStream fileOutputStream = new FileOutputStream(realPath + "/" + fileName);

                //创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];
                //判断是否读取完毕
                int len = 0;
                //如果大于0，则表示还存在数据
                while ((len = inputStream.read(buffer)) > 0) {

                    fileOutputStream.write(buffer, 0, len);
                }

                //关闭流
                fileOutputStream.close();
                inputStream.close();

                fileItem.delete();    //上传成功，删除临时文件

                //msg保存图片路径
                msg = uuidPath + "/" + fileName;

//                System.out.println(msg);

            }
        }
        return msg;
    }
}
