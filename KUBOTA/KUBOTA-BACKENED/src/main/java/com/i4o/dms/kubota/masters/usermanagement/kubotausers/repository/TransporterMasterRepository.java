package com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.TransporterMaster;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.dto.TransporterSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TransporterMasterRepository extends JpaRepository<TransporterMaster, Long> {


    @Query(value="{call sp_mt_transporter_title_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> title();

    @Query( value = "{call sp_mt_transporter_name_autocomplete(:transporterName)}",nativeQuery = true)
    List<Map<String,Object>> findByTransporterNameContaining(@Param("transporterName") String transporterName);

    @Query( value = "{call sp_mt_transporter_code_autocomplete(:code)}",nativeQuery = true)
    List<Map<String,Object>> findByCodeContaining(@Param("code") String code);

    @Query(value = "{call sp_mt_transporter_search(:code,:transporterName,:page,:size)}",nativeQuery = true)
    List<TransporterSearchResponse> searchTransporter(@Param("code") String code,
                                                      @Param("transporterName") String transporterName,
                                                      @Param("page")Integer page,
                                                      @Param("size")Integer size);

    @Query(value = "{call sp_mt_transporter_search_count(:code,:transporterName,:page,:size)}",nativeQuery = true)
    Long searchTransporterCount(@Param("code") String code,
                                                      @Param("transporterName") String transporterName,
                                                      @Param("page")Integer page,
                                                      @Param("size")Integer size);

}
