package com.msp.webportal.businesslogic.model.updatedemodata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class Location {
    @Id
    private String code;
    private String name;
    @Column(name = "hierarchy_level")
    private Integer hierarchy_level;
    @Column(name = "hierarchy_level_name")
    private String hierarchy_level_name; //Country, Province, City, Barangay
    
    @Column(name = "parent_loc_code")
    private String parent_loc_code;

}
