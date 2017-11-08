package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import win.ccav.model.NewSearchForm;
import win.ccav.model.NewSearchResult;
import win.ccav.service.NewSearchService;

import java.util.List;

/**
 * Created by paul on 2017/6/22.
 */
@Controller
public class NewSearchController {
    @Autowired
    private NewSearchService searchService;
    @RequestMapping("/newSearch")
    public String search(NewSearchForm searchForm, Model model){
        List<NewSearchResult> res=searchService.queryForPage(searchForm);
        model.addAttribute("res",res);
        return "newSearchRes";
    }
}
