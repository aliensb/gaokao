package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import win.ccav.model.Favorite;
import win.ccav.model.TianBaoForm;
import win.ccav.model.User;
import win.ccav.model.XuexiaoCompare;
import win.ccav.model.save.StudentEntity;
import win.ccav.model.save.ZiyuanXuexiaoEntity;
import win.ccav.service.FavoriteService;
import win.ccav.service.StudentService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by paul on 2017/4/23.
 */
@Controller
public class StudentController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/stu/add",method = RequestMethod.POST)
    public @ResponseBody boolean addStu(String sname,double score, HttpSession session){
        if(sname==null||sname.equals("")||sname.trim().equals("")){
            return false;
        }
        StudentEntity student=new StudentEntity();
        student.setSname(sname);
        student.setScore(score);
        session.setAttribute("stu",student);
        return true;
    }
    @RequestMapping("/tianbao")
    public String tianbao(HttpSession session, Model model){
        List<Favorite> allFavorite= (List<Favorite>) session.getAttribute("allFavorite");
        if(allFavorite.size()!=6){
            model.addAttribute("errormsg","所选学校数量不符合要求");
            return "error";
        }
        List<XuexiaoCompare> list=favoriteService.getCompare(allFavorite);
        model.addAttribute("xuexiaos",list);
        return "tianbao";
    }
    @RequestMapping("/tiaobao/save")
    public @ResponseBody int tiaoban(TianBaoForm tianBaoForm,HttpSession session){
        boolean res=false;
        int count=0;
        count=studentService.saveOne(tianBaoForm,session);
        session.setAttribute("saveNum",count);
        return count;
    }
    @RequestMapping("/tianbao/details")
    public String tiaobaoDetails(HttpSession session,Model model){
        StudentEntity studentEntity= (StudentEntity) session.getAttribute("stu");
        model.addAttribute("student",studentEntity);
        return "tbdetails";
    }
    @RequestMapping("/tianbao/insert")
    public String insert(HttpSession session,String stuname,Double stuscore,String wenli){
        if(stuname==null||stuname.trim().equals("")||stuscore==null||wenli.trim().equals("")||wenli==null){
            return "redirect:/tianbao/details";
        }
        Map<String, ZiyuanXuexiaoEntity> ziyuanXuexiaoEntityMap = (Map<String, ZiyuanXuexiaoEntity>) session.getAttribute("xuexiaoMap");
        User user= (User) session.getAttribute("user");
        // TODO: 2017/6/18 修改这里，将user封装到student里面 
        if(ziyuanXuexiaoEntityMap==null||ziyuanXuexiaoEntityMap.size()!=6){
            return "redirect:/tianbao/details";
        }
        studentService.saveStudent(stuname,stuscore,wenli,ziyuanXuexiaoEntityMap,user.getUsername());
        return "save";
    }
}
