package com.plit.real.content.service.impl;

import com.plit.real.content.model.AboutInfo;
import com.plit.real.content.repository.AboutInfoRepository;
import com.plit.real.content.service.AboutInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutInfoServiceImpl implements AboutInfoService {

    private final AboutInfoRepository aboutInfoRepository;

    @Override
    public AboutInfo getAboutInfo() {
        return aboutInfoRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    AboutInfo defaultInfo = new AboutInfo();
                    defaultInfo.setMasterName("Александр");
                    defaultInfo.setExperience("более 10 лет");
                    defaultInfo.setDescription("Профессиональный плиточник и сантехник. Работаю официально (ИП). Аккуратно, в срок, с гарантией.");
                    defaultInfo.setPhotoUrl("master.jpeg");
                    return aboutInfoRepository.save(defaultInfo);
                });
    }

    @Override
    public AboutInfo saveAboutInfo(AboutInfo aboutInfo) {
        AboutInfo existing = aboutInfoRepository.findAll().stream().findFirst().orElse(null);
        if (existing != null) {
            existing.setMasterName(aboutInfo.getMasterName());
            existing.setExperience(aboutInfo.getExperience());
            existing.setDescription(aboutInfo.getDescription());
            existing.setPhotoUrl(aboutInfo.getPhotoUrl());
            return aboutInfoRepository.save(existing);
        } else {
            return aboutInfoRepository.save(aboutInfo);
        }
    }
}
