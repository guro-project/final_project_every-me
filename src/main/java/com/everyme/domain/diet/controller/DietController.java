package com.everyme.domain.diet.controller;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.service.DietService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    private final ObjectMapper objectMapper;

    public DietController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // 등록
    @PostMapping("/registdiet")
    public ResponseEntity<Object> registdiet(@RequestPart(value = "dietUri", required = false) MultipartFile dietUri,
                                             @RequestParam("dietData") String dietData) throws IOException {

        // dietDat를 다시 json으로 파싱
        Diet diet = objectMapper.readValue(dietData, Diet.class);

        Object result = dietService.insertDiet(diet);

        System.out.println(result);
        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        Diet response = (Diet)result;

        if (dietUri != null && !dietUri.isEmpty()) {
            // 이미지 처리 로직
            String imgPath = "build/resources/images/" + response.getDietNo() + dietUri.getOriginalFilename();
            byte[] bytes = dietUri.getBytes();
            Path path = Paths.get(imgPath);
            Files.write(path, bytes);
        }


        return ResponseEntity.ok(response);
    }


        // 수정
    @Transactional
    @PutMapping("/updatediet/{dietNo}")
    public ResponseEntity<Object> updatediet(@PathVariable Integer dietNo, @RequestBody Diet diet){
        System.out.println("vb : " +dietNo);
        Object result = dietService.updateDiet(dietNo, diet);
        System.out.println("수정");

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("수정 실패");
        }

        Diet response = (Diet) result;
        System.out.println("2차");
        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    // 조회
//    @GetMapping("/diet")
//    public ResponseEntity<List<Diet>> getAllDiets(){
//        List<Diet> diets = dietService.getAllDiets();
//        return ResponseEntity.ok(diets);
//    }

    @GetMapping("/diet")
    public ResponseEntity<List<Diet>> getUserDiet(@RequestParam("userNo") Integer userNo, @RequestParam("date") String dateString) {
        System.out.println("qqqqq");
        System.out.println("둘 다 확인 : " + dateString);

        Date date = Date.valueOf(dateString);
        System.out.println("date : " + date);
        List<Diet> findUserDiets = dietService.findByUserNoAndDietCalendarDate(userNo, date);
        if (findUserDiets != null && !findUserDiets.isEmpty()) {
            System.out.println("유저별 식단조회");
            return ResponseEntity.ok(findUserDiets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 상세조회
    @GetMapping("/diet/{dietNo}")
    public ResponseEntity<Diet> getDetailDiet(@PathVariable Integer dietNo){
        System.out.println("상세조회");
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

    // 식단 이미지
    @PostMapping("/editDietImg")
    public ResponseEntity<String> editProfileImg(@RequestPart("dietUri") MultipartFile dietUri) {
        if (dietUri.isEmpty()) {
            return ResponseEntity.ok("Please select a file");
        }

        try {
            byte[] bytes = dietUri.getBytes();
            Path path = Paths.get("build/resources/images/" + dietUri.getOriginalFilename());
            Files.write(path, bytes);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("Failed to upload file");
        }
    }




    @GetMapping("/dietPeed")
    public List<Diet> dietPeed() {
        return dietService.getAllDiets();
    }

}
