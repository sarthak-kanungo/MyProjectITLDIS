package com.i4o.dms.itldis.warranty.retrofitmentcampaign.dto;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Column;

@Getter
@Setter
public class ChassisDetails {

    @ExcelCellName("ChassisNo")
    @ExcelCell(0)
    @Column(unique = true)
    private String chassisNo;
    
    /**
     * @author suraj.gaur
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(this.chassisNo);
    }
    
    /**
     * @author suraj.gaur
     */
    @Override
    public boolean equals(Object obj)
    {
		if (this == obj)
			return true;
          
        if(obj == null || obj.getClass() != this.getClass())
            return false;
          
        ChassisDetails details = (ChassisDetails) obj;
          
        return (details.chassisNo == this.chassisNo);
    }
}
