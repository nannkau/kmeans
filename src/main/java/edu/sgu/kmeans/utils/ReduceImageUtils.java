package edu.sgu.kmeans.utils;

import edu.sgu.kmeans.dto.kmean.Centroid;
import edu.sgu.kmeans.dto.kmean.Record;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReduceImageUtils {
    private static List<Record> imageToRGB(BufferedImage img){
        int w = img.getWidth();
        int h = img.getHeight();
        List<Record> records= new ArrayList<>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color color = new Color(img.getRGB(i, j), true);
                Record record= new Record();
                Integer[] integers= new Integer[]{i,j};
                Map<String,Double> map= new HashMap<>();
                map.put("R",Double.parseDouble(String.valueOf(color.getRed())));
                map.put("B",Double.parseDouble(String.valueOf(color.getBlue())));
                map.put("G",Double.parseDouble(String.valueOf(color.getGreen())));
                record.setLabel(integers);
                record.setFeatures(map);
                records.add(record);
            }
        }
        return records;
    }
    public static FileInputStream buildImage(BufferedImage img, int k, Distance distance,
                                   int maxIterations) throws IOException {
        BufferedImage writeBackImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        List<Record> records= imageToRGB(img);
        Map<Centroid,List<Record>> kmeanMap= Kmean.fit(records,k,distance,maxIterations);
        kmeanMap.forEach((key,value)->
                {
                    for (Record record: value
                         ) {
                        Map<String,Double> map=key.getCoordinates();
                        Color color= new Color(map.get("R").intValue(),map.get("G").intValue(),map.get("B").intValue());
                        writeBackImage.setRGB(record.getLabel()[0],record.getLabel()[1], color.getRGB());
                    }
                }
                );
        File outputFile = new File("kmean.png");
        ImageIO.write(writeBackImage, "png", outputFile);
        return new FileInputStream("kmean.png");
    }
}
