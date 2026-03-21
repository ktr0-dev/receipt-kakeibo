package com.example.receiptkakeibo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

@Controller
public class HelloController {

    // 最初にページを開いた時の処理
    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html を探して表示する
    }

    // 「送信」ボタンが押された時の処理
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "ファイルを選択してください");
            return "index";
        }

        // ここでファイルを受け取ったことを確認
        String fileName = file.getOriginalFilename();
        model.addAttribute("message", "アップロード成功！ファイル名: " + fileName);

        return "index"; // とりあえず同じ画面に戻る
    }
}