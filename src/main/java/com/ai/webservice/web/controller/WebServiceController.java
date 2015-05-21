/**
 * 
 */
package com.ai.webservice.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ai.webservice.client.User;
import com.ai.webservice.client.UserService;

/**
 * Class Name		: TestWebServiceController<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
@Controller
public class WebServiceController {
    
    @Resource private UserService           userService;
    
    @RequestMapping(value="sayHello",method=RequestMethod.GET)
    public String sayHello(Model model) {
        User userByName = userService.getUserByName("hongboliu");
        model.addAttribute("user", userByName);
        return "webservice/success";
    }
    
}
