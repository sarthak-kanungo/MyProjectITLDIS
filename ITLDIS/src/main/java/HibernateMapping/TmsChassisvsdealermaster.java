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
@Table(name = "TMS_chassisvsdealermaster")
@NamedQueries({
    @NamedQuery(name = "TmsChassisvsdealermaster.findAll", query = "SELECT t FROM TmsChassisvsdealermaster t"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findById", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.id = :id"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByVinNo", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.vinNo = :vinNo"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByDealerCode", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.dealerCode = :dealerCode"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByModelCode", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.modelCode = :modelCode"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByModelfamily", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.modelfamily = :modelfamily"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByModelfamilyCode", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.modelfamilyCode = :modelfamilyCode"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByModelfamilyDesc", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.modelfamilyDesc = :modelfamilyDesc"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByPdiStatus", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.pdiStatus = :pdiStatus"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByEngineNo", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.engineNo = :engineNo"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByIsserverSync", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.isserverSync = :isserverSync"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findByLastUpdateDate", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.lastUpdateDate = :lastUpdateDate"),
    @NamedQuery(name = "TmsChassisvsdealermaster.findBySaleDate", query = "SELECT t FROM TmsChassisvsdealermaster t WHERE t.saleDate = :saleDate")})
public class TmsChassisvsdealermaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "vinNo")
    private String vinNo;
    @Column(name = "dealerCode")
    private String dealerCode;
    @Column(name = "modelCode")
    private String modelCode;
    @Column(name = "Modelfamily")
    private String modelfamily;
    @Column(name = "modelfamilyCode")
    private String modelfamilyCode;
    @Column(name = "modelfamilyDesc")
    private String modelfamilyDesc;
    @Column(name = "PDI_STATUS")
    private String pdiStatus;
    @Column(name = "EngineNo")
    private String engineNo;
    @Column(name = "Is_serverSync")
    private Character isserverSync;
    @Column(name = "lastUpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "SaleDate")
    private String saleDate;

    public TmsChassisvsdealermaster() {
    }

    public TmsChassisvsdealermaster(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelfamily() {
        return modelfamily;
    }

    public void setModelfamily(String modelfamily) {
        this.modelfamily = modelfamily;
    }

    public String getModelfamilyCode() {
        return modelfamilyCode;
    }

    public void setModelfamilyCode(String modelfamilyCode) {
        this.modelfamilyCode = modelfamilyCode;
    }

    public String getModelfamilyDesc() {
        return modelfamilyDesc;
    }

    public void setModelfamilyDesc(String modelfamilyDesc) {
        this.modelfamilyDesc = modelfamilyDesc;
    }

    public String getPdiStatus() {
        return pdiStatus;
    }

    public void setPdiStatus(String pdiStatus) {
        this.pdiStatus = pdiStatus;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Character getIsserverSync() {
        return isserverSync;
    }

    public void setIsserverSync(Character isserverSync) {
        this.isserverSync = isserverSync;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
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
        if (!(object instanceof TmsChassisvsdealermaster)) {
            return false;
        }
        TmsChassisvsdealermaster other = (TmsChassisvsdealermaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.TmsChassisvsdealermaster[id=" + id + "]";
    }

}
