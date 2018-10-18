package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author qinzhongjian
 * @date created in 2018/10/17 15:47
 * @since 1.0.0
 */

@Slf4j
@Service
public class FileUploadService {

    public String photoUpload(MultipartFile file) throws IllegalStateException, IOException{
        String fileName=file.getOriginalFilename();// 文件原名称
        if (file!=null) {// 判断上传的文件是否为空
            String path;// 文件路径
            String type;// 文件类型
            log.info("上传的文件原名称:"+fileName);
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath="/Users/xiangli/Desktop/elec5619/demo/src/main/resources/tmp/";
                    // 自定义的文件名称
                    //String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
                    // 设置存放图片文件的路径
                    path=realPath+fileName;
                    log.info("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    log.info("文件成功上传到指定目录下");
                }else {
                    log.info("不是我们想要的文件类型,请按要求重新上传");

                }
            }else {
                log.error("文件类型为空");

            }
        }else {
            log.error("没有找到相对应的文件");

        }
        return "http://localhost:8080/tmp/"+fileName;
    }

}
