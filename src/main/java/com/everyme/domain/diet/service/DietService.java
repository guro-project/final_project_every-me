package com.everyme.domain.diet.service;

import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Diet newDiet = new Diet();
        newDiet.setDietName(dietDTO.getDietName());
        newDiet.setTotalKcal(dietDTO.getTotalKcal());
        newDiet.setUserId(dietDTO.getUserId());
        newDiet.setDietCategory(dietDTO.getDietCategory());

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

            // 수정할 정보 업데이트
            existingDiet.setDietName(dietDTO.getDietName());
            existingDiet.setTotalKcal(dietDTO.getTotalKcal());
            existingDiet.setDietCategory(dietDTO.getDietCategory());

            // 업데이트된 식단 저장
            Diet updatedDiet = dietRepository.save(existingDiet);

            return updatedDiet;
        } else {
            System.out.println("식단이 존재하지 않음");
            return null; // 혹은 예외 처리를 할 수 있습니다.
        }
    }
}
