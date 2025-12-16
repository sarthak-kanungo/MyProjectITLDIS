/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vijay.mishra
 */
@Embeddable
public class JobcmpconsequencesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CMP_NO")
    private String cmpNo;
    @Basic(optional = false)
    @Column(name = "ConsequenceCode")
    private String consequenceCode;

    public JobcmpconsequencesPK() {
    }

    public JobcmpconsequencesPK(String cmpNo, String consequenceCode) {
        this.cmpNo = cmpNo;
        this.consequenceCode = consequenceCode;
    }

    public String getCmpNo() {
        return cmpNo;
    }

    public void setCmpNo(String cmpNo) {
        this.cmpNo = cmpNo;
    }

    public String getConsequenceCode() {
        return consequenceCode;
    }

    public void setConsequenceCode(String consequenceCode) {
        this.consequenceCode = consequenceCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmpNo != null ? cmpNo.hashCode() : 0);
        hash += (consequenceCode != null ? consequenceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobcmpconsequencesPK)) {
            return false;
        }
        JobcmpconsequencesPK other = (JobcmpconsequencesPK) object;
        if ((this.cmpNo == null && other.cmpNo != null) || (this.cmpNo != null && !this.cmpNo.equals(other.cmpNo))) {
            return false;
        }
        if ((this.consequenceCode == null && other.consequenceCode != null) || (this.consequenceCode != null && !this.consequenceCode.equals(other.consequenceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.JobcmpconsequencesPK[cmpNo=" + cmpNo + ", consequenceCode=" + consequenceCode + "]";
    }

}
