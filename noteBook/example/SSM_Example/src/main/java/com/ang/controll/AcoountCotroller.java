package com.ang.controll;

import com.ang.domain.Acoount;
import com.ang.service.AcoountService;
import com.ang.service.impl.AcoountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AcoountCotroller {

    @Autowired
    private AcoountServiceImpl acoountService;

    @RequestMapping("/findAll")
    @ResponseBody
    public void findAll(){
        List<Acoount> findall = acoountService.findall();
        System.out.println(findall);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Acoount acoount){
        System.out.println(acoount);
        acoountService.save(acoount);
        return "dose save !!!";
    }
}
