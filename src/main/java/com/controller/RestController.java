package com.controller;


import com.dao.ICrud;
import com.entity.KeyEssence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class RestController {

    @Qualifier("DBCrud")
    @Autowired
    private ICrud alg;

    @RequestMapping(value= "/search/{key}", method = RequestMethod.GET)
    @ResponseBody
    public StringBuilder getMyData(@PathVariable String key) {
        StringBuilder getStringValue= new StringBuilder("Ключ: " + key + " Значение ключа: ");
        if (alg.getValue(key) != null) {
            for (String list: alg.getValue(key)) {
                getStringValue.append(list).append(" ");
            }
            getStringValue.toString();
        }
        return getStringValue;
    }

    @RequestMapping(value= "/result/full", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyEssence> getFullData() {
        alg.read();
        return alg.getEssence();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMyKey(@RequestBody String data) {
        String[] props = data.split(" ");
        return alg.delete(props[0],props[1]);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String postMyData(@RequestBody String data) {
        String[] props = data.split(" ");
        return alg.create(props[0],props[1],props[2]);
    }


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String putUpdateMyData(@RequestBody String data) {
        String[] props = data.split(" ");
        return alg.update(props[0],props[1],props[2]);
    }

}
