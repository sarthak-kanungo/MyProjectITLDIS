/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "partprice")
@NamedQueries({
    @NamedQuery(name = "Partprice.findAll", query = "SELECT p FROM Partprice p"),
    @NamedQuery(name = "Partprice.findByPartNumber", query = "SELECT p FROM Partprice p WHERE p.partNumber = :partNumber"),
    @NamedQuery(name = "Partprice.findByPartPrice", query = "SELECT p FROM Partprice p WHERE p.partPrice = :partPrice"),
    @NamedQuery(name = "Partprice.findByRevNo", query = "SELECT p FROM Partprice p WHERE p.revNo = :revNo"),
    @NamedQuery(name = "Partprice.findByFiledBy", query = "SELECT p FROM Partprice p WHERE p.filedBy = :filedBy"),
    @NamedQuery(name = "Partprice.findByLastUpdatedDate", query = "SELECT p FROM Partprice p WHERE p.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Partprice.findById", query = "SELECT p FROM Partprice p WHERE p.id = :id")})
public class Partprice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "part_Number")
    private String partNumber;
    @Column(name = "part_price")
    private Double partPrice;
    @Column(name = "revNo")
    private BigInteger revNo;
    @Column(name = "filedBy")
    private String filedBy;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Partprice() {
    }

    public Partprice(Integer id) {
        this.id = id;
    }

    public Partprice(Integer id, String partNumber) {
        this.id = id;
        this.partNumber = partNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(Double partPrice) {
        this.partPrice = partPrice;
    }

    public BigInteger getRevNo() {
        return revNo;
    }

    public void setRevNo(BigInteger revNo) {
        this.revNo = revNo;
    }

    public String getFiledBy() {
        return filedBy;
    }

    public void setFiledBy(String filedBy) {
        this.filedBy = filedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partprice)) {
            return false;
        }
        Partprice other = (Partprice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Partprice[id=" + id + "]";
    }

}
