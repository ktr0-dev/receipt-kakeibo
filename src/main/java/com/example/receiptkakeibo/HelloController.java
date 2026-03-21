package com.example.receiptkakeibo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class HelloController {

    // 保存先のフォルダパスを指定
    private static final String UPLOAD_DIR = "uploads/";

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

        try {
            // 1. 保存先のパスを作成（例：uploads/receipt.jpg）
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

            // 2. ファイルを保存（同じ名前があれば上書きする設定）
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            model.addAttribute("message", "保存成功！ファイル名: " + file.getOriginalFilename());

        } catch (IOException e) {
            model.addAttribute("message", "保存中にエラーが発生しました");
        }

        return "index"; // とりあえず同じ画面に戻る
    }
}