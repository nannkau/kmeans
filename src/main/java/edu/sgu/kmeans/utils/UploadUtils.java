package edu.sgu.kmeans.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UploadUtils {
    public static edu.sgu.kmeans.entity.File upload(MultipartFile part, String path) throws IOException {
        System.out.println("uploadRootPath=" + path);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String strDate = dateFormat.format(date);


        File uploadRootDir = new File(path);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        String fileName = strDate+part.getOriginalFilename().replaceAll("\\s", "_");
        File file= new File(path +"/"+ fileName);
        try(InputStream is = part.getInputStream()){
            try(OutputStream os = new FileOutputStream(file)){
                int len = 0;
                byte[] bytes = new byte[1024];
                while ( ( len = is.read(bytes, 0, 1024)) > 0) {
                    os.write(bytes, 0, len);
                }
            }
        }
        edu.sgu.kmeans.entity.File file1= new edu.sgu.kmeans.entity.File();
        file1.setName(part.getOriginalFilename().replaceAll("\\s", "_"));
        file1.setPath(fileName);
        return file1;
    }
    public static List<edu.sgu.kmeans.entity.File> upload(String path, HttpServletRequest request, MultipartFile[] parts) throws IOException {
        List<edu.sgu.kmeans.entity.File> files = new ArrayList<>(parts.length);
        for(MultipartFile part : parts) {
            edu.sgu.kmeans.entity.File file = upload(part, path);
            files.add(file);
        }
        return files;
    }
}
