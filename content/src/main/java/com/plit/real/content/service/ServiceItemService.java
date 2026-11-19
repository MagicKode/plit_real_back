package com.plit.real.content.service;

import com.plit.real.content.model.ServiceEntity;

import java.util.List;

public interface ServiceItemService {
    List<ServiceEntity> getAllServices();
    ServiceEntity createService(ServiceEntity serviceEntity);
    ServiceEntity update(Long id, ServiceEntity serviceEntity);
    List<ServiceEntity> updateAllServices(List<ServiceEntity> serviceEntities);
    void deleteService(Long id);
}
