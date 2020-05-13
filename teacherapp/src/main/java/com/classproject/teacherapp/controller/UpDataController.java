package com.classproject.teacherapp.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONException;
import com.classproject.teacherapp.entity.AppUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UpDataController
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/11 13:07
 **/
@Slf4j
@Api("多文件上传")
@RestController
@RequestMapping("/updata")
@CrossOrigin
public class UpDataController {

    @ResponseBody
    @PostMapping("/read")
    public String readExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), AppUser.class, null);
        } catch (IOException e) {
            log.error("错误", e);
        }

        return "";
    }


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1);
        //抛出异常
        blockingQueue.add(1);
        blockingQueue.remove(1);
        //特殊值→ 插入：成功返回true，失败返回false
        //        移除：成功返回队列元素，队列没有返回NULL
        blockingQueue.offer(1);
        blockingQueue.poll();
        //阻塞：
        // 当阻塞队列满时，put的时候，直到put成功或者程序中断才结束；
        // 当队列为空时，消费者试图从队列中里take元素，队列会一直阻塞消费者线程直到队列可用
        blockingQueue.put(1);
        blockingQueue.take();
        //超时
        blockingQueue.offer(1, 1, TimeUnit.SECONDS);
        blockingQueue.poll(1, TimeUnit.SECONDS);


        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        System.out.println(stack.peek());
        System.out.println(stack.pop());

    }


    @ApiOperation("多文件上传")
    @PostMapping("/upOneFile")
    public Map uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> arr ;
        int i = 0;

        //        获取文件得原始名称
        String oldfileName = file.getOriginalFilename();
//        获取文件后缀
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
//        生成新的文件名
        String newfileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "");
//        文件得大小
        long size = file.getSize();
//        文件类型
        String type = file.getContentType();
        log.info("{} 类型是：{}", oldfileName, type);
//        处理根据日期生成目录
        String realPath = "/home/static/files";
        String dataPath = realPath + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File dateDir = new File(dataPath);
        if (!dateDir.exists()) {
            System.out.println("66666666666666666666666666666");
            dateDir.mkdirs();
        }
//        处理文件上传
        file.transferTo(new File(dateDir, newfileName));
        log.info("文件名字：[{}]，  文件路径：[{}]", newfileName, dateDir);

//        将文件信息放入数据库中
        arr = new HashMap<>();
        arr.put("downloadUrl", getPath(request) + "/" + newfileName);
        arr.put("fileId", newfileName);
        arr.put("previewUrl", getPath(request) + "/" + newfileName);


        map.put("code", 0);
        map.put("message", "string");
        map.put("result", arr);
        return map;
    }


    @ApiOperation("多文件上传")
    @PostMapping("/upData")
    public Map upload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object>[] arr = new HashMap[files.length];
        int i = 0;
        for (MultipartFile file : files) {
            //        获取文件得原始名称
            String oldfileName = file.getOriginalFilename();
//        获取文件后缀
            String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
//        生成新的文件名
            String newfileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "");
//        文件得大小
            long size = file.getSize();
//        文件类型
            String type = file.getContentType();
            log.info("{} 类型是：{}", oldfileName, type);
//        处理根据日期生成目录
            String realPath = "/home/static/files";
            String dataPath = realPath + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File dateDir = new File(dataPath);
            if (!dateDir.exists()) {
                System.out.println("66666666666666666666666666666");
                dateDir.mkdirs();
            }
//        处理文件上传
            file.transferTo(new File(dateDir, newfileName));
            log.info("文件名字：[{}]，  文件路径：[{}]", newfileName, dateDir);

//        将文件信息放入数据库中
            arr[i] = new HashMap<>();
            arr[i].put("downloadUrl", getPath(request) + "/" + newfileName);
            arr[i].put("fileId", newfileName);
            arr[i].put("previewUrl", dataPath + "/" + newfileName);
            i++;
        }
        map.put("code", 200);
        map.put("message", "string");
        map.put("result", arr);
        return map;
    }


    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().write("Hello, jetty server start ok.");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "";

        if (request.getContentLength() > 0) {

            InputStream inputStream = null;
            FileOutputStream outputStream = null;

            try {
                inputStream = request.getInputStream();
                // 给新文件拼上时间毫秒，防止重名
                long now = System.currentTimeMillis();
                File file = new File("c:/", "file-" + now + ".txt");
                file.createNewFile();

                outputStream = new FileOutputStream(file);

                byte temp[] = new byte[1024];
                int size = -1;
                while ((size = inputStream.read(temp)) != -1) { // 每次读取1KB，直至读完
                    outputStream.write(temp, 0, size);
                }

                log.info("File load success.");
                result = "File load success.";
            } catch (IOException e) {
                log.warn("File load fail.", e);
                result = "File load fail.";
            } finally {
                outputStream.close();
                inputStream.close();
            }
        }

        response.getWriter().write(result);
    }


    @ApiOperation("文件下载")
    @GetMapping("/download")
    public Map download(String filePath, HttpServletResponse response, HttpServletRequest request) throws IOException {

        String filePaths = filePath;
        log.info("filePath:{}", filePaths);
        FileInputStream is = new FileInputStream(new File(filePaths ));
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("2016级毕业生校外毕业实习相关材料.docx", "UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
        return new HashMap();
    }

    /**
     * @author snow
     * @description 得到项目的根目录的绝对路径
     */
    public static String getPath(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/");//表示到项目的根目录下，要是想到目录下的子文件夹，修改"/"即可
        path = path.replaceAll("\\\\", "/");
        return path;
    }

    /**
     * 多文件上传
     *
     * @param files
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @throws JSONException
     */
    public static List<HashMap<String, Object>> mutlUpload(MultipartFile[] files, HttpServletRequest request)
            throws IllegalStateException, IOException, JSONException {

//        if(files.length > 0){
//            String path = request.getSession().getServletContext().getRealPath("/upload");
//            //定义文件
//            File parent = new File(path);
//            if(!parent.exists()) parent.mkdirs();
//
//            //创建这个集合保存所有文件的信息
//            List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
//
//            //循环多次上传多个文件
//            for (MultipartFile file : files) {
//
//                //创建map对象保存每一个文件的信息
//                HashMap<String, Object> map = new HashMap<String,Object>();
//
//                String oldName = file.getOriginalFilename();
//
//                long size = file.getSize();
//
//                //使用TmFileUtil文件上传工具获取文件的各种信息
//                //优化文件大小
//                String sizeString = TmFileUtil.countFileSize(size);
//                //获取文件后缀名
//                String ext = TmFileUtil.getExtNoPoint(oldName);
//                //随机重命名，10位时间字符串
//                String newFileName = TmFileUtil.generateFileName(oldName, 10, "yyyyMMddHHmmss");
//
//                String url = "upload/"+newFileName;
//
//                //文件传输，parent文件
//                file.transferTo(new File(parent, newFileName));
//
//                map.put("oldname",oldName);//文件原名称
//                map.put("ext",ext);
//                map.put("size",sizeString);
//                map.put("name",newFileName);//文件新名称
//                map.put("url",url);
//
//                listMap.add(map);
//            }
//
//            //以json方式输出到页面
//            return listMap;
//        }else{
//            return null;
//        }
        return null;
    }
}
