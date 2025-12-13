/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.ActionFormBean;

/**
 *
 * @author manish.kishore
 */
public class UtilityOptionBean {

    private String label;
    private String value;

    public UtilityOptionBean(String label, String value) {
        this.label = label;
        this.value = value;
        //System.out.println("hello");
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
         ///System.out.println("label1");
        UtilityOptionBean tempBean = (UtilityOptionBean) o;
        String label1 = tempBean.getLabel();
        String value1 = tempBean.getValue();
        
       

        if (label1.equals(label) && value1.equals(value)) {
            return true;
        } else {
            return false;
        }
    }
}
