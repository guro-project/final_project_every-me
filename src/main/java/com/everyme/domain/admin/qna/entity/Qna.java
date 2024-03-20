package com.everyme.domain.admin.qna.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_qna")
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNA_NO")
    private Integer qnaNo;

    @Column(name = "QNA_TITLE")
    private String qnaTitle;

    @Column(name = "QNA_CONTENT")
    private String qnaContent;

    @Column(name = "QNA_STATUS")
    private String qnaStatus;

    @Column(name = "QNA_REGIST_DATE")
    private Date qnaRegistDate;

    @Column(name = "QNA_UPDATE_DATE")
    private Date qnaUpdateDate;

    public Qna() {
    }

    public Qna(Integer qnaNo, String qnaTitle, String qnaContent, String qnaStatus, Date qnaRegistDate, Date qnaUpdateDate) {
        this.qnaNo = qnaNo;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaStatus = qnaStatus;
        this.qnaRegistDate = qnaRegistDate;
        this.qnaUpdateDate = qnaUpdateDate;
    }

    public Integer getQnaNo() {
        return qnaNo;
    }

    public void setQnaNo(Integer qnaNo) {
        this.qnaNo = qnaNo;
    }

    public String getQnaTitle() {
        return qnaTitle;
    }

    public void setQnaTitle(String qnaTitle) {
        this.qnaTitle = qnaTitle;
    }

    public String getQnaContent() {
        return qnaContent;
    }

    public void setQnaContent(String qnaContent) {
        this.qnaContent = qnaContent;
    }

    public String getQnaStatus() {
        return qnaStatus;
    }

    public void setQnaStatus(String qnaStatus) {
        this.qnaStatus = qnaStatus;
    }

    public Date getQnaRegistDate() {
        return qnaRegistDate;
    }

    public void setQnaRegistDate(Date qnaRegistDate) {
        this.qnaRegistDate = qnaRegistDate;
    }

    public Date getQnaUpdateDate() {
        return qnaUpdateDate;
    }

    public void setQnaUpdateDate(Date qnaUpdateDate) {
        this.qnaUpdateDate = qnaUpdateDate;
    }

    @Override
    public String toString() {
        return "Qna{" +
                "qnaNo=" + qnaNo +
                ", qnaTitle='" + qnaTitle + '\'' +
                ", qnaContent='" + qnaContent + '\'' +
                ", qnaStatus='" + qnaStatus + '\'' +
                ", qnaRegistDate=" + qnaRegistDate +
                ", qnaUpdateDate=" + qnaUpdateDate +
                '}';
    }
}
