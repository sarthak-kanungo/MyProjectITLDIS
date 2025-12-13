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
@Table(name = "MSW_checklist_content_master")

public class ItlContentMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)@Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CONTENT_ID")
    private Long contentId;
    @Column(name = "CONTENT_DESC")
    private String contentDesc;
    @Column(name = "isActive")
    private Character isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    @Column(name = "LastUpdatedBy")
     private String LastUpdatedBy;

  

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itlContentMaster")
    private Collection<PdiChecklist> pdiChecklistCollection;
    @OneToMany(mappedBy = "contentId")
    private Collection<ItlFormContent> formcontentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contentId")
    private Collection<ItlSubContentMaster> formsubcontentmasterCollection;

    public ItlContentMaster() {
    }

    public ItlContentMaster(Long contentId) {
        this.contentId = contentId;
    }

    public ItlContentMaster(Long contentId, Date lastUpdatedDate) {
        this.contentId = contentId;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
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

    public Collection<ItlSubContentMaster> getFormsubcontentmasterCollection() {
        return formsubcontentmasterCollection;
    }

    public void setFormsubcontentmasterCollection(Collection<ItlSubContentMaster> formsubcontentmasterCollection) {
        this.formsubcontentmasterCollection = formsubcontentmasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contentId != null ? contentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItlContentMaster)) {
            return false;
        }
        ItlContentMaster other = (ItlContentMaster) object;
        if ((this.contentId == null && other.contentId != null) || (this.contentId != null && !this.contentId.equals(other.contentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.ItlContentMaster[contentId=" + contentId + "]";
    }

}
