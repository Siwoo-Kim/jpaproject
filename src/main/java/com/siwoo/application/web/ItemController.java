package com.siwoo.application.web;

import com.siwoo.application.domain.Book;
import com.siwoo.application.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired ItemService itemService;


    @GetMapping
    public String list(Model model) {
        model.addAttribute("items",itemService.findItems());
        return "items/list";
    }

    @GetMapping("/new")
    public String reserveForm() {
        return "items/form";
    }

    @PostMapping("/new")
    public String reservePost(Book item) {
        itemService.reserveItem(item);
        return "redirect:/items";
    }

}
