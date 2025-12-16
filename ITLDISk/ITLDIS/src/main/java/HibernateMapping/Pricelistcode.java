/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ashutosh.kumar
 */
@Entity
@Table(name = "SP_PRICELIST_CODE")
public class Pricelistcode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRICELIST_CODE")
    private String pricelistCode;

    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(optional = false)
    @Column(name = "CURRENCY_TYPE")
    private String currencyType;

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPricelistCode() {
        return pricelistCode;
    }

    public void setPricelistCode(String pricelistCode) {
        this.pricelistCode = pricelistCode;
    }
   
    
    
}
