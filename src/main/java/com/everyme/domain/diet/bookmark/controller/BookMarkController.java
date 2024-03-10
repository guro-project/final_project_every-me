package com.everyme.domain.diet.bookmark.controller;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import com.everyme.domain.diet.bookmark.service.BookMarkService;
import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dietbookmark/{dietNo}")
public class BookMarkController {

    @Autowired
    private BookMarkService bookMarkService;

    @PostMapping
    public ResponseEntity<Object> registdiet(@RequestBody DietBookMark dietBookMark){
        Object result = bookMarkService.insertBookMark(dietBookMark);
        System.out.println("북마크 컨트롤러");

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        Diet response = (Diet)result;

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Object> getDetailDiet(@PathVariable Integer dietNo){
        DietBookMark selectBookMark = bookMarkService.findByDietNo(dietNo);
        if (selectBookMark != null) {
            return ResponseEntity.ok(selectBookMark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deletediet(@PathVariable Integer dietNo){
        Object result = bookMarkService.deleteBookMark(dietNo);
        System.out.println("삭제");

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("삭제 실패");
        }

        Diet response = (Diet) result;

        return ResponseEntity.ok(response);
    }
}
