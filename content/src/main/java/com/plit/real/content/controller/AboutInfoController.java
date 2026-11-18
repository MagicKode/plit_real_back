package com.plit.real.content.controller;

import com.plit.real.content.model.AboutInfo;
import com.plit.real.content.service.AboutInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
public class AboutInfoController {

    private final AboutInfoService aboutInfoService;

    @GetMapping
    public ResponseEntity<AboutInfo> getAboutInfo(){
        return ResponseEntity.ok(aboutInfoService.getAboutInfo());
    }

    @PutMapping
    public ResponseEntity<AboutInfo> updateAboutInfo(@RequestBody AboutInfo aboutInfo){
        return ResponseEntity.ok(aboutInfoService.saveAboutInfo(aboutInfo));
    }
}
