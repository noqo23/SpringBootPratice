package com.ti.ty.service;

import com.ti.ty.model.Board;
import com.ti.ty.model.User;
import com.ti.ty.repository.BoardRepository;
import com.ti.ty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;


    public Board write(String username, Board board){

        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }



}
