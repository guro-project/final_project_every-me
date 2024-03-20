package com.everyme.domain.admin.notice.entity;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "tbl_notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Integer noticeNo;
    @Column(name = "notice_title")
    private String noticeTitle;
    @Column(name = "notice_content")
    private String noticeContent;
    @Column(name = "notice_regist_date")
    private Date noticeRegistDate;
    @Column(name = "notice_update_date")
    private Date noticeUpdateDate;
    @Column(name = "notice_status")
    private String noticeStatus;
    @Column(name = "user_no")
    private Integer userNo;

    @Column(name = "writer")
    private String writer;

    public Notice() {
    }

    public Notice(Integer noticeNo, String noticeTitle, String noticeContent, Date noticeRegistDate, Date noticeUpdateDate, String noticeStatus, Integer userNo, String writer) {
        this.noticeNo = noticeNo;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeRegistDate = noticeRegistDate;
        this.noticeUpdateDate = noticeUpdateDate;
        this.noticeStatus = noticeStatus;
        this.userNo = userNo;
        this.writer = writer;
    }

    public Integer getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(Integer noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeRegistDate() {
        return noticeRegistDate;
    }

    public void setNoticeRegistDate(Date noticeRegistDate) {
        this.noticeRegistDate = noticeRegistDate;
    }

    public Date getNoticeUpdateDate() {
        return noticeUpdateDate;
    }

    public void setNoticeUpdateDate(Date noticeUpdateDate) {
        this.noticeUpdateDate = noticeUpdateDate;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeNo=" + noticeNo +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeRegistDate=" + noticeRegistDate +
                ", noticeUpdateDate=" + noticeUpdateDate +
                ", noticeStatus='" + noticeStatus + '\'' +
                ", userNo=" + userNo + '\'' +
                ", wirter=" + writer +
                '}';
    }
}
