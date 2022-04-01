package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("hello")
    // import org.springframework.ui.Model; 의 Model은 이놈에다가 data를 실어서 controller에서  view에 넘길 수 있다.
    public String hello(Model model){
        model.addAttribute("data","hello!");

        // 화면 이름, resources/templates/hello.html
        // hello 이름만 적었는데 어떻게 위 경로로 찾아가나?
        // spring boot의 thymeleaf가 viewName 매핑해준다.
        // resources:templates/+{ViewName}+.html
        return "hello";
    }
}
