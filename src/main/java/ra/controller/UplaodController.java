package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UplaodController {
    @PostMapping("")
    public String upload(@RequestParam("image")MultipartFile image, Model model) throws IOException {
        // upload file
        String uploadPath = "E:\\JAVACORE\\Java_MD4_Session1_SpringMVC_CRUD\\demoSring\\src\\main\\webapp\\assets\\image";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        // coppy file upload đén thư mục chỉ đinh
        FileCopyUtils.copy(image.getBytes(),new File(uploadPath+File.separator + fileName));
        model.addAttribute("fileName",fileName);
        return "image";
    }
}
