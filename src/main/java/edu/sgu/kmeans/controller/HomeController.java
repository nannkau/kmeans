package edu.sgu.kmeans.controller;

import edu.sgu.kmeans.dto.ImageDTO;
import edu.sgu.kmeans.service.KmeanSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    private final KmeanSevice kmeanSevice;
    @Autowired
    public HomeController(KmeanSevice kmeanSevice) {
        this.kmeanSevice = kmeanSevice;
    }

    @RequestMapping(value = {"/"})
    public String index(Model model){
        ImageDTO imageDTO= new ImageDTO();
        model.addAttribute("imageDTO",imageDTO);
        return "home/index";
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public void reduceImage(ImageDTO imageDTO, HttpServletResponse resp){
        kmeanSevice.reduceImage(imageDTO.getMultipartFile(),imageDTO.getPercent(),resp);
    }
}
