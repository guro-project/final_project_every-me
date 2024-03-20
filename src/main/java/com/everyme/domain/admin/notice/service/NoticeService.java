package com.everyme.domain.admin.notice.service;

import com.everyme.domain.admin.notice.entity.Notice;
import com.everyme.domain.admin.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    // 공지 등록
    public Object insertNotice(Notice notice) {
        System.out.println("공지사항 등록 서비스");
        System.out.println(notice);

        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);
        System.out.println(date);

        System.out.println("제목 : " + notice.getNoticeTitle());
        System.out.println("내용 : " + notice.getNoticeContent());
        System.out.println("유저번호 : " + notice.getUserNo());

        Notice newNotice = new Notice();
        newNotice.setNoticeContent(notice.getNoticeContent());
        newNotice.setNoticeRegistDate(date);
        newNotice.setNoticeStatus("Y");
        newNotice.setNoticeTitle(notice.getNoticeTitle());
        newNotice.setNoticeUpdateDate(date);
        newNotice.setWriter("운영자");
//        newNotice.setUserNo(notice.getUserNo());

        Notice result = noticeRepository.save(newNotice);
        System.out.println(result);

        return result;
    }

    // 공지 조회
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // 공지 상세 조회
    public Notice findByNoticeNo(Integer noticeNo) {
        Notice notice = noticeRepository.findById(noticeNo).orElseThrow(() -> new IllegalArgumentException("존재하지 않음"));

        return notice;
    }

    // 공지 수정
    public Object updateNotice(Integer noticeNo, Notice notice) {
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeNo);

        if(optionalNotice.isPresent()){
            Notice existingNotice = optionalNotice.get();

            LocalDate dateNow = LocalDate.now();
            Date date = Date.valueOf(dateNow);
            notice.setNoticeUpdateDate(date);
            System.out.println(date);

            existingNotice.setNoticeTitle(notice.getNoticeTitle());
            existingNotice.setNoticeContent(notice.getNoticeContent());
            existingNotice.setUserNo(notice.getUserNo());
            existingNotice.setNoticeStatus("Y");
            existingNotice.setNoticeUpdateDate(date);

            Notice updateNotice = noticeRepository.save(existingNotice);

            return updateNotice;
        } else {
            System.out.println("공지사항이 존재하지않음");
            return null;
        }
    }

    // 공지 삭제
    public Object deleteNotice(Integer noticeNo) {
        System.out.println("공지삭제 서비스");
        Optional<Notice> optionalNotice = noticeRepository.findById(noticeNo);

        if (optionalNotice.isPresent()) {
            Notice noticeToDelete = optionalNotice.get();
            noticeRepository.delete(noticeToDelete);
            return noticeToDelete;
        } else {
            System.out.println("식단이 존재하지 않음");
            return null;
        }
    }
}
