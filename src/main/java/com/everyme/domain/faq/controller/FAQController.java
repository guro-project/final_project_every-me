package com.everyme.domain.faq.controller;

import com.everyme.domain.faq.entity.FAQEntity;
import com.everyme.domain.faq.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
public class FAQController {


    @Autowired
    private FAQService faqService;

    @GetMapping
    public List<FAQEntity> getAllFAQs(){
        return faqService.getAllFAQs();
    }

    @PostMapping
    public FAQEntity registFAQ(@RequestBody  FAQEntity faqEntity){
        return faqService.registFAQ(faqEntity);
    }

    @PutMapping("/{id}")
    public FAQEntity updateFAQ(@PathVariable Long id, @RequestBody FAQEntity faqUpdate){
        return faqService.updateFAQ(id, faqUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFAQ(@PathVariable Long id){
        faqService.deleteFAQ(id);
        return ResponseEntity.ok().build();
    }
}
