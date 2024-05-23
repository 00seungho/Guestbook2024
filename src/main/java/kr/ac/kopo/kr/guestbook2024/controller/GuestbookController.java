package kr.ac.kopo.kr.guestbook2024.controller;

import kr.ac.kopo.kr.guestbook2024.dto.PageRequestDTO;
import kr.ac.kopo.kr.guestbook2024.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
    private final GuestbookService service;
    @RequestMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping({ "/list"})
    public void list(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
