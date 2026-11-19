package com.plit.real.content.controller;

import com.plit.real.content.model.ServiceEntity;
import com.plit.real.content.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceItemService serviceItemService;

    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAll() {
        return ResponseEntity.ok(serviceItemService.getAllServices());
    }

    @PostMapping
    public ResponseEntity<ServiceEntity> createService(@RequestBody ServiceEntity serviceEntity) {
        return ResponseEntity.ok(serviceItemService.createService(serviceEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateService(@PathVariable Long id, @RequestBody ServiceEntity serviceEntity) {
        return ResponseEntity.ok(serviceItemService.update(id, serviceEntity));
    }

    @PutMapping
    public ResponseEntity<List<ServiceEntity>> updateAll(@RequestBody List<ServiceEntity> serviceEntities) {
        return ResponseEntity.ok(serviceItemService.updateAllServices(serviceEntities));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<ServiceEntity>> deleteService(@PathVariable Long id) {
        serviceItemService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
