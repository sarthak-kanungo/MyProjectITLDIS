/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "MSW_form_master")
@NamedQueries(
{
    @NamedQuery(name = "FormMaster.findAll", query = "SELECT f FROM FormMaster f"),
    @NamedQuery(name = "FormMaster.findByFormid", query = "SELECT f FROM FormMaster f WHERE f.formMasterPK.formid = :formid"),
    @NamedQuery(name = "FormMaster.findByModelcode", query = "SELECT f FROM FormMaster f WHERE f.formMasterPK.modelcode = :modelcode"),
    @NamedQuery(name = "FormMaster.findByJobtypeid", query = "SELECT f FROM FormMaster f WHERE f.formMasterPK.jobtypeid = :jobtypeid"),
    @NamedQuery(name = "FormMaster.findByLastModifiedBy", query = "SELECT f FROM FormMaster f WHERE f.lastModifiedBy = :lastModifiedBy"),
    @NamedQuery(name = "FormMaster.findByLastModifiedOn", query = "SELECT f FROM FormMaster f WHERE f.lastModifiedOn = :lastModifiedOn")
})
public class FormMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FormMasterPK formMasterPK;
    @Column(name = "LastModifiedBy")
    private String lastModifiedBy;
    @Column(name = "LastModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;

    public FormMaster()
    {
    }

    public FormMaster(FormMasterPK formMasterPK)
    {
        this.formMasterPK = formMasterPK;
    }

    public FormMaster(int formid, String modelcode, int jobtypeid)
    {
        this.formMasterPK = new FormMasterPK(formid, modelcode, jobtypeid);
    }

    public FormMasterPK getFormMasterPK()
    {
        return formMasterPK;
    }

    public void setFormMasterPK(FormMasterPK formMasterPK)
    {
        this.formMasterPK = formMasterPK;
    }

    public String getLastModifiedBy()
    {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy)
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedOn()
    {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn)
    {
        this.lastModifiedOn = lastModifiedOn;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (formMasterPK != null ? formMasterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormMaster))
        {
            return false;
        }
        FormMaster other = (FormMaster) object;
        if ((this.formMasterPK == null && other.formMasterPK != null) || (this.formMasterPK != null && !this.formMasterPK.equals(other.formMasterPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "HibernateMapping.FormMaster[formMasterPK=" + formMasterPK + "]";
    }

}
