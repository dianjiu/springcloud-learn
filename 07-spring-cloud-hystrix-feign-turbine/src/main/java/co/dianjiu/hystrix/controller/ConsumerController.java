package co.dianjiu.hystrix.controller;

import co.dianjiu.hystrix.client.IHelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private IHelloRemote helloRemote;

    @RequestMapping(value = "feignRequest")
    public Object feignRequest(){
        String s = helloRemote.nice();
        return s;
    }

}
