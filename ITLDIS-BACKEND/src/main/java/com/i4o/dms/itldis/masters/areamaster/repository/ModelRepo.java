package com.i4o.dms.itldis.masters.areamaster.repository;

import com.i4o.dms.itldis.masters.areamaster.model.PinCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ModelRepo extends JpaRepository<PinCode, Integer> {
    @Query(value = "{call sp_getPinCode(:pinCode)}", nativeQuery = true)
    List<Map<String, Object>> getPinCode(@Param("pinCode") Integer pinCode);

    @Query(value = "{call sp_getByPinCode(:cityId,:pinCode)}", nativeQuery = true)
    Map<String, Object> getByPinCode(@Param("cityId") Long cityId, @Param("pinCode") Long pinCode);

    @Query(value = "{call sp_getPostOffice(:pinCode)}", nativeQuery = true)
    List<Map<String, Object>> getPostOffice(@Param("pinCode") Integer pinCode);

    @Query(value = "{call sp_getAllTehsil(:dealerId,:TehsilName)}", nativeQuery = true)
    List<Map<String, Object>> getAllTehsil(Long dealerId, String TehsilName);

//    @Query(value = "{call sp_getAllZones()}", nativeQuery = true)
//    List<Map<String, Object>> getAllZones();

    @Query(value = "{call sp_getAllRegion()}", nativeQuery = true)
    List<Map<String, Object>> getAllRegion();

    @Query(value = "{call sp_getAllZone()}", nativeQuery = true)
    List<Map<String, Object>> getAllZones();

    @Query(value = "{call sp_getRegionByZoneId(:zoneId)}", nativeQuery = true)
    List<Map<String, Object>> getRegionByZoneId(@Param("zoneId") String zoneId);

    @Query(value = "{call sp_get_address_details_by_locality(:postOffice)}", nativeQuery = true)
    Map<String, Object> getByLocality(@Param("postOffice") String postOffice);

    @Query(value = "{call sp_pinCode_auto(:pinCode)}", nativeQuery = true)
    List<Map<String, Object>> findByPinCodeContaining(@Param("pinCode") String pinCode);

    @Query(value = "{call sp_post_office_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getByPostOffice();

    @Query(value = "{call sp_area_master_get_country()}", nativeQuery = true)
    Map<String, Object> getCountry();

    @Query(value = "{call sp_area_master_get_state_autocomplete(:countryId,:stateName)}", nativeQuery = true)
    List<Map<String, Object>> getStateAutoComplete(@Param("countryId")Long countryId,
                                                   @Param("stateName")String stateName);

    @Query(value = "{call sp_area_master_get_district_autocomplete(:stateId,:districtName)}", nativeQuery = true)
    List<Map<String, Object>> getDistrictAutoComplete(@Param("stateId")Long stateId,
                                                      @Param("districtName")String districtName);

    @Query(value = "{call sp_area_master_get_tehsil_autocomplete(:districtId,:tehsilName)}", nativeQuery = true)
    List<Map<String, Object>> getTehsilAutoComplete(@Param("districtId")Long districtId,
                                                    @Param("tehsilName")String tehsilName);

    @Query(value = "{call sp_area_master_get_city_autocomplete(:tehsilId,:cityName)}", nativeQuery = true)
    List<Map<String, Object>> getCityAutoComplete(@Param("tehsilId")Long tehsilId,
                                                    @Param("cityName")String cityName);

    @Query(value = "{call sp_area_master_pincode_auto_complete(:cityId,:pincode)}", nativeQuery = true)
    List<Map<String, Object>> getSparesPinCodeAutoComplete(@Param("cityId")Long cityId,
                                                    @Param("pincode")String pincode);
    
    @Query(value = "{call sp_getPinCode(:pincode,:cityId)}", nativeQuery = true)
    List<Map<String, Object>> getPinCodeAutoComplete(@Param("pincode")String pincode,
    												 @Param("cityId")Long cityId);
    
    @Query(value = "{call sp_area_master_post_office_autocomplete(:pinCodeId,:postOffice)}", nativeQuery = true)
    List<Map<String, Object>> getPostOfficeAutoComplete(@Param("pinCodeId")Long pinCodeId,
                                                    @Param("postOffice")String postOffice);







}




