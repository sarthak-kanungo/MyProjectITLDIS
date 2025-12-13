/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EAMG.Group.ActionFormBean;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Avinash.pandey
 */
public class EAMG_GroupCreationByWzActionForm extends org.apache.struts.action.ActionForm {
    
   private String groupname;
   private String groupdesc;
   private String comp;
   private FormFile importimagefile = null;
   private FormFile importSvgFile = null;
   private String[] seqeno;
   private String[] comp_no_text;
   private String[] quantity;
   private String[] group_remarks;
   private String[] interchangeability;
   private String[] cutoffchassis;
   private String[] cutoff;
   private String index;
   private String option;

    public String[] getCutoff() {
        return cutoff;
    }

    public void setCutoff(String[] cutoff) {
        this.cutoff = cutoff;
    }

    public String[] getCutoffchassis() {
        return cutoffchassis;
    }

    public void setCutoffchassis(String[] cutoffchassis) {
        this.cutoffchassis = cutoffchassis;
    }

    public String[] getInterchangeability() {
        return interchangeability;
    }

    public void setInterchangeability(String[] interchangeability) {
        this.interchangeability = interchangeability;
    }

    /**
     * @return the groupname
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * @param groupname the groupname to set
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * @return the groupdesc
     */
    public String getGroupdesc() {
        return groupdesc;
    }

    /**
     * @param groupdesc the groupdesc to set
     */
    public void setGroupdesc(String groupdesc) {
        this.groupdesc = groupdesc;
    }

    /**
     * @return the comp
     */
    public String getComp() {
        return comp;
    }

    /**
     * @param comp the comp to set
     */
    public void setComp(String comp) {
        this.comp = comp;
    }

    /**
     * @return the importimagefile
     */
    public FormFile getImportimagefile() {
        return importimagefile;
    }

    /**
     * @param importimagefile the importimagefile to set
     */
    public void setImportimagefile(FormFile importimagefile) {
        this.importimagefile = importimagefile;
    }

    /**
     * @return the seqeno
     */
    public String[] getSeqeno() {
        return seqeno;
    }

    /**
     * @param seqeno the seqeno to set
     */
    public void setSeqeno(String[] seqeno) {
        this.seqeno = seqeno;
    }

    /**
     * @return the comp_no_text
     */
    public String[] getComp_no_text() {
        return comp_no_text;
    }

    /**
     * @param comp_no_text the comp_no_text to set
     */
    public void setComp_no_text(String[] comp_no_text) {
        this.comp_no_text = comp_no_text;
    }

    /**
     * @return the quantity
     */
    public String[] getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the group_remarks
     */
    public String[] getGroup_remarks() {
        return group_remarks;
    }

    /**
     * @param group_remarks the group_remarks to set
     */
    public void setGroup_remarks(String[] group_remarks) {
        this.group_remarks = group_remarks;
    }

    /**
     * @return the index
     */
    public String getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     * @return the option
     */
    public String getOption() {
        return option;
    }

    /**
     * @param option the option to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    public FormFile getImportSvgFile() {
        return importSvgFile;
    }

    public void setImportSvgFile(FormFile importSvgFile) {
        this.importSvgFile = importSvgFile;
    }
}
