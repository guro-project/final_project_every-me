package com.everyme.domain.admin.notice.controller;

import com.everyme.domain.admin.notice.entity.Notice;
import com.everyme.domain.admin.notice.service.NoticeService;
import com.everyme.domain.diet.entity.Diet;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //공지 등록
    @PostMapping("/uploadnotice")
    public ResponseEntity<Object> registNotice(@RequestBody Notice notice){
        System.out.println("컨트롤 : " + notice);
        Object result = noticeService.insertNotice(notice);
        System.out.println("공지사항 등록 컨트롤러");

        if(!(result instanceof Notice)){
            return ResponseEntity.status(404).body("등록 실패");
        }
        Notice response = (Notice) result;

        return ResponseEntity.ok(response);
    }

    //공지 조회
    @GetMapping("/readnotice")
    public ResponseEntity<List<Notice>> getAllNotices(){
        List<Notice> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    // 공지 상세조회
    @GetMapping("/readnotice/{noticeNo}")
    public ResponseEntity<Notice> getDetailNotice(@PathVariable Integer noticeNo){
        System.out.println("공지 상세조회 컨트롤");
        Notice selectNotice = noticeService.findByNoticeNo(noticeNo);
        if(selectNotice != null){
            return ResponseEntity.ok(selectNotice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 공지 수정
    @Transactional
    @PutMapping("/updatenotice/{noticeNo}")
    public ResponseEntity<Object> updateNotice(@PathVariable Integer noticeNo, @RequestBody Notice notice){
        Object result = noticeService.updateNotice(noticeNo, notice);

        if(!(result instanceof Notice)){
            return ResponseEntity.status(404).body("수정 실패");
        }

        Notice response = (Notice) result;
        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    // 공지 삭제
    @DeleteMapping("removenotice/{noticeNo}")
    public ResponseEntity<Object> deleteNotice(@PathVariable Integer noticeNo){
        Object result = noticeService.deleteNotice(noticeNo);
        System.out.println("삭제");

        if(!(result instanceof Notice)){
            return ResponseEntity.status(404).body("삭제 실패");
        }

        Notice response = (Notice) result;

        return ResponseEntity.ok(response);
    }
}
