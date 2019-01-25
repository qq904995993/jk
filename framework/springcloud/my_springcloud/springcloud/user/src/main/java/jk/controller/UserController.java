package jk.controller;

import com.alibaba.fastjson.JSONObject;
import jk.model.ro.LoginRo;
import jk.service.UserService;
import jk.service.ribbon.FinanceService;
import jk.utils.JSONUtils;
import jk.utils.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FinanceService financeService;

    /**
     * 登录页面
     * @param request
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(@RequestParam(name = "msg", required = false) String msg) {
        ModelAndView mv = new ModelAndView("/index");
        if ("1".equals(msg)) {
            mv.addObject("msg", "账号或密码错误");
        } else if ("2".equals(msg)) {
            mv.addObject("msg", "验证码错误");
        }
        return mv;
    }


    @GetMapping(value = "/{userId:\\d}")
    @ResponseBody
    public JSONObject getUser(@PathVariable Long userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", userService.getUser(userId));
        return jsonObject;
    }

}
