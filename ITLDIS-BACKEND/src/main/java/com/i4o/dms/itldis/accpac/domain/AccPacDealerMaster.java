package com.i4o.dms.itldis.accpac.domain;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class AccPacDealerMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExcelCell(0)
    @ExcelCellName("DEALER CODE")
    private String dealerCode;

    @ExcelCell(1)
    @ExcelCellName("DEALER NAME")
    private String dealerName;

    @ExcelCell(2)
    @ExcelCellName("STATUS")
    private String status;

    @ExcelCell(3)
    @ExcelCellName("DEALER FIRM TYPE")
    private String dealerFirmType;

    @ExcelCell(4)
    @ExcelCellName("GST NO.")
    private String gstNo;

    @ExcelCell(5)
    @ExcelCellName("PAN NO.")
    private String panNo;

    @ExcelCell(6)
    @ExcelCellName("DEALER E-MAIL ID")
    private String emailId;

    @ExcelCell(7)
    @ExcelCellName("ADDRESS 1")
    private String address1;

    @ExcelCell(8)
    @ExcelCellName("ADDRESS 2")
    private String address2;

    @ExcelCell(9)
    @ExcelCellName("ADDRESS 3")
    private String address3;

    @ExcelCell(10)
    @ExcelCellName("ADDRESS 4")
    private String address4;

    @ExcelCell(11)
    @ExcelCellName("PIN CODE")
    private String pinCode;

    @ExcelCell(12)
    @ExcelCellName("TEHSIL")
    private String tehsil;

    @ExcelCell(13)
    @ExcelCellName("CITY")
    private String city;

    @ExcelCell(14)
    @ExcelCellName("STATE")
    private String state;

    @ExcelCell(15)
    @ExcelCellName("COUNTRY")
    private String country;

    //add new columns
    @ExcelCell(16)
    @ExcelCellName("DEALER TYPE")
    private String dealerType;

    @ExcelCell(17)
    @ExcelCellName("DEALER CREDIT LIMIT")
    private String dealerCreditLimit;

    private Date syncDate=new Date();

}
