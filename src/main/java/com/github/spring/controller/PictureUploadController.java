package com.github.spring.controller;

import com.github.spring.config.PictureUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

/**
 * Created by waying.he on 2015/12/18.
 */
@Controller
public class PictureUploadController {

    public static final Resource PICTURE_DIR = new FileSystemResource("./pictures");
    private Resource uploadPath;
    private Resource anonymousPicture;

    @Autowired
    public PictureUploadController(PictureUploadProperties properties) {
        this.uploadPath = properties.getUploadPath();
        this.anonymousPicture = properties.getAnonymousPicture();
    }

    @RequestMapping("upload")
    public String uploadPage() {
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        if (file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error", "Incorrect file. Please upload a picture");
            return "redirect:/upload";
        }
        String fileName = file.getOriginalFilename();
        File tempFile = File.createTempFile("pic", getFileExtension(fileName), PICTURE_DIR.getFile());
        try (
            InputStream in = file.getInputStream();
            OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return "profile/uploadPage";
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
