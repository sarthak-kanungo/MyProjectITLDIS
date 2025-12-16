/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author prashant.kumar
 */
@Embeddable
public class CmTaxMasterDealerPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CommodityCode")
    private String commodityCode;
    @Basic(optional = false)
    @Column(name = "DealerCode")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "EffectiveDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;

    public CmTaxMasterDealerPK() {
    }

    public CmTaxMasterDealerPK(String commodityCode, String dealerCode, Date effectiveDate)
    {
        this.commodityCode = commodityCode;
        this.dealerCode = dealerCode;
        this.effectiveDate = effectiveDate;
    }
    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CmTaxMasterDealerPK other = (CmTaxMasterDealerPK) obj;
        if ((this.commodityCode == null) ? (other.commodityCode != null) : !this.commodityCode.equals(other.commodityCode)) {
            return false;
        }
        if ((this.dealerCode == null) ? (other.dealerCode != null) : !this.dealerCode.equals(other.dealerCode)) {
            return false;
        }
        if (this.effectiveDate != other.effectiveDate && (this.effectiveDate == null || !this.effectiveDate.equals(other.effectiveDate))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.commodityCode != null ? this.commodityCode.hashCode() : 0);
        hash = 47 * hash + (this.dealerCode != null ? this.dealerCode.hashCode() : 0);
        hash = 47 * hash + (this.effectiveDate != null ? this.effectiveDate.hashCode() : 0);
        return hash;
    }   

    @Override
    public String toString() {
        return "HibernateMapping.CmTaxMasterDealerPK[commodityCode="+ commodityCode + ", dealerCode=" + dealerCode + ", effectiveDate=" + effectiveDate + "]";
    }

}
