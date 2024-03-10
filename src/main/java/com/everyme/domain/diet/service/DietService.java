package com.everyme.domain.diet.service;

import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;
    // 등록
    public Object insertDiet(DietDTO dietDTO) {
        System.out.println("서비스");
        System.out.println(dietDTO);

        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);
        System.out.println(date);

        Diet newDiet = new Diet();
        newDiet.setDietName(dietDTO.getDietName());
        newDiet.setDietCategory(dietDTO.getDietCategory());
        newDiet.setTotalKcal(dietDTO.getTotalKcal());
        newDiet.setTotalCarbohydrate(dietDTO.getTotalCarbohydrate());
        newDiet.setTotalProtein(dietDTO.getTotalProtein());
        newDiet.setTotalProvince(dietDTO.getTotalProvince());
        newDiet.setTotalSalt(dietDTO.getTotalSalt());
        newDiet.setDietStatus("Y");
        newDiet.setDietRegistDate(date);
        newDiet.setDietUpdateDate(date);

        Diet result = dietRepository.save(newDiet);

        return result;
    }

    // 전체조회
    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }

    // 번호찾기
    public Diet findByDietNo(Integer dietNo) {
        Diet diet = dietRepository.findById(dietNo).orElseThrow(() -> new IllegalArgumentException("존재하지 않음"));

        return diet;
    }

    // 수정
    public Object updateDiet(Integer dietNo, DietDTO dietDTO) {
        // 식단 번호로 식단 조회
        Optional<Diet> optionalDiet = dietRepository.findById(dietNo);

        // 해당 식단이 존재하는지 확인
        if (optionalDiet.isPresent()) {
            Diet existingDiet = optionalDiet.get();

            LocalDate dateNow = LocalDate.now();
            Date date = Date.valueOf(dateNow);
            System.out.println(date);

            // 수정할 정보 업데이트
            existingDiet.setDietName(dietDTO.getDietName());
            existingDiet.setDietCategory(dietDTO.getDietCategory());
            existingDiet.setTotalKcal(dietDTO.getTotalKcal());
            existingDiet.setTotalCarbohydrate(dietDTO.getTotalCarbohydrate());
            existingDiet.setTotalProtein(dietDTO.getTotalProtein());
            existingDiet.setTotalProvince(dietDTO.getTotalProvince());
            existingDiet.setTotalSalt(dietDTO.getTotalSalt());
            existingDiet.setDietStatus("Y");
            existingDiet.setDietUpdateDate(date);

            // 업데이트된 식단 저장
            Diet updatedDiet = dietRepository.save(existingDiet);

            return updatedDiet;
        } else {
            System.out.println("식단이 존재하지 않음");
            return null; // 혹은 예외 처리를 할 수 있습니다.
        }
    }

    public Object deleteDiet(Integer dietNo) {
        Optional<Diet> optionalDiet = dietRepository.findById(dietNo);

        if (optionalDiet.isPresent()) {
            Diet dietToDelete = optionalDiet.get();
            dietRepository.delete(dietToDelete);
            return dietToDelete;
        } else {
            System.out.println("식단이 존재하지 않음");
            return null;
        }
    }
}
