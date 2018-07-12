package test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public ModelAndView welcome() {
    	ModelAndView mv = new ModelAndView("welcome");
    	mv.addObject("message", "Œ‚’Ò«ø");
        return mv;
    }
	
}
