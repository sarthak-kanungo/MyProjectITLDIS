/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SP_WIP_Inventory_View")
@NamedQueries({
    @NamedQuery(name = "SPWIPInventoryView.findAll", query = "SELECT s FROM SPWIPInventoryView s"),
    @NamedQuery(name = "SPWIPInventoryView.findByDealerCode", query = "SELECT s FROM SPWIPInventoryView s WHERE s.dealerCode = :dealerCode"),
    @NamedQuery(name = "SPWIPInventoryView.findByPartNo", query = "SELECT s FROM SPWIPInventoryView s WHERE s.partNo = :partNo"),
    @NamedQuery(name = "SPWIPInventoryView.findByQty", query = "SELECT s FROM SPWIPInventoryView s WHERE s.qty = :qty")})
public class SPWIPInventoryView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DealerCode")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "part_no")
    private String partNo;
    @Column(name = "Qty")
    private Double qty;

    public SPWIPInventoryView() {
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

}
