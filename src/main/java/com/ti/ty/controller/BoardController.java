package com.ti.ty.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ti.ty.model.Board;
import com.ti.ty.repository.BoardRepository;
import com.ti.ty.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String list(Model model,@PageableDefault(size = 5) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {

//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText,
                searchText, pageable);
        int startPage = 1;
        int endPage = boards.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards", boards);
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
    public String boardWrite(@ModelAttribute Board board, Authentication authentication) {

        String username = authentication.getName();

        boardService.write(username, board);
        return "redirect:/board/list";
    }

    @GetMapping("/board/boardList/{id}")
    public String boardList(@PathVariable Long id, Model model){

        Board board = boardRepository.findById(id).orElse(null);

        model.addAttribute("boards", board);

        return "/board/boardList";
    }


}
