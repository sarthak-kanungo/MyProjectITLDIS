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
 * @author vijay.mishra
 */
@Embeddable
public class SpPriceMasterPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PRICELIST_CODE")
    private String pricelistCode;
    @Basic(optional = false)
    @Column(name = "ITEM")
    private String item;
    @Basic(optional = false)
    @Column(name = "EFF_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effDate;

    public SpPriceMasterPK() {
    }

    public SpPriceMasterPK(String pricelistCode, String item, Date effDate) {
        this.pricelistCode = pricelistCode;
        this.item = item;
        this.effDate = effDate;
    }

    public String getPricelistCode() {
        return pricelistCode;
    }

    public void setPricelistCode(String pricelistCode) {
        this.pricelistCode = pricelistCode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricelistCode != null ? pricelistCode.hashCode() : 0);
        hash += (item != null ? item.hashCode() : 0);
        hash += (effDate != null ? effDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpPriceMasterPK)) {
            return false;
        }
        SpPriceMasterPK other = (SpPriceMasterPK) object;
        if ((this.pricelistCode == null && other.pricelistCode != null) || (this.pricelistCode != null && !this.pricelistCode.equals(other.pricelistCode))) {
            return false;
        }
        if ((this.item == null && other.item != null) || (this.item != null && !this.item.equals(other.item))) {
            return false;
        }
        if ((this.effDate == null && other.effDate != null) || (this.effDate != null && !this.effDate.equals(other.effDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpPriceMasterPK[pricelistCode=" + pricelistCode + ", item=" + item + ", effDate=" + effDate + "]";
    }

}
