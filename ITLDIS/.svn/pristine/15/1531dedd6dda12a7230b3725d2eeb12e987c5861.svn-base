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
@Table(name = "TMS_chassisvsinsmaster")
@NamedQueries({
    @NamedQuery(name = "TmsChassisvsinsmaster.findAll", query = "SELECT t FROM TmsChassisvsinsmaster t"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findById", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.id = :id"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByVinNo", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.vinNo = :vinNo"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByDealerCode", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.dealerCode = :dealerCode"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByCustomerName", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.customerName = :customerName"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByVillageName", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.villageName = :villageName"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByTaluka", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.taluka = :taluka"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByTehsil", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.tehsil = :tehsil"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByDistrict", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.district = :district"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByPincode", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.pincode = :pincode"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByState", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.state = :state"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByCountry", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.country = :country"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByMobilePh", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.mobilePh = :mobilePh"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByLandlineNo", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.landlineNo = :landlineNo"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByEmailId", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.emailId = :emailId"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findBySaleDate", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.saleDate = :saleDate"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByINSStatus", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.iNSStatus = :iNSStatus"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByIsServersync", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.isServersync = :isServersync"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByLastUpdateDate", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.lastUpdateDate = :lastUpdateDate"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findBySizeLandHolding", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.sizeLandHolding = :sizeLandHolding"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByMainCrops", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.mainCrops = :mainCrops"),
    @NamedQuery(name = "TmsChassisvsinsmaster.findByOccupation", query = "SELECT t FROM TmsChassisvsinsmaster t WHERE t.occupation = :occupation")})
public class TmsChassisvsinsmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "vinNo")
    private String vinNo;
    @Column(name = "dealerCode")
    private String dealerCode;
    @Column(name = "CustomerName")
    private String customerName;
    @Column(name = "villageName")
    private String villageName;
    @Column(name = "Taluka")
    private String taluka;
    @Column(name = "Tehsil")
    private String tehsil;
    @Column(name = "District")
    private String district;
    @Column(name = "Pincode")
    private Long pincode;
    @Column(name = "State")
    private String state;
    @Column(name = "Country")
    private String country;
    @Column(name = "MobilePh")
    private Long mobilePh;
    @Column(name = "LandlineNo")
    private String landlineNo;
    @Column(name = "emailId")
    private String emailId;
    @Column(name = "saleDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
    @Column(name = "INS_Status")
    private String iNSStatus;
    @Column(name = "isServer_sync")
    private Character isServersync;
    @Column(name = "lastUpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "sizeLandHolding")
    private String sizeLandHolding;
    @Column(name = "mainCrops")
    private String mainCrops;
    @Column(name = "occupation")
    private String occupation;

    public TmsChassisvsinsmaster() {
    }

    public TmsChassisvsinsmaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getMobilePh() {
        return mobilePh;
    }

    public void setMobilePh(Long mobilePh) {
        this.mobilePh = mobilePh;
    }

    public String getLandlineNo() {
        return landlineNo;
    }

    public void setLandlineNo(String landlineNo) {
        this.landlineNo = landlineNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getINSStatus() {
        return iNSStatus;
    }

    public void setINSStatus(String iNSStatus) {
        this.iNSStatus = iNSStatus;
    }

    public Character getIsServersync() {
        return isServersync;
    }

    public void setIsServersync(Character isServersync) {
        this.isServersync = isServersync;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getSizeLandHolding() {
        return sizeLandHolding;
    }

    public void setSizeLandHolding(String sizeLandHolding) {
        this.sizeLandHolding = sizeLandHolding;
    }

    public String getMainCrops() {
        return mainCrops;
    }

    public void setMainCrops(String mainCrops) {
        this.mainCrops = mainCrops;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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
        if (!(object instanceof TmsChassisvsinsmaster)) {
            return false;
        }
        TmsChassisvsinsmaster other = (TmsChassisvsinsmaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.TmsChassisvsinsmaster[id=" + id + "]";
    }

}
