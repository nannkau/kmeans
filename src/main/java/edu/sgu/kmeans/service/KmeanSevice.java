package edu.sgu.kmeans.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface KmeanSevice {
    void reduceImage(MultipartFile file, Integer pecent,HttpServletResponse resp);
}
