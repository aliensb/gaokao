package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import win.ccav.model.Page;
import win.ccav.model.TongjiPage;
import win.ccav.model.save.ZiyuanXuexiaoEntity;
import win.ccav.model.tongji.TongJiModel;
import win.ccav.service.TongjiService;

import java.rmi.server.RMIClassLoader;

/**
 * Created by paul on 2017/4/25.
 */
@Controller
@PropertySource("classpath:/application.properties")
public class TongJiController {
    @Autowired
    private TongjiService tongjiService;
    @Value("${myapp.tongji.pagesize}")
    private Integer pageSize;
    @RequestMapping("/tongji/{pageNo}")
    public String tongji(Model model,@PathVariable(value = "pageNo",required = false) Integer pageNo){
        if(pageNo==null){
            pageNo=1;
        }
        TongjiPage<TongJiModel> page=tongjiService.queryForTongjiPage(pageNo,pageSize);
        model.addAttribute("page",page);
        return "tongji";
    }
    @RequestMapping("/tongji")
    public String tongjiDef(){
        return "redirect:/tongji/1";
    }
    @RequestMapping("/record/{pageNo}")
    public String getRecord(Model model,@PathVariable(value = "pageNo",required = false) Integer pageNo){
        if(pageNo==null){
            pageNo=1;
        }
        TongjiPage<ZiyuanXuexiaoEntity> page=tongjiService.queryForPage(pageNo,pageSize);
        model.addAttribute("page",page);
        return "record";
    }
    @RequestMapping(value = "/record")
    public String findXuexiaoDef(){
        return "redirect:/record/1";
    }

}
