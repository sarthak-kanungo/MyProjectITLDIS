package com.i4o.dms.itldis.utils;

public class NumberGenerator {

    public static String generateKubotaEmployeeCode(Long id) {
        System.out.print("id" + id);
        return "KEMP-" + id;
    }

 public static String generateSchemeNo(Long id)
 {
     System.out.print("id" + id);
     return "SCH-" + id;
 }

    public static String generateTrasnportCode(Long id) {
        System.out.print("id" + id);
        return "TR-" + id;
    }

    public static String generatePartyCode(Long id) {
        System.out.print("id" + id);
        return "PM-" + id;
    }

    public static String generateBranchCode(Long id) {
        System.out.print("id" + id);
        return "BR-" +id;
    }

    public static String generateDmsItemNumber(Long id)
    {
        System.out.print("id" + id);
        return "LP-"  +id;
    }

    public static String generateInstallationNumber(Long id)
    {
        System.out.print("id" + id);
        return "IN-"  +id;
    }

}


