package com.everyme.domain.faq.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_faq")
public class FAQEntity {

    @Id
    @Column(name = "faq_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faq_question")
    private String question;

    @Column(name = "faq_answer")
    private String answer;

    public FAQEntity() {
    }

    public FAQEntity(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "FAQEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
