package com.example.controller;

import cn.hutool.core.io.IoUtil;
import com.example.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @RequestMapping("/list")
    public String list() throws IOException {
        InputStream in=this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines=IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());
        List<User> userlist = lines.stream().map(line ->{
            String[] parts=line.split(",");
            Integer id=Integer.parseInt(parts[0]);
            String username=parts[1];
            String password=parts[2];
            String name=parts[3];
            Integer age=Integer.parseInt(parts[4]);
            LocalDateTime updataTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id,username,password,name,age,updataTime);
        }).toList();
        return userlist.toString();
    }
}
