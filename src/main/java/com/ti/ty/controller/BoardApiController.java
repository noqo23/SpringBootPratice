package com.ti.ty.controller;
import com.ti.ty.model.Board;
import com.ti.ty.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
class BoardApiController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/api/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                    @RequestParam(required = false, defaultValue = "") String content) {

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
            return boardRepository.findAll();
        } else {
            return boardRepository.findByTitleOrContent(title,content);
        }
    }


    @PostMapping("/api/boards")
    Board board(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    @GetMapping("/api/boards/{id}")
    Board one(@PathVariable Long id) {

        return boardRepository.findById(id).orElse(null);
    }

    @PutMapping("/api/boards/{id}")
    Board replaceBoard(@RequestBody Board board, @PathVariable Long id) {

        return boardRepository.findById(id)
                .map(Board -> {
                    Board.setTitle(board.getTitle());
                    Board.setContent(board.getContent());
                    return boardRepository.save(Board);
                })
                .orElseGet(() -> {
                    board.setId(id);
                    return boardRepository.save(board);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/api/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }
}

