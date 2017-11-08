package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import win.ccav.model.Page;
import win.ccav.model.XuexiaoEntity;
import win.ccav.service.XueXiaoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by paul on 2017/4/17.
 */

@Controller
@PropertySource("classpath:/application.properties")
public class IndexController {
    @Autowired
    private XueXiaoService xueXiaoService;
    @Value("${myapp.pagesize}")
    private int pageSize;
    @RequestMapping("/")
    public String index(Model model){
        // model.addAttribute("xuexiaos",xueXiaoService.getXueXiaos());
        // return "redirect:/showAll/1";
        return "forward:/showAll/1";
    }

    @RequestMapping(value = "/showAll")
    public String findXuexiaoDef(){
        return "redirect:/showAll/1";
    }



    @RequestMapping(value = "/showAll/{pageNo}")
    public String findAllXuexiao(Model model,@PathVariable(value = "pageNo",required = false) Integer pageNo) {
        try {
            if (pageNo == null) {
                pageNo = 1;
            }
            Page page = xueXiaoService.queryForPage(pageNo, pageSize);
            model.addAttribute("page", page);
            //model.addAttribute("stuscore","100");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    //获取一个省份下面的所有城市
    @RequestMapping(value = "/getchen",method = RequestMethod.POST)
    public @ResponseBody List<String> getChen(String shengfen){
        return xueXiaoService.getChengshi(shengfen);
    }
}
