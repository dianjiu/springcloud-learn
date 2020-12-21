package co.dianjiu.client.controller;

import co.dianjiu.client.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @Autowired
    private GitConfig gitConfig;

    @RequestMapping("/hello")
    public String from() {
        return gitConfig.getHello();
    }
}
