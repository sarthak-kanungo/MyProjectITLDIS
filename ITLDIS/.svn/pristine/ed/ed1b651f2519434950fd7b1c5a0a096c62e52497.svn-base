/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author megha.pandya
 */
@Entity
@Table(name = "UM_USER_DEALER_MATRIX")
@NamedQueries({
    @NamedQuery(name = "UmUserDealerMatrix.findAll", query = "SELECT u FROM UmUserDealerMatrix u"),
    @NamedQuery(name = "UmUserDealerMatrix.findByLoginId", query = "SELECT u FROM UmUserDealerMatrix u WHERE u.loginId = :loginId"),
    @NamedQuery(name = "UmUserDealerMatrix.findByDealerCode", query = "SELECT u FROM UmUserDealerMatrix u WHERE u.dealerCode = :dealerCode")})
public class UmUserDealerMatrix implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LOGIN_ID")
    private String loginId;
    @Id
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;

    public UmUserDealerMatrix() {
    }

    public UmUserDealerMatrix(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealerCode != null ? dealerCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UmUserDealerMatrix)) {
            return false;
        }
        UmUserDealerMatrix other = (UmUserDealerMatrix) object;
        if ((this.dealerCode == null && other.dealerCode != null) || (this.dealerCode != null && !this.dealerCode.equals(other.dealerCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.UmUserDealerMatrix[ dealerCode=" + dealerCode + " ]";
    }

}
