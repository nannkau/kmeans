package edu.sgu.kmeans.service.impl;

import edu.sgu.kmeans.entity.File;
import edu.sgu.kmeans.service.KmeanSevice;
import edu.sgu.kmeans.utils.Distance;
import edu.sgu.kmeans.utils.EuclideanDistance;
import edu.sgu.kmeans.utils.ReduceImageUtils;
import edu.sgu.kmeans.utils.UploadUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class KmeanServiceImpl implements KmeanSevice {
    @Value("${upload.path}")
    private String outdir;
    @Override
    public void reduceImage(MultipartFile part, Integer percent, HttpServletResponse resp) {
        try {
            File file= UploadUtils.upload(part,outdir);
            BufferedImage image= ImageIO.read(new java.io.File(outdir+"/"+file.getPath()));
            Integer k=30-((percent*30)/100);
            Distance distance= new EuclideanDistance();
            FileInputStream fileInputStream=ReduceImageUtils.buildImage(image,k,distance,100);
            resp.setContentType(FilenameUtils.getExtension(outdir+"/"+file.getPath()));
            resp.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            BufferedInputStream inStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream outStream = new BufferedOutputStream(resp.getOutputStream());
            byte[] buffer = new byte[5];
            int bytesRead = 0;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();
            inStream.close();
            java.io.File file1= new java.io.File("kmean.png");
            file1.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
