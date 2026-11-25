package com.plit.real.content.controller;

import com.plit.real.content.model.Hero;
import com.plit.real.content.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hero")
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;

    @GetMapping
    public ResponseEntity<Hero> getAllHeroes() {
        return ResponseEntity.ok(heroService.getHero());
    }

    @PutMapping
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero) {
        return ResponseEntity.ok(heroService.updateHero(hero));
    }
}
