package com.everyme.domain.faq.service;


import com.everyme.domain.faq.entity.FAQEntity;
import com.everyme.domain.faq.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    public List<FAQEntity> getAllFAQs(){
        return faqRepository.findAll();
    }

    public FAQEntity registFAQ(FAQEntity faqEntity){
        return faqRepository.save(faqEntity);
    }

    public FAQEntity updateFAQ(Long id, FAQEntity faqUpdate){
        FAQEntity faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글 조회 실패 " + id));

        faq.setQuestion(faqUpdate.getQuestion());
        faq.setAnswer(faqUpdate.getAnswer());

        return faqRepository.save(faq);
    }

    public void deleteFAQ(Long id){
        FAQEntity faq = faqRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 게시글 조회 실패" + id));

        faqRepository.delete(faq);
    }
}
