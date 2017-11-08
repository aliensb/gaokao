package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import win.ccav.dao.UserChooseDetailsDao;
import win.ccav.model.User;
import win.ccav.model.UserChooseDetails;
import win.ccav.model.XuexiaoEntityEager;
import win.ccav.service.DetailsService;

import javax.servlet.http.HttpSession;

/**
 * Created by paul on 2017/4/20.
 */
@Controller
public class DetailsController {
    @Autowired
    private DetailsService detailsService;
    @Autowired
    private UserChooseDetailsDao userChooseDetailsDao;
    @RequestMapping("/details/{id}")
    public String details(@PathVariable Integer id, Model model, HttpSession session){
        User user= (User) session.getAttribute("user");
        XuexiaoEntityEager xuexiaoEntityEager =detailsService.getDetails(id);
        UserChooseDetails  userChooseDetails=new UserChooseDetails(xuexiaoEntityEager.getXuexiaomingcheng(), user.getId());
        userChooseDetailsDao.add(userChooseDetails);
        if(xuexiaoEntityEager==null||xuexiaoEntityEager.getZhuanyeEntities()==null){
            return "error";
        }else {
            model.addAttribute("xuexiao",xuexiaoEntityEager);
            return "details";
        }
    }
}
