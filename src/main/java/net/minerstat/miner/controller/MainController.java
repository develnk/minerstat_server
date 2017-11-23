package net.minerstat.miner.controller;

import net.minerstat.miner.entity.User;
import net.minerstat.miner.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MainController {

    private static final String template = "{\"Text\":\"Hello, %s!\"}";

    @Autowired
    private UserServiceImpl userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/greeting")
    public String  greeting(@RequestParam(required=false, defaultValue="World") String name) {
        System.out.println("==== in greeting ====");
        User user = this.userService.get(4);
        user.setPassword("asdf78");
        this.userService.update(user);

//        User newUser = this.userService.add(new User("sad435f", "dsfgh23446", "345345"));
//        System.out.println("New User id: " + newUser.getUid());

        return String.format(template, name);
    }

}
