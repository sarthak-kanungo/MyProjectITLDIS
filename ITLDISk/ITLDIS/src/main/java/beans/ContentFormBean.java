/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author manish.kishore
 */
public class ContentFormBean implements Comparable{
    private Integer contentId;
    private String contentDesc;
    private String observation;
    private String status;
    private int seqno;
    private String serialno;
    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
    
    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }
    

    public int compareTo(Object o) {
        ContentFormBean i=(ContentFormBean)o;
        return getContentId().compareTo(i.getContentId());
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return the contentId
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * @param contentId the contentId to set
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * @return the contentDesc
     */
    public String getContentDesc() {
        return contentDesc;
    }

    /**
     * @param contentDesc the contentDesc to set
     */
    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }
    
}
