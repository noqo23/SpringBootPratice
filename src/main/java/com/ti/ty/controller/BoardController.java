package com.ti.ty.controller;

import com.ti.ty.model.Board;
import com.ti.ty.repository.BoardRepository;
import com.ti.ty.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/board/list")
    public String list(Model model) {

        model.addAttribute("boards", boardService.boardList());
        return "board/list";
    }

    @GetMapping("/board/form")
    public String form(Model model, @RequestParam(required = false) Long id) {

        if(id == null){
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/board/new")
    public String boardWrite(@ModelAttribute Board board) {
        boardService.write(board);
        return "redirect:/board/list";
    }


}
