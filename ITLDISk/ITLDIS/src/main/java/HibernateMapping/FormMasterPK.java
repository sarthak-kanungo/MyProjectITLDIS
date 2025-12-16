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
public class FormMasterPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "formid")
    private int formid;
    @Basic(optional = false)
    @Column(name = "Modelcode")
    private String modelcode;
    @Basic(optional = false)
    @Column(name = "jobtypeid")
    private int jobtypeid;

    public FormMasterPK()
    {
    }

    public FormMasterPK(int formid, String modelcode, int jobtypeid)
    {
        this.formid = formid;
        this.modelcode = modelcode;
        this.jobtypeid = jobtypeid;
    }

    public int getFormid()
    {
        return formid;
    }

    public void setFormid(int formid)
    {
        this.formid = formid;
    }

    public String getModelcode()
    {
        return modelcode;
    }

    public void setModelcode(String modelcode)
    {
        this.modelcode = modelcode;
    }

    public int getJobtypeid()
    {
        return jobtypeid;
    }

    public void setJobtypeid(int jobtypeid)
    {
        this.jobtypeid = jobtypeid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) formid;
        hash += (modelcode != null ? modelcode.hashCode() : 0);
        hash += (int) jobtypeid;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormMasterPK))
        {
            return false;
        }
        FormMasterPK other = (FormMasterPK) object;
        if (this.formid != other.formid)
        {
            return false;
        }
        if ((this.modelcode == null && other.modelcode != null) || (this.modelcode != null && !this.modelcode.equals(other.modelcode)))
        {
            return false;
        }
        if (this.jobtypeid != other.jobtypeid)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "HibernateMapping.FormMasterPK[formid=" + formid + ", modelcode=" + modelcode + ", jobtypeid=" + jobtypeid + "]";
    }

}
