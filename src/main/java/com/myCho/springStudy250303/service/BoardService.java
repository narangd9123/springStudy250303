package com.myCho.springStudy250303.service;

import com.myCho.springStudy250303.model.Board;
import com.myCho.springStudy250303.model.User;
import com.myCho.springStudy250303.repository.BoardRepository;
import com.myCho.springStudy250303.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void registerProcess(@ModelAttribute User user) {
        //db에 이미 동일한 username을 가진 회원이 존재하는지?
        boolean isUser = userRepository.existsByUsername(user.getUsername());
        if(isUser) {
            log.info("username: {}", user.getUsername());
            return;
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            userRepository.save(user);
        }
    }

    public Board save(@ModelAttribute String username, Board board){
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
