package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import win.ccav.dao.UserChooseDetailsDao;
import win.ccav.model.*;
import win.ccav.service.FavoriteService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 2017/4/20.
 */
@Controller
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private UserChooseDetailsDao userChooseDetailsDao;
    @RequestMapping(value = "/favorite/add",method = RequestMethod.POST)
    public @ResponseBody FavoriteResponse favoriteAdd(Favorite favorite, HttpSession session){
        FavoriteResponse response=new FavoriteResponse();
        User user= (User) session.getAttribute("user");
        UserChooseDetails userChooseDetails=new UserChooseDetails(favorite.getXuexiaomingcheng(),user.getId());
        userChooseDetailsDao.add(userChooseDetails);
        List<Favorite> allFavorite= (List<Favorite>) session.getAttribute("allFavorite");
        if(allFavorite==null||allFavorite.size()==0){
            allFavorite=new ArrayList<Favorite>();
            allFavorite.add(favorite);
            session.setAttribute("allFavorite",allFavorite);
            response.setAdded(true);
            response.setMsg("添加成功");
        }
        else{
            for(Favorite f: allFavorite){
                // TODO: 2017/4/20 这里这个f.getid==fa.getid 会有时候不等空了想清楚是怎么的
                if(f.getId().equals(favorite.getId())){
                    response.setAdded(false);
                    response.setMsg("已添加的学校，请从新选择");
                    response.setLen(allFavorite.size());
                    session.setAttribute("length",allFavorite.size());
                    return response;
                }
            }
            response.setAdded(true);
            response.setMsg("添加成功");
            allFavorite.add(favorite);
            session.removeAttribute("allFavorite");

            session.setAttribute("allFavorite",allFavorite);
        }
        response.setLen(allFavorite.size());
        session.setAttribute("length",allFavorite.size());
        return response;
    }
    @RequestMapping(value = "/favorite/remove",method = RequestMethod.POST)
    public @ResponseBody FavoriteResponse removeFavor(HttpSession session,Integer id){
        List<Favorite> allFavorite= (List<Favorite>) session.getAttribute("allFavorite");
        FavoriteResponse response=new FavoriteResponse();
        if(allFavorite==null||allFavorite.size()==0){
            response.setAdded(false);
        }else {
            for(int i=0;i<allFavorite.size();i++){
                if(allFavorite.get(i).getId().equals(id)){
                    allFavorite.remove(i);
                }
            }
            session.removeAttribute("allFavorite");
            session.setAttribute("allFavorite",allFavorite);
            response.setMsg("删除成功");
            response.setAdded(true);
            response.setLen(allFavorite.size());
            session.setAttribute("length",allFavorite.size());
        }
        return response;
    }
    @RequestMapping("/favorite/compare")
    public String compare(HttpSession session, Model model){
        List<Favorite> allFavorite= (List<Favorite>) session.getAttribute("allFavorite");
        if(allFavorite==null||allFavorite.size()==0) {
            return "error";
        }
        List<XuexiaoCompare> xuexiaoEntities=favoriteService.getCompare(allFavorite);
        model.addAttribute("xuexiaos",xuexiaoEntities);
        return "compare";
    }
    @RequestMapping(value = "/favorite/getDetails",method = RequestMethod.POST)
    public @ResponseBody ZhuanyeEntity getDetails(Integer id) {

        return favoriteService.getZhuanyeById(id);
    }
    @RequestMapping(value = "/favorite/getZhuanyes",method = RequestMethod.POST)
    public @ResponseBody List<ZhuanyeCompare> getzhuanyes(ZhuanYeKey zhuanYeKey){
        System.out.println(zhuanYeKey);
        return favoriteService.getzhuanyes(zhuanYeKey);
    }
}
