package kz.seisen.mid_rpo.controller;


import kz.seisen.mid_rpo.entity.UserModel;
import kz.seisen.mid_rpo.service.impl.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserModelController {

    private final MyUserService userService;


    @GetMapping
    public String getAllTest() {
        return "test";
    }


    @PostMapping("/register")
    public void register(@RequestBody UserModel userModel) {
        userService.registr(userModel);
    }



}
