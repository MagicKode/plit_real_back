package com.plit.real.content.service.impl;

import com.plit.real.content.model.Hero;
import com.plit.real.content.repository.HeroRepository;
import com.plit.real.content.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;

    @Override
    public Hero getHero() {
        return heroRepository.findAll().stream()
                .findFirst()
                .orElseGet(Hero::new);
    }

    @Override
    public Hero updateHero(Hero updated) {
        Hero hero = heroRepository.findAll().stream()
                .findFirst()
                .orElseGet(Hero::new);

        hero.setTitle(updated.getTitle());
        hero.setSubtitle(updated.getSubtitle());
        hero.setButtonText(updated.getButtonText());
        hero.setBackgroundUrl(updated.getBackgroundUrl());

        return heroRepository.save(hero);
    }
}
