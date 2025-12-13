/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_checklist_subcontent_master")

public class ItlSubContentMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SUB_CONTENT_ID")
    private Long subContentId;
    @Column(name = "SUB_CONTENT_DESC")
    private String subContentDesc;
    @Column(name = "isActive")
    private Character isActive;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String LastUpdatedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formsubcontentmaster")
    private Collection<PdiChecklist> pdiChecklistCollection;
    @OneToMany(mappedBy = "subContentId")
    private Collection<ItlFormContent> formcontentCollection;
    @JoinColumn(name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    @ManyToOne(optional = false)
    private ItlContentMaster contentId;

    public ItlSubContentMaster() {
    }

    public ItlSubContentMaster(Long subContentId) {
        this.subContentId = subContentId;
    }

    public Long getSubContentId() {
        return subContentId;
    }

    public void setSubContentId(Long subContentId) {
        this.subContentId = subContentId;
    }

    public String getSubContentDesc() {
        return subContentDesc;
    }

    public void setSubContentDesc(String subContentDesc) {
        this.subContentDesc = subContentDesc;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    public String getLastUpdatedBy() {
        return LastUpdatedBy;
    }

    public void setLastUpdatedBy(String LastUpdatedBy) {
        this.LastUpdatedBy = LastUpdatedBy;
    }
    public Collection<PdiChecklist> getPdiChecklistCollection() {
        return pdiChecklistCollection;
    }

    public void setPdiChecklistCollection(Collection<PdiChecklist> pdiChecklistCollection) {
        this.pdiChecklistCollection = pdiChecklistCollection;
    }

    public Collection<ItlFormContent> getFormcontentCollection() {
        return formcontentCollection;
    }

    public void setFormcontentCollection(Collection<ItlFormContent> formcontentCollection) {
        this.formcontentCollection = formcontentCollection;
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
        hash += (subContentId != null ? subContentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItlSubContentMaster)) {
            return false;
        }
        ItlSubContentMaster other = (ItlSubContentMaster) object;
        if ((this.subContentId == null && other.subContentId != null) || (this.subContentId != null && !this.subContentId.equals(other.subContentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.ItluSubContentMaster[subContentId=" + subContentId + "]";
    }

}
