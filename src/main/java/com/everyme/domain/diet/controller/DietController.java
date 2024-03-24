package com.everyme.domain.diet.controller;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.service.DietService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Base64;
import java.util.Collections;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    private final ObjectMapper objectMapper;

    public DietController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // 이미지를 압축하는 메소드
    public byte[] compressImage(byte[] imageData, String formatName, float quality) throws Exception {
        try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
            BufferedImage originalImage = ImageIO.read(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // 이미지 압축
            ImageIO.write(originalImage, formatName, outputStream);

            return outputStream.toByteArray();
        }
    }

    // 이미지를 저장하는 메소드
    public void saveCompressedImage(byte[] compressedImageData, String imagePath) throws Exception {
        Path path = Paths.get(imagePath);
        Files.write(path, compressedImageData);
    }

    // 등록
    @PostMapping("/registdiet")
    public ResponseEntity<Object> registdiet(@RequestPart(value = "dietUri", required = false) MultipartFile dietUri,
                                             @RequestParam("dietData") String dietData) throws Exception {

        // dietDat를 다시 json으로 파싱
        Diet diet = objectMapper.readValue(dietData, Diet.class);

        Object result = dietService.insertDiet(diet);

        if(!(result instanceof Diet)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        Diet response = (Diet)result;

        if (dietUri != null && !dietUri.isEmpty()) {
            String imgPath = "build/resources/images/" + response.getDietNo() + dietUri.getOriginalFilename();
            byte[] bytes = dietUri.getBytes();

            // 이미지를 압축
            byte[] compressedImageData = compressImage(bytes, "jpg", 0.8f);

            // 압축된 이미지를 저장
            saveCompressedImage(compressedImageData, imgPath);
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

    @GetMapping("/diet")
    public ResponseEntity<List<Diet>> getUserDiet(@RequestParam("userNo") Integer userNo, @RequestParam(value = "date", required = false) String dateString) {
        if (dateString == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Date date = Date.valueOf(dateString);
            System.out.println(date);
            List<Diet> findUserDiets = dietService.findByUserNoAndDietCalendarDate(userNo, date);
            System.out.println(findUserDiets);

            if (!findUserDiets.isEmpty()) {
                return ResponseEntity.ok(findUserDiets);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
//    @GetMapping("/diet")
//    public ResponseEntity<List<Diet>> getUserDiet(@RequestParam("userNo") Integer userNo, @RequestParam(value = "date", required = false) String dateString) {
//        System.out.println("qqqqq");
//        System.out.println("둘 다 확인 : " + dateString);
//
//        Date date = Date.valueOf(dateString);
//        System.out.println("date : " + date);
//        List<Diet> findUserDiets = dietService.findByUserNoAndDietCalendarDate(userNo, date);
//        System.out.println(findUserDiets);
//        if (findUserDiets != null && !findUserDiets.isEmpty()) {
//            System.out.println("유저별 식단조회");
//            return ResponseEntity.ok(findUserDiets);
//        } else {
//            return ResponseEntity.ok(Collections.emptyList());
//        }
//    }

    @GetMapping("/dietList")
    public ResponseEntity<List<Diet>> getUserDietList(@RequestParam Integer userNo) {
        System.out.println(userNo);
        List<Diet> findUserDietList = dietService.findByUserNo(userNo);
        System.out.println(findUserDietList);
        if (findUserDietList != null && !findUserDietList.isEmpty()) {
            System.out.println("유저별 식단조회");
            return ResponseEntity.ok(findUserDietList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
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


    @GetMapping("/dietPeed")
    public List<Diet> dietPeed() throws IOException {
        List<Diet> diets = dietService.getAllDiets();

        for (Diet diet : diets) {
            System.out.println(diet.getDietNo());
            String imagePath;
            if (diet.getDietNo() == null) {
                imagePath = null;
            } else {
                imagePath = "build/resources/images/" + diet.getDietNo() + "_DietImg.jpg";
            }

            File file = new File(imagePath);

            if (file.exists()) {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                String base64Image = Base64.getEncoder().encodeToString(fileContent);
                diet.setDietImg(base64Image);
            } else {
                System.out.println("Image not found for dietNo: " + diet.getDietNo());
                // 이미지가 없더라도 반복문을 계속 실행합니다.
            }
        }
        return diets;
    }

    @GetMapping("/dietPeed/{dietNo}")
    public Diet dietPeedImage(@PathVariable Integer dietNo) throws IOException {
        Diet selectDiet = dietService.findByDietNo(dietNo);
        System.out.println(selectDiet);

        String imagePath = "build/resources/images/" + selectDiet.getDietNo() + "_DietImg.jpg";

        System.out.println(imagePath);

        File selectedFile = new File(imagePath);

        if (selectedFile.exists()) {
            byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
            String base64Image = Base64.getEncoder().encodeToString(fileContent);
            selectDiet.setDietImg(base64Image);
        } else {
            System.out.println("Image not found for dietNo: " + selectDiet.getDietNo());
            // 이미지가 없더라도 반복문을 계속 실행합니다.
        }

        return selectDiet;
    }

}
