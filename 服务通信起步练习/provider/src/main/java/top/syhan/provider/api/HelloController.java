package top.syhan.provider.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: syhan
 * @create: 2022-08-30
 **/
@RestController
@RequestMapping(value = "/api")
public class HelloController {
    @GetMapping("/hello")
    public String getHello() {
        return "hello world";
    }
}
