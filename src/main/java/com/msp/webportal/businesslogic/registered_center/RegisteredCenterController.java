package com.msp.webportal.businesslogic.registered_center;

import com.msp.webportal.businesslogic.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("registered-center")
public class RegisteredCenterController {

    public final RegisteredCenterService service;

    @GetMapping("type/{type}")
    public ResponseEntity<CustomResponse> getLocationByType(@PathVariable String type){
        long requestTime = System.currentTimeMillis();
        CustomResponse response = service.getLocationByType(type);
        long responseTime = System.currentTimeMillis() - requestTime;
        response.setRequestTime(requestTime);
        response.setResponseTime(responseTime);
        return ResponseEntity.ok(response);
    }

    @GetMapping("province/{province}")
    public ResponseEntity<CustomResponse> findByProvince(@PathVariable String province){
        long requestTime = System.currentTimeMillis();
        CustomResponse response = service.findByProvince(province);
        long responseTime = System.currentTimeMillis() - requestTime;
        response.setRequestTime(requestTime);
        response.setResponseTime(responseTime);
        return ResponseEntity.ok(response);
    }

    @GetMapping("search/{searchTerm}")
    public ResponseEntity<CustomResponse> searchAllField(@PathVariable String searchTerm) {
        long requestTime = System.currentTimeMillis();
        CustomResponse response = service.searchAllFields(searchTerm);
        long responseTime = System.currentTimeMillis() - requestTime;
        response.setRequestTime(requestTime);
        response.setResponseTime(responseTime);
        return ResponseEntity.ok(response);
    }

    }
