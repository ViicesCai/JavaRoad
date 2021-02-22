package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author CAI
 * @time 2021/2/20
 */
@Controller
public class FileUploadController {

    /**
     * 文件上传
     *
     * @param username 用户名
     * @param model 视图模型
     * @param file 上传文件
     * @return 首页
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "username", required = false) String username,
                         Model model,
                         @RequestParam(value = "headerimg") MultipartFile file) {
        System.out.println("上传的文件信息：");
        System.out.println("参数名：" + file.getName());
        System.out.println("文件名：" + file.getOriginalFilename());

        // 文件保存
        try {
            file.transferTo(new File("E:\\mvc_upload\\" + file.getOriginalFilename()));
            model.addAttribute("msg", "文件上传成功");

        } catch (Exception e) {
            model.addAttribute("msg", "文件上传失败,原因：" + e.getMessage());
        }

        return "forward:/index.jsp";
    }

    /**
     * 多文件上传
     *
     * @param username 用户名
     * @param model 视图模型
     * @param files 上传文件数组
     * @return
     */
    @RequestMapping("/uploadFiles")
    public String uploadFiles(@RequestParam(value = "username", required = false) String username,
                              Model model,
                              @RequestParam(value = "headerfile") MultipartFile[] files) {
        System.out.println("上传的文件信息：");

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                System.out.println("文件参数名：" + file.getName());
                System.out.println("文件名：" + file.getOriginalFilename());

                // 文件保存
                try {
                    file.transferTo(new File("E:\\mvc_upload\\" + file.getOriginalFilename()));
                    model.addAttribute("msg", "文件上传成功");

                } catch (Exception e) {
                    model.addAttribute("msg", "文件上传失败,原因：" + e.getMessage());
                }
            }
        }

        return "forward:/index.jsp";
    }
}
