package HibernateMapping;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.struts.action.ActionForm;


/**
 *
 * @author vijay.mishra
 */
@Entity
@org.hibernate.annotations.Subselect("select * from getalljobcarddata")
@org.hibernate.annotations.Synchronize("SW_jobcarddetails")
public class ViewJobCard extends ActionForm {
//JobCardNo, vinno, PayeeName, MobilePhone, status
    @Id
    @Column(name = "JobCardNo")
    private String JobCardNo;

    @Column(name = "DealerCode")
    private String DealerCode;

    @Column(name = "CreatedBy")
    private String CreatedBy;

    @Column(name = "vinno")
    private String vinno;

    @Column(name = "PayeeName")
    private String PayeeName;

    @Column(name = "MobilePhone")
    private String MobilePhone;

    @Column(name = "status")
    private String status;

   
    public String getJobCardNo() {
        return JobCardNo;
    }

    public void setJobCardNo(String JobCardNo) {
        this.JobCardNo = JobCardNo;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public String getPayeeName() {
        return PayeeName;
    }

    public void setPayeeName(String PayeeName) {
        this.PayeeName = PayeeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVinno() {
        return vinno;
    }

    public void setVinno(String vinno) {
        this.vinno = vinno;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public String getDealerCode() {
        return DealerCode;
    }

    public void setDealerCode(String DealerCode) {
        this.DealerCode = DealerCode;
    }

 

}