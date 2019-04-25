package com.ordersys.controller;



import com.ordersys.comon.CommResult;
import com.ordersys.model.User;
import com.ordersys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
     @Autowired
     private UserService service;

     @GetMapping("/getById")
     public CommResult getById(Integer id){
         return new CommResult(service.getById(id));
     }

     @PostMapping("/addUser")
     public CommResult addUser(@RequestBody User user){
         Integer num=service.addUser(user);
         if(num>0){
             return new CommResult(200,"添加成功");
         }else {
             return new CommResult(500,"添加失败");
         }
     }

     @GetMapping("/list")
    public CommResult list(){
         return new CommResult(service.list());
     }

}
