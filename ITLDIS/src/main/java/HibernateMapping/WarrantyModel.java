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
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_warranty_model")

public class WarrantyModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "ModelCode")
    private String modelCode;
    @Column(name = "months")
    private Long months;
    @Column(name = "Hrs")
    private Integer hrs;
    @Column(name = "wty_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wtyStartDate;
    @Column(name = "wty_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wtyEndDate;

    public WarrantyModel() {
    }

    public WarrantyModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Long getMonths() {
        return months;
    }

    public void setMonths(Long months) {
        this.months = months;
    }

    public Integer getHrs() {
        return hrs;
    }

    public void setHrs(Integer hrs) {
        this.hrs = hrs;
    }

    public Date getWtyStartDate() {
        return wtyStartDate;
    }

    public void setWtyStartDate(Date wtyStartDate) {
        this.wtyStartDate = wtyStartDate;
    }

    public Date getWtyEndDate() {
        return wtyEndDate;
    }

    public void setWtyEndDate(Date wtyEndDate) {
        this.wtyEndDate = wtyEndDate;
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
        if (!(object instanceof WarrantyModel)) {
            return false;
        }
        WarrantyModel other = (WarrantyModel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.WarrantyModel[id=" + id + "]";
    }

}
