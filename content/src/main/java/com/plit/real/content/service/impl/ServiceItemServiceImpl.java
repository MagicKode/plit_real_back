package com.plit.real.content.service.impl;

import com.plit.real.content.model.ServiceEntity;
import com.plit.real.content.repository.ServiceRepository;
import com.plit.real.content.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService {

    private final ServiceRepository  serviceRepository;

    @Override
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAllByOrderByIdAsc();
    }

    @Override
    public ServiceEntity createService(ServiceEntity serviceEntity) {
        serviceEntity.setId(null);
        return serviceRepository.save(serviceEntity);
    }

    @Override
    public ServiceEntity update(Long id, ServiceEntity serviceEntity) {
        ServiceEntity existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
        existingService.setTitle(serviceEntity.getTitle());
        existingService.setDescription(serviceEntity.getDescription());
        existingService.setIcon(serviceEntity.getIcon());
        return serviceRepository.save(existingService);
    }

    @Override
    @Transactional
    public List<ServiceEntity> updateAllServices(List<ServiceEntity> serviceEntities) {
        Set<Long> incomingIds = serviceEntities.stream()
                .map(ServiceEntity :: getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<ServiceEntity> toDelete = serviceRepository.findAll().stream()
                .filter(existing -> !incomingIds.contains(existing.getId()))
                .toList();
        serviceRepository.deleteAll(toDelete);

        return serviceRepository.saveAll(serviceEntities);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
