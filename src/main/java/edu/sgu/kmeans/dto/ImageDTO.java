package edu.sgu.kmeans.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {
    private MultipartFile multipartFile;
    private Integer percent;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
