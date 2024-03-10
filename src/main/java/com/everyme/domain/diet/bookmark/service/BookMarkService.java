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

        DietBookMark result = bookMarkRepository.save(newBookMark);

        return result;
    }

    public DietBookMark findByDietNo(Integer dietNo) {
        DietBookMark bookMark = bookMarkRepository.findById(dietNo).orElseThrow(() -> new IllegalArgumentException("존재하지 않음"));

        return bookMark;
    }

    public Object deleteBookMark(Integer dietNo) {
        Optional<DietBookMark> optionalDietBookMark = bookMarkRepository.findById(dietNo);

        if (optionalDietBookMark.isPresent()) {
            DietBookMark dietToDelete = optionalDietBookMark.get();
            bookMarkRepository.delete(dietToDelete);
            return dietToDelete;
        } else {
            System.out.println("식단이 존재하지 않음");
            return null;
        }
    }
}
