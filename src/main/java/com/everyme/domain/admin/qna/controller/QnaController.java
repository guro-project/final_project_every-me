package com.everyme.domain.admin.qna.controller;

import com.everyme.domain.admin.notice.entity.Notice;
import com.everyme.domain.admin.qna.entity.Qna;
import com.everyme.domain.admin.qna.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QnaController {

    @Autowired
    private QnaService qnaService;

    // 질문등록
    @PostMapping("/uploadqna")
    public ResponseEntity<Object> registQna(@RequestBody Qna qna){
        System.out.println("qna컨트롤 : " + qna);
        Object result = qnaService.insertQna(qna);
        System.out.println("질문 등록 컨트롤러");

        if(!(result instanceof Qna)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        Qna response = (Qna) result;

        return ResponseEntity.ok(response);
    }

    // 질문조회
    @GetMapping("/readqna")
    public ResponseEntity<List<Qna>> getAllQna(){
        List<Qna> qnas = qnaService.getAllQnas();
        return ResponseEntity.ok(qnas);
    }

    // 질문 상세조회
    @GetMapping("/readqna/{qnaNo}")
    public ResponseEntity<Qna> getDetailQna(@PathVariable Integer qnaNo){
        System.out.println("질문 상세조회 컨트롤");
        Qna selectQna = qnaService.findByQnaNo(qnaNo);
        if(selectQna != null){
            return ResponseEntity.ok(selectQna);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 질문 수정
    @Transactional
    @PutMapping("/updateqna/{qnaNo}")
    public ResponseEntity<Object> updateQna(@PathVariable Integer qnaNo, @RequestBody Qna qna){
        Object result = qnaService.updateQna(qnaNo, qna);

        if(!(result instanceof Qna)){
            return ResponseEntity.status(404).body("수정 실패");
        }

        Qna response = (Qna) result;
        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    // 질문 삭제
    @DeleteMapping("removeqna/{qnaNo}")
    public ResponseEntity<Object> deleteQna(@PathVariable Integer qnaNo){
        Object result = qnaService.deleteQna(qnaNo);
        System.out.println("삭제");

        if(!(result instanceof Qna)){
            return ResponseEntity.status(404).body("삭제 실패");
        }

        Qna response = (Qna) result;

        return ResponseEntity.ok(response);
    }
}
