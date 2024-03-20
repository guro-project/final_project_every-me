package com.everyme.domain.admin.qna.service;

import com.everyme.domain.admin.notice.entity.Notice;
import com.everyme.domain.admin.qna.entity.Qna;
import com.everyme.domain.admin.qna.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QnaService {

    @Autowired
    private QnaRepository qnaRepository;
    public Object insertQna(Qna qna) {
        System.out.println("질문 등록 서비스");
        System.out.println(qna);

        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);
        System.out.println(date);

        Qna newQna = new Qna();
        newQna.setQnaTitle(qna.getQnaTitle());
        newQna.setQnaContent(qna.getQnaContent());
        newQna.setQnaStatus("Y");
        newQna.setQnaRegistDate(date);
        newQna.setQnaUpdateDate(date);

        Qna result = qnaRepository.save(newQna);
        System.out.println(result);

        return result;
    }

    public List<Qna> getAllQnas() {
        return qnaRepository.findAll();
    }

    public Qna findByQnaNo(Integer qnaNo) {
        Qna qna = qnaRepository.findById(qnaNo).orElseThrow(() -> new IllegalArgumentException("존재하지 않음"));

        return qna;
    }

    public Object updateQna(Integer qnaNo, Qna qna) {
        Optional<Qna> optionalQna = qnaRepository.findById(qnaNo);

        if(optionalQna.isPresent()){
            Qna existingQna = optionalQna.get();

            LocalDate dateNow = LocalDate.now();
            Date date = Date.valueOf(dateNow);
            qna.setQnaUpdateDate(date);
            System.out.println(date);

            existingQna.setQnaTitle(qna.getQnaTitle());
            existingQna.setQnaContent(qna.getQnaContent());
            existingQna.setQnaStatus("Y");
            existingQna.setQnaUpdateDate(date);

            Qna updateQna = qnaRepository.save(existingQna);

            return updateQna;
        } else {
            System.out.println("질문이 존재하지않음");
            return null;
        }
    }

    public Object deleteQna(Integer qnaNo) {
        System.out.println("질문 삭제 서비스");
        Optional<Qna> optionalQna = qnaRepository.findById(qnaNo);

        if (optionalQna.isPresent()) {
            Qna qnaToDelete = optionalQna.get();
            qnaRepository.delete(qnaToDelete);
            return qnaToDelete;
        } else {
            System.out.println("질문이 존재하지 않음");
            return null;
        }
    }
}
