package com.yulan.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileUpload {

    private final static int LENGTH=1024;
//   private final static String PATH = "D:/Application/apache-tomcat-main/upload";
//    private final static String PATH = "C:/rongbin-tomcat/apache-tomcat-main/upload";
//   private final static String PATH = "E:/服务器目录/apache-tomcat-first/upload";
 //   private final static String PATH = "D:/tomcat/apache-tomcat-8.0.0/upload";
    //服务器路径
   private final static String PATH = "D:\\Application\\servers\\apache-tomcat-9.0.14\\upload";
    private final static String CUSTOMER_IMAGE_PATH = "/customer-image/";
    private final static String YLCONTRACT_IMAGE_PATH = "/YLcontract-image/";

    public static Map<String,Object> copyFile(MultipartFile file, String path,String fileName) {
        String type = file.getContentType();
       String typeValue = type.substring(type.lastIndexOf('/')+1);
  //      String fileName = System.currentTimeMillis()+"-"+file.hashCode()+"-"+(int)(100000000000000000L*Math.random())+"."+typeValue;
   //     String fileName = file.getOriginalFilename();
        String filePath = path+fileName + "."+ typeValue;
        String code = "SUCCESS";

        InputStream is = null;
        OutputStream os = null;
        try {
            if(!Files.exists(Paths.get(path)))
                Files.createDirectories(Paths.get(path));
            is = file.getInputStream();
            os = new FileOutputStream(Paths.get(filePath).toFile());
            byte[] buffer = new byte[LENGTH];
            int size;
            while((size=is.read(buffer,0,LENGTH))>0) {
                os.write(buffer,0,size);
            }
        } catch (IOException e) {
            code = "FAILED";
            e.printStackTrace();
        } finally {
            try {
                if(os!=null) {
                    os.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

        Map<String,Object> result = new HashMap<>(4);
        result.put("code",code);
        result.put("fileName",fileName + "." + typeValue);
        result.put("filePath",filePath);
        result.put("fileTypecopyCustomerImg",type);
        return result;
    }

    public static Map<String,Object> copyCustomerImg(MultipartFile file,String fileName) {
        Map<String,Object> result = copyFile(file,PATH + CUSTOMER_IMAGE_PATH ,fileName);
        result.put("relativePath" , CUSTOMER_IMAGE_PATH );
        return result;
    }

    public static Map<String,Object> copyYLcontractImg(MultipartFile file, String fileName) {
        Map<String,Object> result = copyFile(file,PATH + YLCONTRACT_IMAGE_PATH ,fileName);
        result.put("relativePath" , YLCONTRACT_IMAGE_PATH);
        return result;
    }



    public static void deleteFile(String deletePath, String filePath) {
        Path path = Paths.get(deletePath + filePath);
        if(Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   /* public static void deleteBanner(String relativePath) {
        deleteFile(PATH,relativePath);
    }

    public static void deleteUeditorImage(String ueditorImageName) {
        deleteFile(PATH + UEDITOR_IMAGE_PATH,ueditorImageName);
    }

    public static void deleteTeacherImg(String relativePath){
        deleteFile(PATH,relativePath);
    }*/

}