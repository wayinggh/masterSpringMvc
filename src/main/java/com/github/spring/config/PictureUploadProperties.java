package com.github.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * Created by waying.he on 2015/12/18.
 */
@ConfigurationProperties(prefix = "upload.pictures")
public class PictureUploadProperties {
    private Resource uploadPath;
    private Resource anonymousPicture;

    public Resource getAnonymousPicture() {
        return this.anonymousPicture;
    }

    public void setAnonymousPicture(String location) {
        this.anonymousPicture = new DefaultResourceLoader().getResource(location);
    }

    public Resource getUploadPath() {
        return this.uploadPath;
    }

    public void setUploadPath(String location) {
        this.uploadPath = new DefaultResourceLoader().getResource(location);
    }
}
