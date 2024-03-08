package com.everyme.domain.diet.service;

import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;
    public Object insertDiet(DietDTO dietDTO) {
        System.out.println(dietDTO);

        Diet newDiet = new Diet();
        newDiet.setDietName(dietDTO.getDietName());
        newDiet.setTotalKcal(dietDTO.getTotalKcal());

        Diet result = dietRepository.save(newDiet);

        return result;
    }
}
