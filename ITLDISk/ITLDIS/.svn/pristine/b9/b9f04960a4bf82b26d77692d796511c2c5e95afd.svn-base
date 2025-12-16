/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_pdi_checklist")

public class PdiChecklist implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PdiChecklistPK pdiChecklistPK;
    @Column(name = "orderSeq")
    private Integer orderSeq;
    @Column(name = "ok_status")
    private String okStatus;
    @Column(name = "ActionTaken")
    private String actionTaken;
    @Column(name = "Remarks")
    private String remarks;
    @Column(name = "Final_status")
    private String finalstatus;
    @JoinColumn(name = "subContentId", referencedColumnName = "SUB_CONTENT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ItlSubContentMaster formsubcontentmaster;
    @JoinColumn(name = "content_id", referencedColumnName = "CONTENT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ItlContentMaster itlContentMaster;

    public PdiChecklist() {
    }

    public PdiChecklist(PdiChecklistPK pdiChecklistPK) {
        this.pdiChecklistPK = pdiChecklistPK;
    }

    public PdiChecklist(String pDINo, long contentId, long subContentId) {
        this.pdiChecklistPK = new PdiChecklistPK(pDINo, contentId, subContentId);
    }

    public PdiChecklistPK getPdiChecklistPK() {
        return pdiChecklistPK;
    }

    public void setPdiChecklistPK(PdiChecklistPK pdiChecklistPK) {
        this.pdiChecklistPK = pdiChecklistPK;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getOkStatus() {
        return okStatus;
    }

    public void setOkStatus(String okStatus) {
        this.okStatus = okStatus;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFinalstatus() {
        return finalstatus;
    }

    public void setFinalstatus(String finalstatus) {
        this.finalstatus = finalstatus;
    }

    public ItlSubContentMaster getFormsubcontentmaster() {
        return formsubcontentmaster;
    }

    public void setFormsubcontentmaster(ItlSubContentMaster formsubcontentmaster) {
        this.formsubcontentmaster = formsubcontentmaster;
    }

    public ItlContentMaster getItlContentMaster() {
        return itlContentMaster;
    }

    public void setItlContentMaster(ItlContentMaster itlContentMaster) {
        this.itlContentMaster = itlContentMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdiChecklistPK != null ? pdiChecklistPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdiChecklist)) {
            return false;
        }
        PdiChecklist other = (PdiChecklist) object;
        if ((this.pdiChecklistPK == null && other.pdiChecklistPK != null) || (this.pdiChecklistPK != null && !this.pdiChecklistPK.equals(other.pdiChecklistPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.PdiChecklist[pdiChecklistPK=" + pdiChecklistPK + "]";
    }

}
