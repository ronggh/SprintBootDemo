package cn.alan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class FileUploadController {
    @GetMapping("/index")
    public String index(){
        return "fileupload";
    }

    /**
     * MultipartFile SpringiBoot的文件上传类，自动封装上传过来的文件
     *
     * @param userName
     * @param headImage
     * @param photos
     * @return
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("userName") String userName,
                             @RequestParam("headImage") MultipartFile headImage,
                             @RequestParam("photos") MultipartFile[] photos) throws Exception{
        if (!headImage.isEmpty()) {
            // 传输到服务器
            String fileName = headImage.getOriginalFilename();
            headImage.transferTo(new File("F:\\test\\" + fileName));
        }

        if(photos.length>0){
            for(MultipartFile photo:photos){
                if(!photo.isEmpty()){
                    String fileName = photo.getOriginalFilename();
                    photo.transferTo(new File("F:\\test\\photo\\" + fileName));
                }
            }
        }

        // 返回主页
        return "main";
    }
}
