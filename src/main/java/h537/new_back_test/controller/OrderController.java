package h537.new_back_test.controller;

import h537.new_back_test.entity.GameOrder_entity.BoardGameApplication;
import h537.new_back_test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/applications")
    public String applications(@RequestBody BoardGameApplication boardGameApplication) throws IOException {
        return orderService.applications(boardGameApplication);
    }
}
