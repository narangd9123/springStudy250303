package com.myCho.springStudy250303.controller;

import com.myCho.springStudy250303.model.Board;
import com.myCho.springStudy250303.repository.BoardRepository;
import com.myCho.springStudy250303.service.BoardService;
import com.myCho.springStudy250303.validator.BoardValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private BoardService boardService;

    @GetMapping("list")
    public String list(@PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText,
                       Model model) {
//      Page<Board> boards = boardRepository.findAll(PageRequest.of(1,20));
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);

        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        if(id == null){
            model.addAttribute("board", new Board());
        } else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boardService.save(username, board);
//        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
