package com.everyme.domain.diet.bookmark.service;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import com.everyme.domain.diet.bookmark.repository.BookMarkRepository;
import com.everyme.domain.diet.entity.Diet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookMarkService {

    @Autowired
    private BookMarkRepository bookMarkRepository;
    public Object insertBookMark(DietBookMark dietBookMark) {
        DietBookMark newBookMark = new DietBookMark();
        newBookMark.setDietNo(dietBookMark.getDietNo());
        newBookMark.setUserNo(dietBookMark.getUserNo());
        System.out.println("북마크 추가 서비스");

        DietBookMark result = bookMarkRepository.save(newBookMark);

        return result;
    }

    public DietBookMark findByDietNo(String dietBookMark) {
        System.out.println("북마크 조회 서비스");
        // 식단 번호를 입력했을시 해당 데이터를 조회해줌
        // dietBookMark가 null이거나 dietNo가 비어있으면 조회할 수 없음
//        if (dietBookMark == null || dietBookMark.getDietNo() == null) {
//            System.out.println("식단 북마크가 존재하지 않거나 식단번호가 비어 있습니다.");
//            return null;
//        }

        // 식단번호로 식단북마크를 조회
//        DietBookMark viewBm = bookMarkRepository.findByDietNo(dietBookMark.getDietNo());
        DietBookMark viewBm = bookMarkRepository.findByDietNo(Integer.parseInt(dietBookMark));


        // 조회 결과가 없으면 null 반환
        if (viewBm == null) {
            System.out.println("해당 식단번호의 북마크가 존재하지 않습니다.");
            return null;
        }

        // 조회된 식단북마크를 반환
        return viewBm;
    }

    public Object deleteBookMark(DietBookMark dietBookMark) {
        System.out.println("북마크 삭제 서비스");

        // 1. DietBookMark 객체 존재 여부 확인
        if (dietBookMark == null) {
            System.out.println("식단 북마크가 존재하지 않음");
            return null;
        }

        // 2. dietNo 값 존재 여부 확인
        if (dietBookMark.getDietNo() == null) {
            System.out.println("dietNo 값이 비어있음");
            return null;
        }

        // 3. 해당 dietNo 값을 가진 DietBookMark 객체 존재 여부 확인
        DietBookMark dietToDelete = bookMarkRepository.findByDietNo(dietBookMark.getDietNo());

        if (dietToDelete == null) {
            System.out.println("식단 북마크가 존재하지 않음");
            return null;
        }

        // 4. 존재하면 삭제
        System.out.println("식단 삭제");
        bookMarkRepository.delete(dietToDelete);
        return dietToDelete;
    }


}
