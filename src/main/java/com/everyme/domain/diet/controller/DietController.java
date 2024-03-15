package com.everyme.domain.diet.controller;

import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    // 등록
    @PostMapping("/registdiet")
    public ResponseEntity<Object> registdiet(@RequestBody DietDTO dietDTO){
        Object result = dietService.insertDiet(dietDTO);
        System.out.println("컨트롤러");

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        Diet response = (Diet)result;

        return ResponseEntity.ok(response);
    }

    // 수정
    @PutMapping("/updatediet/{dietNo}")
    public ResponseEntity<Object> updatediet(@PathVariable Integer dietNo, @RequestBody DietDTO dietDTO){
        Object result = dietService.updateDiet(dietNo, dietDTO);
        System.out.println("수정");

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("수정 실패");
        }

        Diet response = (Diet) result;

        return ResponseEntity.ok(response);
    }

    // 조회
    @GetMapping("/diet")
    public ResponseEntity<List<Diet>> getAllDiets(){
        List<Diet> diets = dietService.getAllDiets();
        return ResponseEntity.ok(diets);
    }

    // 상세조회
    @GetMapping("/diet/{dietNo}")
    public ResponseEntity<Diet> getDetailDiet(@PathVariable Integer dietNo){
        Diet selectDiet = dietService.findByDietNo(dietNo);
        if (selectDiet != null) {
            return ResponseEntity.ok(selectDiet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 삭제
    @DeleteMapping("/deletediet/{dietNo}")
    public ResponseEntity<Object> deletediet(@PathVariable Integer dietNo){
        Object result = dietService.deleteDiet(dietNo);
        System.out.println("삭제");

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("삭제 실패");
        }

        Diet response = (Diet) result;

        return ResponseEntity.ok(response);
    }




    @GetMapping("/dietPeed")
    public List<Diet> dietPeed() {
        return dietService.getAllDiets();
    }

}
