package com.i4o.dms.itldis.eamg.servicebulletin.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * EAMG Service Bulletin Entity
 */
@Entity
@Table(name = "eamg_service_bulletin")
@Data
public class EamgServiceBulletin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "bulletin_no", unique = true)
    private String bulletinNo;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "year_of_issue")
    private String yearOfIssue;
    
    @Column(name = "issue_date")
    private Date issueDate;
    
    @Column(name = "subject")
    private String subject;
    
    @Column(name = "description", length = 2000)
    private String description;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
}
