package com.everyme.domain.diet.service;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import com.everyme.domain.diet.dto.DietDTO;
import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.diet.repository.DietRepository;
import com.everyme.domain.user.model.EveryMeRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;

    @PersistenceContext
    private EntityManager entityManager;
    // 등록
    public Object insertDiet(Diet diet) {

        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);
        System.out.println(date);

        Diet newDiet = new Diet();
        newDiet.setUserNo(diet.getUserNo());
        newDiet.setDietName(diet.getDietName());
        newDiet.setDietCategory(diet.getDietCategory());
        newDiet.setTotalKcal(diet.getTotalKcal());
        newDiet.setTotalCarbohydrate(diet.getTotalCarbohydrate());
        newDiet.setTotalProtein(diet.getTotalProtein());
        newDiet.setTotalProvince(diet.getTotalProvince());
        newDiet.setTotalSalt(diet.getTotalSalt());
        newDiet.setIngredientName(diet.getIngredientName());
        newDiet.setDietStatus("Y");
        newDiet.setDietRegistDate(date);
        newDiet.setDietCalendarDate(diet.getDietCalendarDate());
        System.out.println("확인 : " + diet.getDietCalendarDate());
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
        Diet selectDiet = dietRepository.findById(dietNo).orElseThrow(() -> new IllegalArgumentException("존재하지 않음"));

        return selectDiet;
    }

    // 수정
    public Object updateDiet(Integer dietNo, Diet diet) {
        System.out.println("qwqw");
        // 식단 번호로 식단 조회
        Optional<Diet> optionalDiet = dietRepository.findById(dietNo);
        System.out.println("번호 : " + optionalDiet);

        // 해당 식단이 존재하는지 확인
        if (optionalDiet.isPresent()) {
            Diet existingDiet = optionalDiet.get();
            System.out.println("ex : " + existingDiet);
            System.out.println("zz");

            LocalDate dateNow = LocalDate.now();
            Date date = Date.valueOf(dateNow);
            diet.setDietUpdateDate(date);
            System.out.println(date);

            System.out.println(dietNo);

            // 수정할 정보 업데이트
            StringBuilder updateQuery = new StringBuilder("UPDATE tbl_diet SET ");

            List<Object> parameters = new ArrayList<>();
            int parameterIndex = 1;
            System.out.println("카테고리 : " + diet.getDietCategory());
            System.out.println("이름 : " + diet.getDietName());
            System.out.println("칼로리 : " + diet.getTotalKcal());
            System.out.println("탄수화물 : " + diet.getTotalCarbohydrate());
            System.out.println("단백질 : " + diet.getTotalProtein());
            System.out.println("지방 : " + diet.getTotalProvince());
            System.out.println("나트륨 : " + diet.getTotalSalt());

            if (diet.getDietCategory() != null && !Objects.equals(diet.getDietCategory(), "")){
                System.out.println("1");
                updateQuery.append("DIET_CATEGORY = ?, ");
                parameters.add(diet.getDietCategory());
            }
            if (diet.getDietName() != null && !Objects.equals(diet.getDietName(), "")){
                updateQuery.append("DIET_NAME = ?, ");
                System.out.println("2");
                parameters.add(diet.getDietName());
            }
            if (diet.getTotalKcal() != null) {
                updateQuery.append("TOTAL_KCAL = ?, ");
                System.out.println("3");
                parameters.add(diet.getTotalKcal());
            }
            if (diet.getTotalCarbohydrate() != null) {
                updateQuery.append("TOTAL_CARBOHYDRATE = ?, ");
                System.out.println("4");
                parameters.add(diet.getTotalCarbohydrate());
            }
            if (diet.getTotalProtein() != null) {
                updateQuery.append("TOTAL_PROTEIN = ?, ");
                System.out.println("5");
                parameters.add(diet.getTotalProtein());
            }
            if (diet.getTotalProvince() != null) {
                updateQuery.append("TOTAL_PROVINCE = ?, ");
                System.out.println("6");
                parameters.add(diet.getTotalProvince());
            }
            if (diet.getTotalSalt() != null) {
                updateQuery.append("TOTAL_SALT = ?, ");
                System.out.println("7");
                parameters.add(diet.getTotalSalt());
            }
            updateQuery.append("DIET_UPDATE_DATE = ? WHERE DIET_NO = ?");
            parameters.add(diet.getDietUpdateDate());
            parameters.add(dietNo);

            Query query = entityManager.createNativeQuery(updateQuery.toString());
            System.out.println("쿼리 : " + query);

            for (Object paramter : parameters) {
                query.setParameter(parameterIndex++,paramter);
            }

            query.executeUpdate();

            // 업데이트된 식단 저장
//            Diet updatedDiet = dietRepository.save(existingDiet);

            return diet;
        } else {
            System.out.println("식단이 존재하지 않음");
            return null; // 혹은 예외 처리를 할 수 있습니다.
        }
    }

    // 삭제
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


    public List<Diet> findByUserNoAndDietCalendarDate(Integer userNo, Date date) {
        System.out.println("유저별 식단조회 서비스");
        return dietRepository.findByUserNoAndDietCalendarDate(userNo, date);
    }

    public List<Diet> findByUserNo(Integer userNo) {
        System.out.println("유저별 식단조회 서비스");
        return dietRepository.findByUserNo(userNo);
    }
}
