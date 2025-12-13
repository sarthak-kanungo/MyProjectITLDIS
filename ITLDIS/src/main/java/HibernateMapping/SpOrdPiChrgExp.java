package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author prashant.kumar
 */
@Entity
@Table(name = "SP_ORD_PI_CHRG_EXP")
public class SpOrdPiChrgExp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private SpOrdPiChrgExpPK spOrdPiChrgExpPK;
    @Column(name="CostHeadValue")
    private Float costHeadValue;

    public Float getCostHeadValue() {
        return costHeadValue;
    }

    public void setCostHeadValue(Float costHeadValue) {
        this.costHeadValue = costHeadValue;
    }

    public SpOrdPiChrgExpPK getSpOrdPiChrgExpPK() {
        return spOrdPiChrgExpPK;
    }

    public void setSpOrdPiChrgExpPK(SpOrdPiChrgExpPK spOrdPiChrgExpPK) {
        this.spOrdPiChrgExpPK = spOrdPiChrgExpPK;
    }

}