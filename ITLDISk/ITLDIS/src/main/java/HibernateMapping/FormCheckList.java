/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/**
 *
 * @author megha.pandya
 */
@Entity
@NamedNativeQuery(
    name = "call_FormCheckList",
    query = "EXECUTE FormCheckList :FormId",
    resultClass = FormCheckList.class
    )
public class FormCheckList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "n")
    private BigInteger n;
    @Basic(optional = false)
    @Column(name = "CONTENT_ID")
    private int contentId;
    @Column(name = "CONTENT_DESC")
    private String contentDesc;
    @Basic(optional = false)
    @Column(name = "SUB_CONTENT_ID")
    private long subContentId;
    @Column(name = "SUB_CONTENT_DESC")
    private String subContentDesc;
    @Column(name = "ORDER_SEQ")
    private Integer orderSeq;
    @Column(name = "FORM_ID")
    private Integer formId;

    public FormCheckList() {
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public long getSubContentId() {
        return subContentId;
    }

    public void setSubContentId(long subContentId) {
        this.subContentId = subContentId;
    }

    public String getSubContentDesc() {
        return subContentDesc;
    }

    public void setSubContentDesc(String subContentDesc) {
        this.subContentDesc = subContentDesc;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }
}
