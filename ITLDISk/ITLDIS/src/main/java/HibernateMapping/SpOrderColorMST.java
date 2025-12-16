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
import javax.persistence.Table;

/**
 *
 * @author Avinash.Pandey
 */
@Entity
@Table(name = "SP_ORDER_COLOR_MST")
public class SpOrderColorMST implements Serializable {
     private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COLOR_ID")
    private Integer color_id;
    @Basic(optional = false)
    @Column(name = "COLOR_NAME")
    private String color_name;
    @Basic(optional = false)
    @Column(name = "COLOR_CODE")
    private String color_code;
    @Basic(optional = false)
    @Column(name = "COLOR_TEXT")
    private String color_text;
    @Basic(optional = false)
    @Column(name = "IsActive")
    private String isActive;

    public SpOrderColorMST() {
        System.out.println("hi");
    }

    public SpOrderColorMST(Integer color_id) {
        this.color_id = color_id;
    }

   public SpOrderColorMST(Integer color_id, String color_name, String color_code,String color_text,String isActive) {
        this.color_id = color_id;
        this.color_name = color_name;
        this.color_code = color_code;
        this.color_text = color_text;
        this.isActive = isActive;
    }

    public Integer getColor_Id() {
        return color_id;
    }

    public void setColor_Id(Integer color_id) {
        this.color_id = color_id;
    }

    public String getColor_Name() {
        return color_name;
    }

    public void setColor_Name(String color_name) {
        this.color_name = color_name;
    }

    public String getColor_Code() {
        return color_code;
    }

    public void setColor_Code(String color_code) {
        this.color_code = color_code;
    }


    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
   public String getColor_Text() {
        return color_text;
    }

    public void setColor_Text(String color_text) {
        this.color_text = color_text;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderColorMST[color_id=" + color_id + "]";
    }

}
