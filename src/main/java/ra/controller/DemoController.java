package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
///demo/hello
public class DemoController {
    @RequestMapping( "/hello")
    public String helloView() {
        return "hello";
    }
//    @RequestMapping("/hello/{id}")
//    public String getValueId(@PathVariable("id") int id, Model model){
//        System.out.println("============>"+id);
//        model.addAttribute("id",id);
//        return "hello";
//    }
    @RequestMapping("/hello/{name:[a]+}")
    public String getName(@PathVariable("name") String name, Model model){
        System.out.println("============>"+name);
        model.addAttribute("name",name);
        return "hello";
    }
}
