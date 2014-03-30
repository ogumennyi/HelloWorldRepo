package iShop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IController {

	@RequestMapping("/ipage")
    public ModelAndView helloWorld() {
 
		System.out.println("1111111111111111");
        String message = "Hello World, Spring 3.0!";
        return new ModelAndView("ipage", "message", message);
    }
	
}
