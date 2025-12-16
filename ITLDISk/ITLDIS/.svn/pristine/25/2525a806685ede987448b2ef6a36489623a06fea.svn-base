/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "GEN_LANGUAGES")
@NamedQueries({
    @NamedQuery(name = "GenLanguages.findAll", query = "SELECT g FROM GenLanguages g"),
    @NamedQuery(name = "GenLanguages.findByLangId", query = "SELECT g FROM GenLanguages g WHERE g.langId = :langId"),
    @NamedQuery(name = "GenLanguages.findByLanguageName", query = "SELECT g FROM GenLanguages g WHERE g.languageName = :languageName"),
    @NamedQuery(name = "GenLanguages.findByLanguageCode", query = "SELECT g FROM GenLanguages g WHERE g.languageCode = :languageCode"),
    @NamedQuery(name = "GenLanguages.findByLanguageCountry", query = "SELECT g FROM GenLanguages g WHERE g.languageCountry = :languageCountry"),
    @NamedQuery(name = "GenLanguages.findByIsAppDefaultLanguge1", query = "SELECT g FROM GenLanguages g WHERE g.isAppDefaultLanguge1 = :isAppDefaultLanguge1"),
    @NamedQuery(name = "GenLanguages.findByIsUserDefaultLanguge", query = "SELECT g FROM GenLanguages g WHERE g.isUserDefaultLanguge = :isUserDefaultLanguge")})
public class GenLanguages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LANG_ID")
    private Integer langId;
    @Basic(optional = false)
    @Column(name = "LANGUAGE_NAME")
    private String languageName;
    @Lob
    @Column(name = "LANGUAGE_DISPLAY_CODE")
    private String languageDisplayCode;
    @Basic(optional = false)
    @Column(name = "LANGUAGE_CODE")
    private String languageCode;
    @Basic(optional = false)
    @Column(name = "LANGUAGE_COUNTRY")
    private String languageCountry;
    @Basic(optional = false)
    @Column(name = "IS_APP_DEFAULT_LANGUGE1")
    private String isAppDefaultLanguge1;
    @Basic(optional = false)
    @Column(name = "IS_USER_DEFAULT_LANGUGE")
    private String isUserDefaultLanguge;

    public GenLanguages() {
    }

    public GenLanguages(Integer langId) {
        this.langId = langId;
    }

    public GenLanguages(Integer langId, String languageName, String languageCode, String languageCountry, String isAppDefaultLanguge1, String isUserDefaultLanguge) {
        this.langId = langId;
        this.languageName = languageName;
        this.languageCode = languageCode;
        this.languageCountry = languageCountry;
        this.isAppDefaultLanguge1 = isAppDefaultLanguge1;
        this.isUserDefaultLanguge = isUserDefaultLanguge;
    }

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageDisplayCode() {
        return languageDisplayCode;
    }

    public void setLanguageDisplayCode(String languageDisplayCode) {
        this.languageDisplayCode = languageDisplayCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCountry() {
        return languageCountry;
    }

    public void setLanguageCountry(String languageCountry) {
        this.languageCountry = languageCountry;
    }

    public String getIsAppDefaultLanguge1() {
        return isAppDefaultLanguge1;
    }

    public void setIsAppDefaultLanguge1(String isAppDefaultLanguge1) {
        this.isAppDefaultLanguge1 = isAppDefaultLanguge1;
    }

    public String getIsUserDefaultLanguge() {
        return isUserDefaultLanguge;
    }

    public void setIsUserDefaultLanguge(String isUserDefaultLanguge) {
        this.isUserDefaultLanguge = isUserDefaultLanguge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (langId != null ? langId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenLanguages)) {
            return false;
        }
        GenLanguages other = (GenLanguages) object;
        if ((this.langId == null && other.langId != null) || (this.langId != null && !this.langId.equals(other.langId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.GenLanguages[langId=" + langId + "]";
    }

}
