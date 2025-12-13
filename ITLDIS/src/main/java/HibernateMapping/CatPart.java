/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "CAT_PART")
@NamedQueries({
    @NamedQuery(name = "CatPart.findAll", query = "SELECT c FROM CatPart c"),
    @NamedQuery(name = "CatPart.findByPartNo", query = "SELECT c FROM CatPart c WHERE c.partNo = :partNo"),
    @NamedQuery(name = "CatPart.findByPartType", query = "SELECT c FROM CatPart c WHERE c.partType = :partType"),
    @NamedQuery(name = "CatPart.findByCreationDate", query = "SELECT c FROM CatPart c WHERE c.creationDate = :creationDate"),
    @NamedQuery(name = "CatPart.findByCreator", query = "SELECT c FROM CatPart c WHERE c.creator = :creator"),
    @NamedQuery(name = "CatPart.findByCdNo", query = "SELECT c FROM CatPart c WHERE c.cdNo = :cdNo"),
    @NamedQuery(name = "CatPart.findByPatchNo", query = "SELECT c FROM CatPart c WHERE c.patchNo = :patchNo"),
    @NamedQuery(name = "CatPart.findByP1", query = "SELECT c FROM CatPart c WHERE c.p1 = :p1"),
    @NamedQuery(name = "CatPart.findByP2", query = "SELECT c FROM CatPart c WHERE c.p2 = :p2"),
    @NamedQuery(name = "CatPart.findByP3", query = "SELECT c FROM CatPart c WHERE c.p3 = :p3"),
    @NamedQuery(name = "CatPart.findByP4", query = "SELECT c FROM CatPart c WHERE c.p4 = :p4"),
    @NamedQuery(name = "CatPart.findByP5", query = "SELECT c FROM CatPart c WHERE c.p5 = :p5"),
    @NamedQuery(name = "CatPart.findByP6", query = "SELECT c FROM CatPart c WHERE c.p6 = :p6"),
    @NamedQuery(name = "CatPart.findByP7", query = "SELECT c FROM CatPart c WHERE c.p7 = :p7"),
    @NamedQuery(name = "CatPart.findByP8", query = "SELECT c FROM CatPart c WHERE c.p8 = :p8"),
    @NamedQuery(name = "CatPart.findByP9", query = "SELECT c FROM CatPart c WHERE c.p9 = :p9"),
    @NamedQuery(name = "CatPart.findByP10", query = "SELECT c FROM CatPart c WHERE c.p10 = :p10"),
    @NamedQuery(name = "CatPart.findByNp1", query = "SELECT c FROM CatPart c WHERE c.np1 = :np1"),
    @NamedQuery(name = "CatPart.findByNp2", query = "SELECT c FROM CatPart c WHERE c.np2 = :np2"),
    @NamedQuery(name = "CatPart.findByNp3", query = "SELECT c FROM CatPart c WHERE c.np3 = :np3"),
    @NamedQuery(name = "CatPart.findByNp4", query = "SELECT c FROM CatPart c WHERE c.np4 = :np4"),
    @NamedQuery(name = "CatPart.findByNp5", query = "SELECT c FROM CatPart c WHERE c.np5 = :np5"),
    @NamedQuery(name = "CatPart.findByNp6", query = "SELECT c FROM CatPart c WHERE c.np6 = :np6"),
    @NamedQuery(name = "CatPart.findByNp7", query = "SELECT c FROM CatPart c WHERE c.np7 = :np7"),
    @NamedQuery(name = "CatPart.findByNp8", query = "SELECT c FROM CatPart c WHERE c.np8 = :np8"),
    @NamedQuery(name = "CatPart.findByNp9", query = "SELECT c FROM CatPart c WHERE c.np9 = :np9"),
    @NamedQuery(name = "CatPart.findByNp10", query = "SELECT c FROM CatPart c WHERE c.np10 = :np10"),
    @NamedQuery(name = "CatPart.findByNp11", query = "SELECT c FROM CatPart c WHERE c.np11 = :np11"),
    @NamedQuery(name = "CatPart.findByNp12", query = "SELECT c FROM CatPart c WHERE c.np12 = :np12"),
    @NamedQuery(name = "CatPart.findByNp13", query = "SELECT c FROM CatPart c WHERE c.np13 = :np13"),
    @NamedQuery(name = "CatPart.findByNp14", query = "SELECT c FROM CatPart c WHERE c.np14 = :np14"),
    @NamedQuery(name = "CatPart.findByNp15", query = "SELECT c FROM CatPart c WHERE c.np15 = :np15"),
    @NamedQuery(name = "CatPart.findByNp16", query = "SELECT c FROM CatPart c WHERE c.np16 = :np16"),
    @NamedQuery(name = "CatPart.findByNp17", query = "SELECT c FROM CatPart c WHERE c.np17 = :np17"),
    @NamedQuery(name = "CatPart.findByNp18", query = "SELECT c FROM CatPart c WHERE c.np18 = :np18"),
    @NamedQuery(name = "CatPart.findByNp19", query = "SELECT c FROM CatPart c WHERE c.np19 = :np19"),
    @NamedQuery(name = "CatPart.findByNp20", query = "SELECT c FROM CatPart c WHERE c.np20 = :np20"),
    @NamedQuery(name = "CatPart.findByPuId", query = "SELECT c FROM CatPart c WHERE c.puId = :puId")})
