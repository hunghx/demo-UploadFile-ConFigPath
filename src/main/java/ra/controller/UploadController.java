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
public class UploadController {
    // lấy đường dẫn upload ảnh tuyệt đối
    private String pathUpload = "C:\\Users\\hung1\\OneDrive\\Desktop\\demo-UploadFile-ConFigPath\\src\\main\\webapp\\assets\\uploads\\";
    @PostMapping("")
    public String upload(@RequestParam("image")MultipartFile image, Model model) throws IOException {
        // upload file
        File file = new File(pathUpload);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        // coppy file upload đén thư mục chỉ đinh
        FileCopyUtils.copy(image.getBytes(),new File(pathUpload+File.separator + fileName));
        model.addAttribute("fileName",fileName);
        return "image";
    }
}
