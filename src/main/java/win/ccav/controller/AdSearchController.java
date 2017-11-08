package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.ccav.dao.TongjiDao;
import win.ccav.model.Page;
import win.ccav.model.save.StudentEntity;
import win.ccav.model.save.ZiyuanXuexiaoEntity;
import win.ccav.service.XueXiaoService;

import java.util.List;
import java.util.Set;

/**
 * Created by paul on 2017/4/25.
 */
@Controller
public class AdSearchController {

    @Autowired
    private XueXiaoService xueXiaoService;
    @RequestMapping("/ads")
    public String test(Model model){
        Page page = xueXiaoService.queryForPage(1, 10);
        model.addAttribute("page",page);
        return "newSearch";
    }

}