public class CatPart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "part_no")
    private String partNo;
    @Column(name = "part_type")
    private String partType;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "creator")
    private String creator;
    @Column(name = "CD_NO")
    private Integer cdNo;
    @Column(name = "PATCH_NO")
    private Integer patchNo;
    @Column(name = "p1")
    private String p1;
    @Column(name = "p2")
    private String p2;
    @Column(name = "p3")
    private String p3;
    @Column(name = "p4")
    private String p4;
    @Column(name = "p5")
    private String p5;
    @Column(name = "p6")
    private String p6;
    @Column(name = "p7")
    private String p7;
    @Column(name = "p8")
    private String p8;
    @Column(name = "p9")
    private String p9;
    @Column(name = "p10")
    private String p10;
    @Column(name = "np1")
    private String np1;
    @Column(name = "np2")
    private String np2;
    @Column(name = "np3")
    private String np3;
    @Column(name = "np4")
    private String np4;
    @Column(name = "np5")
    private String np5;
    @Column(name = "np6")
    private String np6;
    @Column(name = "np7")
    private String np7;
    @Column(name = "np8")
    private String np8;
    @Column(name = "np9")
    private String np9;
    @Column(name = "np10")
    private String np10;
    @Column(name = "np11")
    private String np11;
    @Column(name = "np12")
    private String np12;
    @Column(name = "np13")
    private String np13;
    @Column(name = "np14")
    private String np14;
    @Column(name = "np15")
    private String np15;
    @Column(name = "np16")
    private String np16;
    @Column(name = "np17")
    private String np17;
    @Column(name = "np18")
    private String np18;
    @Column(name = "np19")
    private String np19;
    @Column(name = "np20")
    private String np20;
    @Column(name = "pu_id")
    private String puId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catPart", fetch = FetchType.LAZY)
    private List<SpPriceMaster> spPriceMasterList;

    public CatPart() {
    }

    public CatPart(String partNo) {
        this.partNo = partNo;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getCdNo() {
        return cdNo;
    }

    public void setCdNo(Integer cdNo) {
        this.cdNo = cdNo;
    }

    public Integer getPatchNo() {
        return patchNo;
    }

    public void setPatchNo(Integer patchNo) {
        this.patchNo = patchNo;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public String getP6() {
        return p6;
    }

    public void setP6(String p6) {
        this.p6 = p6;
    }

    public String getP7() {
        return p7;
    }

    public void setP7(String p7) {
        this.p7 = p7;
    }

    public String getP8() {
        return p8;
    }

    public void setP8(String p8) {
        this.p8 = p8;
    }

    public String getP9() {
        return p9;
    }

    public void setP9(String p9) {
        this.p9 = p9;
    }

    public String getP10() {
        return p10;
    }

    public void setP10(String p10) {
        this.p10 = p10;
    }

    public String getNp1() {
        return np1;
    }

    public void setNp1(String np1) {
        this.np1 = np1;
    }

    public String getNp2() {
        return np2;
    }

    public void setNp2(String np2) {
        this.np2 = np2;
    }

    public String getNp3() {
        return np3;
    }

    public void setNp3(String np3) {
        this.np3 = np3;
    }

    public String getNp4() {
        return np4;
    }

    public void setNp4(String np4) {
        this.np4 = np4;
    }

    public String getNp5() {
        return np5;
    }

    public void setNp5(String np5) {
        this.np5 = np5;
    }

    public String getNp6() {
        return np6;
    }

    public void setNp6(String np6) {
        this.np6 = np6;
    }

    public String getNp7() {
        return np7;
    }

    public void setNp7(String np7) {
        this.np7 = np7;
    }

    public String getNp8() {
        return np8;
    }

    public void setNp8(String np8) {
        this.np8 = np8;
    }

    public String getNp9() {
        return np9;
    }

    public void setNp9(String np9) {
        this.np9 = np9;
    }

    public String getNp10() {
        return np10;
    }

    public void setNp10(String np10) {
        this.np10 = np10;
    }

    public String getNp11() {
        return np11;
    }

    public void setNp11(String np11) {
        this.np11 = np11;
    }

    public String getNp12() {
        return np12;
    }

    public void setNp12(String np12) {
        this.np12 = np12;
    }

    public String getNp13() {
        return np13;
    }

    public void setNp13(String np13) {
        this.np13 = np13;
    }

    public String getNp14() {
        return np14;
    }

    public void setNp14(String np14) {
        this.np14 = np14;
    }

    public String getNp15() {
        return np15;
    }

    public void setNp15(String np15) {
        this.np15 = np15;
    }

    public String getNp16() {
        return np16;
    }

    public void setNp16(String np16) {
        this.np16 = np16;
    }

    public String getNp17() {
        return np17;
    }

    public void setNp17(String np17) {
        this.np17 = np17;
    }

    public String getNp18() {
        return np18;
    }

    public void setNp18(String np18) {
        this.np18 = np18;
    }

    public String getNp19() {
        return np19;
    }

    public void setNp19(String np19) {
        this.np19 = np19;
    }

    public String getNp20() {
        return np20;
    }

    public void setNp20(String np20) {
        this.np20 = np20;
    }

    public String getPuId() {
        return puId;
    }

    public void setPuId(String puId) {
        this.puId = puId;
    }

    public List<SpPriceMaster> getSpPriceMasterList() {
        return spPriceMasterList;
    }

    public void setSpPriceMasterList(List<SpPriceMaster> spPriceMasterList) {
        this.spPriceMasterList = spPriceMasterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partNo != null ? partNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatPart)) {
            return false;
        }
        CatPart other = (CatPart) object;
        if ((this.partNo == null && other.partNo != null) || (this.partNo != null && !this.partNo.equals(other.partNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.CatPart[partNo=" + partNo + "]";
    }

}
