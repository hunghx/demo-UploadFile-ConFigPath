package ra.controller;

import com.google.cloud.storage.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.service.StorageService;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/firebase")
public class FirebaseController {
    @Autowired
    public StorageService storageService;

    @PostMapping("/uploadToFirebase")
    public String uploadToFirebase(Model model, @RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            String link = storageService.uploadFile(file);
            model.addAttribute("fileName",link);
            return "display";
        }
        return "redirect:/";
    }

}
