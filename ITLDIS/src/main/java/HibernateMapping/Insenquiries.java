/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_ins_enquiries")
@NamedQueries({
    @NamedQuery(name = "Insenquiries.findAll", query = "SELECT i FROM Insenquiries i"),
    @NamedQuery(name = "Insenquiries.findByINSNo", query = "SELECT i FROM Insenquiries i WHERE i.insenquiriesPK.iNSNo = :iNSNo"),
    @NamedQuery(name = "Insenquiries.findByCustomerName", query = "SELECT i FROM Insenquiries i WHERE i.insenquiriesPK.customerName = :customerName"),
    @NamedQuery(name = "Insenquiries.findByFatherName", query = "SELECT i FROM Insenquiries i WHERE i.fatherName = :fatherName"),
    @NamedQuery(name = "Insenquiries.findByVillage", query = "SELECT i FROM Insenquiries i WHERE i.village = :village"),
    @NamedQuery(name = "Insenquiries.findByContactNo", query = "SELECT i FROM Insenquiries i WHERE i.contactNo = :contactNo"),
    @NamedQuery(name = "Insenquiries.findByCustomerRelationship", query = "SELECT i FROM Insenquiries i WHERE i.customerRelationship = :customerRelationship")})
public class Insenquiries implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsenquiriesPK insenquiriesPK;
    @Basic(optional = false)
    @Column(name = "FatherName")
    private String fatherName;
    @Basic(optional = false)
    @Column(name = "Village")
    private String village;
    @Basic(optional = false)
    @Column(name = "ContactNo")
    private String contactNo;
    @Basic(optional = false)
    @Column(name = "CustomerRelationship")
    private String customerRelationship;

    public Insenquiries() {
    }

    public Insenquiries(InsenquiriesPK insenquiriesPK) {
        this.insenquiriesPK = insenquiriesPK;
    }

    public Insenquiries(InsenquiriesPK insenquiriesPK, String fatherName, String village, String contactNo, String customerRelationship) {
        this.insenquiriesPK = insenquiriesPK;
        this.fatherName = fatherName;
        this.village = village;
        this.contactNo = contactNo;
        this.customerRelationship = customerRelationship;
    }

    public Insenquiries(String iNSNo, String customerName) {
        this.insenquiriesPK = new InsenquiriesPK(iNSNo, customerName);
    }

    public InsenquiriesPK getInsenquiriesPK() {
        return insenquiriesPK;
    }

    public void setInsenquiriesPK(InsenquiriesPK insenquiriesPK) {
        this.insenquiriesPK = insenquiriesPK;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCustomerRelationship() {
        return customerRelationship;
    }

    public void setCustomerRelationship(String customerRelationship) {
        this.customerRelationship = customerRelationship;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insenquiriesPK != null ? insenquiriesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insenquiries)) {
            return false;
        }
        Insenquiries other = (Insenquiries) object;
        if ((this.insenquiriesPK == null && other.insenquiriesPK != null) || (this.insenquiriesPK != null && !this.insenquiriesPK.equals(other.insenquiriesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Insenquiries[insenquiriesPK=" + insenquiriesPK + "]";
    }

}
