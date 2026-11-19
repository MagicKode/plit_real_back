package com.plit.real.content.service;

import com.plit.real.content.model.ContactInfo;

public interface ContactService {
    ContactInfo getContacts();
    ContactInfo updateContacts(ContactInfo contactInfo);
}
