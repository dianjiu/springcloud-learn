package co.dianjiu.fegin.controller;

import co.dianjiu.fegin.client.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private IHelloService helloService;

    @RequestMapping(value = "feignRequest")
    public Object feignRequest(){
        String s = helloService.nice();
        return s;
    }

}
