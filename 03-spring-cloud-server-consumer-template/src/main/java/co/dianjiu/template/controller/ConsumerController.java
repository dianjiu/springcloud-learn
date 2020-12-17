package co.dianjiu.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;


    private static final String applicationName = "server-provider";

    @RequestMapping(value = "commonRequest")
    public Object commonRequest(){
        String url = "http://"+ applicationName +"/hello";
        String s = restTemplate.getForObject(url,String.class);
        return s;
    }

}
