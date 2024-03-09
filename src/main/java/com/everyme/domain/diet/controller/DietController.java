package com.everyme.domain.diet.controller;

import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping("/registdiet")
    public ResponseEntity<Object> registdiet(@RequestBody DietDTO dietDTO){
        Object result = dietService.insertDiet(dietDTO);

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        Diet response = (Diet)result;

        return ResponseEntity.ok(response);
    }
}
