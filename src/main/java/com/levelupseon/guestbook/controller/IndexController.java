package com.levelupseon.guestbook.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Log4j2
public class IndexController {
  @GetMapping("")
  @ResponseBody
  //@ResponseBody 생략시 jsp forward
  public Map<?,?> index(){
    return Map.of("test", 1234);
  }

}
