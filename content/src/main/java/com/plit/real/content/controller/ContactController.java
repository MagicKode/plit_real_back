package com.plit.real.content.controller;

import com.plit.real.content.model.ContactInfo;
import com.plit.real.content.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<ContactInfo> getContacts() {
        return ResponseEntity.ok(contactService.getContacts());
    }

    @PutMapping
    public ResponseEntity<ContactInfo> updateContacts(@RequestBody ContactInfo contactInfo) {
        return ResponseEntity.ok(contactService.updateContacts(contactInfo));
    }
}
