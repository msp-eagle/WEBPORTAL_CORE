package com.msp.webportal.businesslogic.repository.nearbyloc;

import com.msp.webportal.businesslogic.dto.locationModeldto;
import com.msp.webportal.businesslogic.model.home.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredCenterRepository extends JpaRepository<LocationModel,String> {
    @Query(value = "SELECT \n" +
            "    id, \n" +
            "    name, \n" +
            "    addr_line2, \n" +
            "    addr_line3, \n" +
            "    CAST(latitude as double precision) As latitude, \n" +
            "    CAST(longitude as double precision)as longitude,\n" +
            "    2 * 6371 * \n" +
            "asin(sqrt(\n" +
            "    sin(radians(( CAST(?1 as double precision)- CAST(latitude as double precision)) / 2))^2 +\n" +
            "    cos(radians(CAST(?1 as double precision))) *\n" +
            "    cos(radians(CAST(latitude as double precision))) *\n" +
            "    sin(radians((CAST(?2 as double precision) - CAST(longitude as double precision)) / 2))^2\n" +
            ")) as distance\n" +
            "FROM registration_center where latitude is not null and longitude  IS NOT null\n"  +
            "ORDER BY distance asc limit 10",nativeQuery = true)
    List<locationModeldto> finddist(String lat, String lon);

    @Query(value = "SELECT * FROM registration_center WHERE LOWER(addr_line3) = LOWER(?1)", nativeQuery = true)
    List<LocationModel> findAllByAddr_line3(String addr_line3);

    @Query(value = "SELECT Distinct(addr_line2) from registration_center order by addr_line2", nativeQuery = true)
    List<String> getUniqueAddr_line2();
    @Query(value = "SELECT Distinct(addr_line3) from registration_center order by addr_line3", nativeQuery = true)
    List<String> getUniqueAddr_line3();

    @Query(value = "SELECT e.* FROM registration_center e WHERE "  +
            "e.name ILIKE %:searchTerm% OR " +
            "e.addr_line2 ILIKE %:searchTerm% OR " +
            "e.addr_line3 ILIKE %:searchTerm%", nativeQuery = true)
    List<LocationModel> searchAllFields(String searchTerm);


}
