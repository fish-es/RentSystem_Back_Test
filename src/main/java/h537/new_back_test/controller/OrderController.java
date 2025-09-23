package h537.new_back_test.controller;

import h537.new_back_test.entity.GameOrder_entity.BoardGameApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class OrderController {
    @PostMapping("/applications")
    public String Application(@RequestBody BoardGameApplication boardGameApplication) throws IOException {
        return "ok";
    }
}
