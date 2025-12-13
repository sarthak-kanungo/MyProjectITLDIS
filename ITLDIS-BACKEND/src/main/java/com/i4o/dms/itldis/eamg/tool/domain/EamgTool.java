package com.i4o.dms.itldis.eamg.tool.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * EAMG Tool Entity
 */
@Entity
@Table(name = "eamg_tool")
@Data
public class EamgTool {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tool_no", unique = true)
    private String toolNo;
    
    @Column(name = "tool_name")
    private String toolName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
}
