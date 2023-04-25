package com.example.demospringmvc.controller;

import com.example.demospringmvc.model.constant.PaginationConstant;
import com.example.demospringmvc.model.dto.BookDTO;
import com.example.demospringmvc.service.BookService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Cholpon Kurmanalieva
 */

@Controller
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/index")
    public ModelAndView getIndex(@RequestParam(value = "page", required = false) @Validated @PositiveOrZero Integer page,
                                 @RequestParam(value = "size", required = false) @Validated @Length(min = 1, max = 25) Integer size) {
        ModelAndView model = new ModelAndView("index");

        int pageSize = Objects.isNull(page) ? PaginationConstant.INITIAL_PAGE_SIZE : page - 1;
        int elementSize = Objects.isNull(size) ? PaginationConstant.INITIAL_ELEMENT_SIZE : size;

        Pageable pageable = PageRequest.of(pageSize, elementSize);
        Page<BookDTO> bookDTOS = bookService.getAll(pageable);

        model.addObject("books", bookDTOS.getContent());
        model.addObject("totalPage", bookDTOS.getTotalPages());
        model.addObject("totalElements", bookDTOS.getTotalElements());
        model.addObject("currentPage", pageSize + 1);
        model.addObject("currentSize", elementSize);

        return model;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new BookDTO());

        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors())
            return "create";

        bookService.save(bookDTO);
        
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("book", bookService.getById(id));

        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") UUID id, @ModelAttribute("book") @Valid BookDTO bookDTO,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "edit";

        bookService.update(id, bookDTO);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        bookService.delete(id);

        return "redirect:/index";
    }
}