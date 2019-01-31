package com.controller;

import com.dao.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerAlpha {

    @Qualifier("DBCrud")
    @Autowired
    private ICrud alg;

    @Qualifier("InputConfig")
    @Autowired
    private CheckInput input;

    private List<String > alphaStringList = new ArrayList<>();
    private String resultString;

    @RequestMapping(value = "/result",method = RequestMethod.GET)
    public String result(ModelMap model) {
        model.addAttribute("alphabeted", alg.getAlphabet());
        return "result";

    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(ModelMap model) {
        resultString = alg.read();
        model.addAttribute(resultString,"resultString");

        return "index";

    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(String key,String value,String idGroup) {
        alg.create(key,value,idGroup);

        try {
            EnumFormat format = input.checkInputAdd(key);
            if (idGroup.equals(format.toString())) {
                if (input.checkLength(5, key)) {
                    resultString= alg.create(key,value,idGroup);
                }
            } else {
                resultString="Данный словарь является "+ idGroup;
            }
            resultString="Ошибка,Данные не добавлены";
        } catch (Exception e) {
            resultString= e.getMessage();
        }
        return "index";

    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(ModelMap model) {
        alphaStringList = alg.getAlphabetNames();
        model.addAttribute("alphaStringList", alphaStringList);
        return "create";

    }

    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public String remove(String key,String idGroup) {
        alg.delete(key,idGroup);
        return "index";

    }
    @RequestMapping(value = "/remove",method = RequestMethod.GET)
    public String remove(ModelMap model) {
        alphaStringList = alg.getAlphabetNames();
        model.addAttribute("alphaStringList", alphaStringList);
        return "remove";

    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search() {
        return "search";
    }


    @RequestMapping(params = "key",value = "/search/",method = RequestMethod.GET)
    public String search( @RequestParam(name = "key") String key,ModelMap model) {
        StringBuilder getStringValue= new StringBuilder("Ключ: " + key + " Значение ключа: ");
        if (alg.getValue(key) != null) {
            for (String list: alg.getValue(key)) {
                getStringValue.append(list).append(" ");
            }
            getStringValue.toString();
        }
        else
            getStringValue.append("По данному ключу не обнаруженно значения");
        model.addAttribute("getStringValue", getStringValue);
        return "search";

    }


    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String update(ModelMap model) {
        alphaStringList = alg.getAlphabetNames();
        model.addAttribute("alphaStringList", alphaStringList);
        return "update";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(String key,String value,String idGroup) {
        try {
            EnumFormat format = input.checkInputAdd(key);
            if (idGroup.equals(format.toString())) {
                if (input.checkLength(5, key)) {
                   resultString= alg.update(key, value, idGroup);
                }
            } else {
                resultString= "Данный словарь является строковым";
            }
        } catch (Exception e) {
            resultString= e.getMessage();
        }
        return "index";
    }


}
