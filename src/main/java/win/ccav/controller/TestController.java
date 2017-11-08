package win.ccav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paul on 2017/6/27.
 */
@RestController
@RequestMapping("/testcon")
public class TestController {
    @RequestMapping
    public String tes(){
        return "okokookok";
    }
    @RequestMapping("ads")
    public String test(){
        return "tetetetete";
    }
}
