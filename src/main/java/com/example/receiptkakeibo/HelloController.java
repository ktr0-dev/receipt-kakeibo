package com.example.receiptkakeibo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "家計簿アプリ、開発スタート！この画面が出れば成功です。";
    }
}