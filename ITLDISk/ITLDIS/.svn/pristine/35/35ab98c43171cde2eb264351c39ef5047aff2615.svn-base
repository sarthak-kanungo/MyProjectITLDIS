/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ashutosh.kumar
 */
@Entity
@Table(name = "UM_USER_PRICELIST")
public class Userpricelist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private String userId;
    @Basic(optional = false)
    @Column(name = "PRICELIST_CODE")
    private String pricelistCode;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATED_BY")
    private String lastCreatedBy;
    @Column(name = "LAST_UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    public String getLastCreatedBy() {
        return lastCreatedBy;
    }

    public void setLastCreatedBy(String lastCreatedBy) {
        this.lastCreatedBy = lastCreatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getPricelistCode() {
        return pricelistCode;
    }

    public void setPricelistCode(String pricelistCode) {
        this.pricelistCode = pricelistCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
}
