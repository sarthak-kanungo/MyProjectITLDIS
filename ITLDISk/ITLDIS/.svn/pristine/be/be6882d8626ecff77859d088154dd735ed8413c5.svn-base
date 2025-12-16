/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_form_content")

public class ItlFormContent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "FORM_ID")
    private Integer formId;

    @Column(name = "ORDER_SEQ")
    private Integer orderSeq;
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "fID")
    private Integer fID;
    @JoinColumn(name = "SUB_CONTENT_ID", referencedColumnName = "SUB_CONTENT_ID")
    @ManyToOne
    private ItlSubContentMaster subContentId;
    @JoinColumn(name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    @ManyToOne
    private ItlContentMaster contentId;

    public ItlFormContent() {
    }

    public ItlFormContent(Integer fID) {
        this.fID = fID;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer contentOrder) {
        this.orderSeq = contentOrder;
    }


    public Integer getFID() {
        return fID;
    }

    public void setFID(Integer fID) {
        this.fID = fID;
    }

    public ItlSubContentMaster getSubContentId() {
        return subContentId;
    }

    public void setSubContentId(ItlSubContentMaster subContentId) {
        this.subContentId = subContentId;
    }

    public ItlContentMaster getContentId() {
        return contentId;
    }

    public void setContentId(ItlContentMaster contentId) {
        this.contentId = contentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fID != null ? fID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItlFormContent)) {
            return false;
        }
        ItlFormContent other = (ItlFormContent) object;
        if ((this.fID == null && other.fID != null) || (this.fID != null && !this.fID.equals(other.fID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.ItlFormContent[fID=" + fID + "]";
    }

}
