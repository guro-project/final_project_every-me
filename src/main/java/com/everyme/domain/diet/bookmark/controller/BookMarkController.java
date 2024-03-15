package com.everyme.domain.diet.bookmark.controller;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import com.everyme.domain.diet.bookmark.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookMarkController {

    @Autowired
    private BookMarkService bookMarkService;

    // 추가
    @PostMapping("/registdietbm")
    public ResponseEntity<Object> registdiet(@RequestBody DietBookMark dietBookMark){
        Object result = bookMarkService.insertBookMark(dietBookMark);

        System.out.println("북마크 추가 컨트롤러");

        if(!(result instanceof DietBookMark)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        DietBookMark response = (DietBookMark)result;
        System.out.println(result);

        return ResponseEntity.ok(response);
    }

    // 조회
    @GetMapping("/dietbm")
    public ResponseEntity<Object> getDetailDiet(@RequestParam(required = false) String dietNo) {

        DietBookMark findBookMark = bookMarkService.findByDietNo(dietNo);
        if (findBookMark != null) {
            System.out.println("북마크 조회 컨트롤러");
            return ResponseEntity.ok(findBookMark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 삭제
    @DeleteMapping("/deletedietbm")
    public ResponseEntity<Object> deletediet(@RequestBody DietBookMark dietBookMark){
        Object result = bookMarkService.deleteBookMark(dietBookMark);

        if(!(result instanceof DietBookMark)){
            return ResponseEntity.status(404).body("삭제 실패");
        }

        DietBookMark response = (DietBookMark) result;
        System.out.println("북마크 삭제 컨트롤러");

        return ResponseEntity.ok(response);
    }
}
