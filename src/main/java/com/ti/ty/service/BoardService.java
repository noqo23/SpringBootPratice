package com.ti.ty.service;

import com.ti.ty.model.Board;
import com.ti.ty.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> boardList(){

        return boardRepository.findAll();
    }

    public void write(Board board){

        boardRepository.save(board);
    }



}
