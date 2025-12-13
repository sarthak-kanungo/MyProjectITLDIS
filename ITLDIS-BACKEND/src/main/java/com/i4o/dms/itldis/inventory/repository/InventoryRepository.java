package com.i4o.dms.itldis.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.inventory.domain.InventoryItem;
import java.util.List;

/**
 * Inventory Repository
 * TODO: Add custom query methods based on legacy DAO methods
 */
@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    
    /**
     * Find inventory items by dealer code
     */
    List<InventoryItem> findByDealerCode(String dealerCode);
    
    /**
     * Search inventory items
     */
    @Query("SELECT i FROM InventoryItem i WHERE i.dealerCode = :dealerCode AND " +
           "(i.partNo LIKE %:searchTerm% OR i.partName LIKE %:searchTerm%)")
    List<InventoryItem> searchInventory(@Param("dealerCode") String dealerCode, 
                                       @Param("searchTerm") String searchTerm);
    
    // TODO: Add more query methods based on legacy DAO methods
}
