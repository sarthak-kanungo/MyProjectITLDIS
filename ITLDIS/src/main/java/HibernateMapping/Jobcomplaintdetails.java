/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_jobcomplaintdetails")

public class Jobcomplaintdetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CMP_NO")
    private String cmpNo;
    @Basic(optional = false)
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Basic(optional = false)
    @Column(name = "CMP_SERIAL_ID")
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cmpId;
    @Column(name = "CustVerbatim")
    private String custVerbatim;
    @Column(name = "AggCode")
    private String aggCode;
    @Column(name = "SubAggCode")
    private String subAggCode;
    @Column(name = "subassemblyCode")
    private String subassemblyCode;
    @Column(name = "DefectCode")
    private String defectCode;
    @Column(name = "CauseCode")
    private String causeCode;
    @Column(name = "foremanObsv")
    private String foremanObsv;
    @Column(name = "AppCode")
    private String appCode;
    @Column(name = "BiPart")
    private String biPart;
    @Column(name = "AtmCase")
    private String atmCase;

    public Jobcomplaintdetails() {
    }

    public Jobcomplaintdetails(String cmpNo) {
        this.cmpNo = cmpNo;
    }

    public Jobcomplaintdetails(String cmpNo, String jobCardNo, int cmpId) {
        this.cmpNo = cmpNo;
        this.jobCardNo = jobCardNo;
        this.cmpId = cmpId;
    }

    public String getCmpNo() {
        return cmpNo;
    }

    public void setCmpNo(String cmpNo) {
        this.cmpNo = cmpNo;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public int getCmpId() {
        return cmpId;
    }

    public void setCmpId(int cmpId) {
        this.cmpId = cmpId;
    }

    

    public String getCustVerbatim() {
        return custVerbatim;
    }

    public void setCustVerbatim(String custVerbatim) {
        this.custVerbatim = custVerbatim;
    }

    public String getAggCode() {
        return aggCode;
    }

    public void setAggCode(String aggCode) {
        this.aggCode = aggCode;
    }

    public String getSubAggCode() {
        return subAggCode;
    }

    public void setSubAggCode(String subAggCode) {
        this.subAggCode = subAggCode;
    }

    public String getSubassemblyCode() {
        return subassemblyCode;
    }

    public void setSubassemblyCode(String subassemblyCode) {
        this.subassemblyCode = subassemblyCode;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(String causeCode) {
        this.causeCode = causeCode;
    }

    public String getForemanObsv() {
        return foremanObsv;
    }

    public void setForemanObsv(String foremanObsv) {
        this.foremanObsv = foremanObsv;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmpNo != null ? cmpNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobcomplaintdetails)) {
            return false;
        }
        Jobcomplaintdetails other = (Jobcomplaintdetails) object;
        if ((this.cmpNo == null && other.cmpNo != null) || (this.cmpNo != null && !this.cmpNo.equals(other.cmpNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Jobcomplaintdetails[cmpNo=" + cmpNo + "]";
    }

	public String getBiPart() {
		return biPart;
	}

	public void setBiPart(String biPart) {
		this.biPart = biPart;
	}

	public String getAtmCase() {
		return atmCase;
	}

	public void setAtmCase(String atmCase) {
		this.atmCase = atmCase;
	}
    
    

}
