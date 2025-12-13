/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SP_PRICE_MASTER")
@NamedQueries({
    @NamedQuery(name = "SpPriceMaster.findAll", query = "SELECT s FROM SpPriceMaster s"),
    @NamedQuery(name = "SpPriceMaster.findByPricelistCode", query = "SELECT s FROM SpPriceMaster s WHERE s.spPriceMasterPK.pricelistCode = :pricelistCode"),
    @NamedQuery(name = "SpPriceMaster.findByItem", query = "SELECT s FROM SpPriceMaster s WHERE s.spPriceMasterPK.item = :item"),
    @NamedQuery(name = "SpPriceMaster.findByEffDate", query = "SELECT s FROM SpPriceMaster s WHERE s.spPriceMasterPK.effDate = :effDate"),
    @NamedQuery(name = "SpPriceMaster.findByExpDate", query = "SELECT s FROM SpPriceMaster s WHERE s.expDate = :expDate"),
    @NamedQuery(name = "SpPriceMaster.findByPrice", query = "SELECT s FROM SpPriceMaster s WHERE s.price = :price")})
public class SpPriceMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpPriceMasterPK spPriceMasterPK;
    @Column(name = "EXP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expDate;
    @Column(name = "PRICE")
    private Double price;
    //Added by satendra
    @Column(name = "ORDER_PRICE")
    private Double orderPrice;

    @JoinColumn(name = "ITEM", referencedColumnName = "part_no", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CatPart catPart;
    public SpPriceMaster() {
    }

    public SpPriceMaster(SpPriceMasterPK spPriceMasterPK) {
        this.spPriceMasterPK = spPriceMasterPK;
    }

    public SpPriceMaster(String pricelistCode, String item, Date effDate) {
        this.spPriceMasterPK = new SpPriceMasterPK(pricelistCode, item, effDate);
    }

    public SpPriceMasterPK getSpPriceMasterPK() {
        return spPriceMasterPK;
    }

    public void setSpPriceMasterPK(SpPriceMasterPK spPriceMasterPK) {
        this.spPriceMasterPK = spPriceMasterPK;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CatPart getCatPart() {
        return catPart;
    }

    public void setCatPart(CatPart catPart) {
        this.catPart = catPart;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spPriceMasterPK != null ? spPriceMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpPriceMaster)) {
            return false;
        }
        SpPriceMaster other = (SpPriceMaster) object;
        if ((this.spPriceMasterPK == null && other.spPriceMasterPK != null) || (this.spPriceMasterPK != null && !this.spPriceMasterPK.equals(other.spPriceMasterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpPriceMaster[spPriceMasterPK=" + spPriceMasterPK + "]";
    }

}
