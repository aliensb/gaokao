package win.ccav.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import win.ccav.dao.SearchDao;
import win.ccav.model.Page;
import win.ccav.service.SearchService;
import win.ccav.service.ZhuanyeService;

import javax.servlet.http.HttpSession;

/**
 * Created by paul on 2017/4/22.
 */
@Controller
@PropertySource("classpath:/application.properties")
public class FindController{
        @Value("${myapp.pagesize}")
        private Integer pageSize;
    @Autowired
    private SearchService  searchService;
    @Autowired
    private ZhuanyeService zhuanyeService;
    @RequestMapping(value = "/find")
    public String find(String name,Integer pageNo,Model model,HttpSession session) {
        if (name == null) {
            return "redirect:/showAll/1";
        }
        if (pageNo == null) {
            pageNo = 1;
        }
        Page page = searchService.searchForPage(name, pageNo, pageSize);
        session.setAttribute("searchName",name);
        model.addAttribute("page", page);
        return "find";
    }
    @RequestMapping("/find/{pageNo}")
    public String findPage(@PathVariable Integer pageNo, Model model, HttpSession session){
        String name= (String) session.getAttribute("searchName");
        if(name==null){
            return "redirect:/showAll/1";
        }
        else {
            if(pageNo==null){
                pageNo=1;
            }
            Page page=searchService.searchForPage(name,pageNo,pageSize);
            model.addAttribute("page", page);
            return "find";
        }
    }
    @RequestMapping(value = "/zhuanyefind")
    public String zhuanyefind(String name,Integer pageNo,Model model,HttpSession session) {
        if (name == null) {
            return "redirect:/showAll/1";
        }
        if (pageNo == null) {
            pageNo = 1;
        }
        Page page = zhuanyeService.searchForPage(name, pageNo, pageSize);
        session.setAttribute("searchName",name);
        model.addAttribute("page", page);
        return "zhuanyefind";
    }
    @RequestMapping("/zhuanyefind/{pageNo}")
    public String zhuanyefindPage(@PathVariable Integer pageNo, Model model, HttpSession session){
        String name= (String) session.getAttribute("searchName");
        if(name==null){
            return "redirect:/showAll/1";
        }
        else {
            if(pageNo==null){
                pageNo=1;
            }
            Page page=zhuanyeService.searchForPage(name,pageNo,pageSize);
            model.addAttribute("page", page);
            return "zhuanyefind";
        }
    }

}
