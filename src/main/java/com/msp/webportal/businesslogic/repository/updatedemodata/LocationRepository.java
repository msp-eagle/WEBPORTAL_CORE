package com.msp.webportal.businesslogic.repository.updatedemodata;

import com.msp.webportal.businesslogic.model.updatedemodata.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, String> {

    @Query(value = "select l from Location l where l.hierarchy_level_name = :level")
    List<Location> findByHierachyLevel(String level);

    @Query(value = "Select l from Location l where l.parent_loc_code = :code")
    List<Location> findByParentLocationCode(String code);

    @Query(value = "Select l from Location l where l.name = :name and l.hierarchy_level = :hierarchy_level")
    List<Location> findByNameAndHierarchy_level(String name, int hierarchy_level);
    // @Query(value = "select l from public.location l where l.hierarchy_level_name
    // = ?1", nativeQuery = true)
    // List<Location> findByHierarchy_level_name(String hierarchy_level_name);
    // List<Location> findByHierarchy_level(Integer hierarchy_level);

    // List<Location> findByParent_loc_code(String parent_loc_code);
}
