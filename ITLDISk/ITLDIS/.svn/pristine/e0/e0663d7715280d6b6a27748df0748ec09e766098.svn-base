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
@Table(name = "SW_installation_details")
@NamedQueries({
    @NamedQuery(name = "InstallationDetails.findAll", query = "SELECT i FROM InstallationDetails i"),
    @NamedQuery(name = "InstallationDetails.findByINSNo", query = "SELECT i FROM InstallationDetails i WHERE i.iNSNo = :iNSNo"),
    @NamedQuery(name = "InstallationDetails.findByDealercode", query = "SELECT i FROM InstallationDetails i WHERE i.dealercode = :dealercode"),
    @NamedQuery(name = "InstallationDetails.findByVinNo", query = "SELECT i FROM InstallationDetails i WHERE i.vinNo = :vinNo"),
    @NamedQuery(name = "InstallationDetails.findByEngineNo", query = "SELECT i FROM InstallationDetails i WHERE i.engineNo = :engineNo"),
    @NamedQuery(name = "InstallationDetails.findByInsDate", query = "SELECT i FROM InstallationDetails i WHERE i.insDate = :insDate"),
    @NamedQuery(name = "InstallationDetails.findByBuyerName", query = "SELECT i FROM InstallationDetails i WHERE i.buyerName = :buyerName"),
    @NamedQuery(name = "InstallationDetails.findByVisitDate", query = "SELECT i FROM InstallationDetails i WHERE i.visitDate = :visitDate"),
    @NamedQuery(name = "InstallationDetails.findByDeliveryDate", query = "SELECT i FROM InstallationDetails i WHERE i.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "InstallationDetails.findByHmr", query = "SELECT i FROM InstallationDetails i WHERE i.hmr = :hmr"),
    @NamedQuery(name = "InstallationDetails.findByMajorApplications", query = "SELECT i FROM InstallationDetails i WHERE i.majorApplications = :majorApplications"),
    @NamedQuery(name = "InstallationDetails.findByAccessories", query = "SELECT i FROM InstallationDetails i WHERE i.accessories = :accessories"),
    
    @NamedQuery(name = "InstallationDetails.findByFamilyMembers", query = "SELECT i FROM InstallationDetails i WHERE i.familyMembers = :familyMembers"),
    @NamedQuery(name = "InstallationDetails.findByOthertractorDetail", query = "SELECT i FROM InstallationDetails i WHERE i.othertractorDetail = :othertractorDetail"),
    @NamedQuery(name = "InstallationDetails.findByPaymentMode", query = "SELECT i FROM InstallationDetails i WHERE i.paymentMode = :paymentMode"),
    @NamedQuery(name = "InstallationDetails.findByBankName", query = "SELECT i FROM InstallationDetails i WHERE i.bankName = :bankName"),
    @NamedQuery(name = "InstallationDetails.findByPhotographFileName", query = "SELECT i FROM InstallationDetails i WHERE i.photographFileName = :photographFileName"),
    @NamedQuery(name = "InstallationDetails.findByIsServerSync", query = "SELECT i FROM InstallationDetails i WHERE i.isServerSync = :isServerSync"),
    @NamedQuery(name = "InstallationDetails.findByLastSyncDate", query = "SELECT i FROM InstallationDetails i WHERE i.lastSyncDate = :lastSyncDate"),
    @NamedQuery(name = "InstallationDetails.findByCreatedBy", query = "SELECT i FROM InstallationDetails i WHERE i.createdBy = :createdBy"),
    @NamedQuery(name = "InstallationDetails.findByCreatedOn", query = "SELECT i FROM InstallationDetails i WHERE i.createdOn = :createdOn"),
    @NamedQuery(name = "InstallationDetails.findByModifiedBy", query = "SELECT i FROM InstallationDetails i WHERE i.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "InstallationDetails.findByModifiedOn", query = "SELECT i FROM InstallationDetails i WHERE i.modifiedOn = :modifiedOn")})
public class InstallationDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "INS_No")
    private String iNSNo;
    @Column(name = "Dealercode")
    private String dealercode;
    @Column(name = "VinNo")
    private String vinNo;
    @Column(name = "EngineNo")
    private String engineNo;
    @Column(name = "INS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insDate;
    @Column(name = "CustomerName")
    private String buyerName;
    @Column(name = "VisitDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitDate;
    @Column(name = "DeliveryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Column(name = "HMR")
    private Integer hmr;
    @Column(name = "majorApplications")
    private String majorApplications;
    @Column(name = "Accessories")
    private String accessories;
   
    @Column(name = "FamilyMembers")
    private Integer familyMembers;
    @Column(name = "OthertractorDetail")
    private String othertractorDetail;
    @Column(name = "PaymentMode")
    private String paymentMode;
    @Column(name = "BankName")
    private String bankName;
    @Column(name = "PhotographFileName")
    private String photographFileName;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Column(name = "Remarks")
    private String remarks;//Remarks
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "ContactName")
    private String contactName;//Remarks
    @Column(name = "ContactNo")
    private String contactNo;//Remarks
    @Column(name = "RelationshipCustomer")
    private String relationshipCustomer;//Remarks
    @Column(name = "isTMSSync")
    private Character isTMSSync;//Remarks
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TMSSyncDate")
    private Date TMSSyncDate;//Remarks

    @JoinColumn(name = "MechanicDealerKey", referencedColumnName = "MechanicDealerKey")
    @ManyToOne
    private MSWDmechanicmaster mechanicDealerKey;

    public InstallationDetails() {
    }

    public Date getTMSSyncDate()
    {
        return TMSSyncDate;
    }

    public void setTMSSyncDate(Date TMSSyncDate)
    {
        this.TMSSyncDate = TMSSyncDate;
    }

    public Character getIsTMSSync()
    {
        return isTMSSync;
    }

    public void setIsTMSSync(Character isTMSSync)
    {
        this.isTMSSync = isTMSSync;
    }

    public InstallationDetails(String iNSNo) {
        this.iNSNo = iNSNo;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactNo()
    {
        return contactNo;
    }

    public void setContactNo(String contactNo)
    {
        this.contactNo = contactNo;
    }

    public String getRelationshipCustomer()
    {
        return relationshipCustomer;
    }

    public void setRelationshipCustomer(String relationshipCustomer)
    {
        this.relationshipCustomer = relationshipCustomer;
    }

    

    public String getINSNo() {
        return iNSNo;
    }

    public void setINSNo(String iNSNo) {
        this.iNSNo = iNSNo;
    }

    public String getDealercode() {
        return dealercode;
    }

    public void setDealercode(String dealercode) {
        this.dealercode = dealercode;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getHmr() {
        return hmr;
    }

    public void setHmr(Integer hmr) {
        this.hmr = hmr;
    }

    public String getMajorApplications() {
        return majorApplications;
    }

    public void setMajorApplications(String majorApplications) {
        this.majorApplications = majorApplications;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    

    public Integer getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Integer familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getOthertractorDetail() {
        return othertractorDetail;
    }

    public void setOthertractorDetail(String othertractorDetail) {
        this.othertractorDetail = othertractorDetail;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPhotographFileName() {
        return photographFileName;
    }

    public void setPhotographFileName(String photographFileName) {
        this.photographFileName = photographFileName;
    }

    public Character getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public MSWDmechanicmaster getMechanicDealerKey() {
        return mechanicDealerKey;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public void setMechanicDealerKey(MSWDmechanicmaster mechanicDealerKey) {
        this.mechanicDealerKey = mechanicDealerKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iNSNo != null ? iNSNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstallationDetails)) {
            return false;
        }
        InstallationDetails other = (InstallationDetails) object;
        if ((this.iNSNo == null && other.iNSNo != null) || (this.iNSNo != null && !this.iNSNo.equals(other.iNSNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InstallationDetails[iNSNo=" + iNSNo + "]";
    }

}
