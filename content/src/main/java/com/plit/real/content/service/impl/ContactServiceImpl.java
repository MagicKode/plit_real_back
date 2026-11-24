package com.plit.real.content.service.impl;

import com.plit.real.content.model.ContactInfo;
import com.plit.real.content.repository.ContactRepository;
import com.plit.real.content.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private static final Long DEFAULT_ID = 1L;
    private final ContactRepository contactRepository;

    @Override
    public ContactInfo getContacts() {
        return contactRepository.findById(DEFAULT_ID)
                .orElseGet(() -> new ContactInfo(DEFAULT_ID, "", "", "", ""));
    }

    @Override
    public ContactInfo updateContacts(ContactInfo contactInfo) {
        contactInfo.setId(DEFAULT_ID);
        return contactRepository.save(contactInfo);
    }
}
