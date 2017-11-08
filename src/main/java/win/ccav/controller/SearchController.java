package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.ccav.model.Page;
import win.ccav.model.SearchForm;
import win.ccav.model.WeiciSearchForm;
import win.ccav.model.XuexiaoEntity;
import win.ccav.service.SearchService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by paul on 2017/4/19.
 */
@Controller
@PropertySource("classpath:/application.properties")
public class SearchController {
    @Value("${myapp.pagesize}")
    private Integer pageSize;
    @Autowired
    private SearchService searchService;
//    @RequestMapping("/search")
//    public String searchDef(){
//        System.out.println("search def");
//        return "forward:/search/1";
//    }
    @RequestMapping("/search")
    public String search(SearchForm searchForm, Model model, Integer pageNo, HttpSession session){
        if(pageNo==null)
            pageNo=1;
        Page page=searchService.queryForPage(searchForm,pageNo,pageSize);
        session.setAttribute("searchForm",searchForm);
        //学生成绩获取
        
        if(page.getList()==null){
            return "redirect:/showAll/1";
        }
        else {
            model.addAttribute("page",page);
            return "search";
        }
    }
    @RequestMapping("/search/{pageNo}")
    public String searchPage(@PathVariable Integer pageNo,HttpSession session,Model model){
        if(pageNo==null)
            pageNo=1;
        SearchForm searchForm= (SearchForm) session.getAttribute("searchForm");
        if(searchForm==null)
            return "redirect:/showAll/1";
        else{
            Page page=searchService.queryForPage(searchForm,pageNo,pageSize);
            model.addAttribute("page",page);
            return "search";
        }
    }
    @RequestMapping("/weiciSearch")
    public String weiciSearch(WeiciSearchForm searchForm, Model model, Integer pageNo, HttpSession session){
        if(pageNo==null)
            pageNo=1;
        Page page=searchService.weiciQueryForPage(searchForm,pageNo,pageSize);
        session.setAttribute("searchForm",searchForm);
        //学生成绩获取

        if(page.getList()==null){
            return "redirect:/showAll/1";
        }
        else {
            model.addAttribute("page",page);
            return "weiciSearchpage";
        }
    }
    @RequestMapping("/weiciSearch/{pageNo}")
    public String weiciSearchPage(@PathVariable Integer pageNo,HttpSession session,Model model){
        if(pageNo==null)
            pageNo=1;
        WeiciSearchForm searchForm= (WeiciSearchForm) session.getAttribute("searchForm");
        if(searchForm==null)
            return "redirect:/showAll/1";
        else{
            Page page=searchService.weiciQueryForPage(searchForm,pageNo,pageSize);
            model.addAttribute("page",page);
            return "weiciSearchpage";
        }
    }
}
