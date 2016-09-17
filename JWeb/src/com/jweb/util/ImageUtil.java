package com.jweb.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * @desc: ͼƬ������
 * @author: bingye
 * @createTime: 2015-3-17 ����04:25:32
 * @version: v1.0
 */
public class ImageUtil {
 
    /**
    * ��ͼƬд���ͻ���
    * @author: bingye
    * @createTime: 2015-3-17 ����04:36:04
    * @history:
    * @param image
    * @param response void
    */
    public static void writeImage(byte[] image,HttpServletResponse response){
        if(image==null){
            return;
        }
        byte[] buffer=new byte[1024];
        InputStream is=null;
        OutputStream os=null;
        try {
            is=new ByteArrayInputStream(image);
            os=response.getOutputStream();
            while(is.read(buffer)!=-1){
                os.write(buffer);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(is!=null){is.close();}
                if(os!=null){os.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     
    /**
    * ��ȡָ��·��ͼƬ
    * @author: bingye
    * @createTime: 2015-3-21 ����10:50:44
    * @param filePath
    * @param response void
    */
    public static void writeImage(String filePath,HttpServletResponse response){
        File imageFile=new File(filePath); 
        if(imageFile!=null && imageFile.exists()){
            byte[] buffer=new byte[1024];
            InputStream is=null;
            OutputStream os=null;
            try {
                is=new FileInputStream(imageFile);
                os=response.getOutputStream();
                while(is.read(buffer)!=-1){
                    os.write(buffer);
                    os.flush();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try {
                    if(is!=null){is.close();}
                    if(os!=null){os.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     
    /**
    * ͼƬ�ϴ����ļ���
    * @author: bingye
    * @createTime: 2015-3-20 ����08:07:25
    * @param file
    * @param savePath
    * @return boolean
    */
//    public static ResultDto uploadToLocal(CommonsMultipartFile file,String savePath){
//        if(file!=null && !file.isEmpty()){
//            //��ȡ�ļ�����
//            String fileName=file.getOriginalFilename();
//            //��ȡ��׺��
//            String suffixName=fileName.substring(fileName.indexOf(".")+1);
//            //������
//            String newFileName=System.currentTimeMillis()+"."+suffixName;
//            //���ļ�·��
//            String filePath=savePath+newFileName;
//            //��ȡ�洢�ļ�·��
//            File fileDir=new File(savePath);
//            if(!fileDir.exists()){
//                //����ļ���û�У��½�
//                fileDir.mkdirs();
//            }
//            FileOutputStream fos=null;
//            try {
//                fos=new FileOutputStream(filePath);
//                fos.write(file.getBytes());
//                fos.flush();
//                return ResultUtil.success("UPLOAD_SUCCESS", URLEncoder.encode(newFileName,"utf-8"));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return ResultUtil.fail("UPLOAD_ERROR");
//            } finally{
//                try {
//                    if(fos!=null){
//                        fos.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return ResultUtil.fail("UPLOAD_ERROR");
//                }
//            }
//        }
//        return ResultUtil.fail("UPLOAD_ERROR");
//    }
//     
     
     
}
