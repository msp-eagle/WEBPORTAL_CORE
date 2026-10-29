package com.msp.webportal.businesslogic.registered_center;

import com.msp.webportal.businesslogic.repository.nearbyloc.RegisteredCenterRepository;
import com.msp.webportal.businesslogic.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredCenterService {
    @Autowired
    private RegisteredCenterRepository repository;
    public CustomResponse getLocationByType(String type){
        CustomResponse response = new CustomResponse();
        String lowercase = type.toLowerCase();
        response.setStatus("Success");
        try{
            if(lowercase.equals("province")){
                response.setData(repository.getUniqueAddr_line3());
            } else if (lowercase.equals("city")) {
                response.setData(repository.getUniqueAddr_line2());
            }else{
                response.setStatus("Failed");
                response.setError("Type is invalid. Provide correct type");
            }
        }catch (Exception e){
            response.setStatus("Failed");
            response.setError("Something went wrong. Try again later.");
        }
        return response;
    }

    public CustomResponse findByProvince(String province){
        CustomResponse response = new CustomResponse();
        String lowercase = province.toLowerCase();
        response.setStatus("Success");
        try{
                response.setData(repository.findAllByAddr_line3(province));
        }catch (Exception e){
            response.setStatus("Failed");
            response.setError("Something went wrong. Try again later.");
        }
        return response;
    }

    public CustomResponse searchAllFields(String searchTerm){
        CustomResponse response = new CustomResponse();
        String lowercase = searchTerm.toLowerCase();
        response.setStatus("Success");
        try{
            response.setData(repository.searchAllFields(searchTerm));
        }catch (Exception e){
            response.setStatus("Failed");
            response.setError("Something went wrong. Try again later.");
        }
        return response;
    }
}
