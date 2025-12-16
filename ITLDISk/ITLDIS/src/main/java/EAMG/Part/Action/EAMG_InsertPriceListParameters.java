/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Part.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author vijay.mishra
 */
public class EAMG_InsertPriceListParameters
{

    public EAMG_InsertPriceListParameters()
    {
    }

    public Double calculatePrice(int np1, double mrp)
    {
        Double price = null;
        if (np1 > 1)
        {
            price = (mrp / np1) + ((mrp / np1) * 0.05);
        }
        else
        {
            price = mrp;
        }
        return price;

    }

    public Vector PriceListInsertion(File xlsfile, Connection conn, String userid, String effectiveDate) throws Exception
    {

        //decalration of variables used.
        int row = 1, column = 0;
        int errind = 0;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        PreparedStatement ps5 = null;
        Vector result = new Vector();
        Vector message = new Vector();
        Vector exists = new Vector();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        int noOfParts = 0;
        String mrp = "";
        String currency = "";
        int np1 = 1;
        boolean partExist = false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //System.out.println("enter");
        try
        {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);

            ps = conn.prepareStatement("SELECT * FROM CAT_PART(NOLOCK) WHERE part_no=?");
            ps1 = conn.prepareStatement("Insert into SP_PRICE_MASTER (pricelist_code,item,EFF_DATE,EXP_DATE,price,ORDER_PRICE) VALUES(?,?,?,?,?,?)");
            ps2 = conn.prepareStatement("select PRICELIST_CODE from SP_PRICELIST_CODE(NOLOCK)");
            ps3 = conn.prepareStatement("Insert into SP_PRICELIST_CODE (pricelist_code,description,CURRENCY_TYPE) VALUES(?,?,?)");
            ps4 = conn.prepareStatement("select * from SP_PRICE_MASTER(NOLOCK) where item=? and pricelist_code=? ");
            ps5 = conn.prepareStatement("update SP_PRICE_MASTER set item=? , pricelist_code=? , EFF_DATE=? , PRICE=?, ORDER_PRICE =? where item=? and pricelist_code=?");


            //loop for reading part parameters row wise.
            ArrayList PriceListArr = new ArrayList();

            rs1 = ps2.executeQuery();
            while (rs1.next())
            {
                PriceListArr.add(rs1.getString(1).toUpperCase());
            }
            rs1.close();
            ps2.close();

            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End")))
            {

                partno = sheet.getCell(column, row).getContents().trim();
                partno = partno.toUpperCase();
                column++;
                mrp = sheet.getCell(column, row).getContents().trim();
                column++;
                currency = sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;

                if(!PriceListArr.contains(currency))
                {
                   message.add(0, "Pricelist Code '" + currency + "' at row '" + (row + 1) + "' and column '3' does not exist.");
                    message.add(1, "Try Again");
                    message.add(2, "" + noOfParts);

                    result.add(message);
                    result.add(exists);

                    return result;
                }

                ps.setString(1, partno);
                rs = ps.executeQuery();
                if (rs.next())
                {
                    partExist = true;
                    try
                    {
                        np1 = Integer.parseInt(rs.getString("np1"));
                    }
                    catch (Exception e)
                    {
                        np1 = 1;
                    }

                }
                rs.close();

                if (partExist)
                {
                    ps4.setString(1, partno);
                    ps4.setString(2, currency);
                    //  ps4.setTimestamp(3, new java.sql.Timestamp(sdf.parse(effectiveDate).getTime()));
                    rs2 = ps4.executeQuery();

                    if (rs2.next())
                    {
                        
                        ps5.setString(1, partno);
                        ps5.setString(2, currency);
                        ps5.setTimestamp(3, new java.sql.Timestamp(sdf.parse(effectiveDate).getTime()));
                        ps5.setDouble(4, calculatePrice(np1, Double.parseDouble(mrp)));
                        ps5.setDouble(5, Double.parseDouble(mrp));
                        ps5.setString(6, partno);
                        ps5.setString(7, currency);
                        //    ps5.setTimestamp(7, new java.sql.Timestamp(sdf.parse(effectiveDate).getTime()));
                        ps5.executeUpdate();
                        noOfParts++;

                    }
                    else
                    {
//insert price list
//insert in ecat207 for new currency
                       

                        ps1.setString(1, currency);
                        ps1.setString(2, partno);
                        ps1.setTimestamp(3, new java.sql.Timestamp(sdf.parse(effectiveDate).getTime()));
                        ps1.setString(4, null);
                        ps1.setDouble(5, calculatePrice(np1, Double.parseDouble(mrp)));
                        ps1.setDouble(6, Double.parseDouble(mrp));
                        ps1.executeUpdate();

                        noOfParts++;

                    }
                }
                else
                {
                    message.add(0, "Part Number '" + partno + "' at row '" + (row + 1) + "' and column '1' does not exist.");
                    message.add(1, "Try Again");
                    message.add(2, "" + noOfParts);

                    result.add(message);
                    result.add(exists);

                    return result;
                }

                errind++;
                column = 0;
                row++;

            }
            //   ps.executeBatch();

            conn.commit();
            ps1.close();
            //ps1.close();

            if (noOfParts > 0)
            {
                message.add(0, "PriceList has been Added Successfully.");
                message.add(1, "Add More");

            }
            else
            {
                message.add(0, "No PriceList has been Added.");
                message.add(1, "Try Again");
            }
            message.add(2, "" + noOfParts);

            result.add(message);
            result.add(exists);

        }
        catch (Exception e)
        {
            e.printStackTrace();

            message.add(0, "No PriceList has been Added.");
            message.add(1, "Try Again");
            message.add(2, "" + noOfParts);

            result.add(message);
            result.add(exists);
        }
        finally
        {
            try
            {
                if(ps!=null)
                {
                    ps.close();
                    ps=null;
                }
                if(ps1!=null)
                {
                    ps1.close();
                    ps1=null;
                }
                if(ps2!=null)
                {
                    ps2.close();
                    ps2=null;
                }
                if(ps3!=null)
                {
                    ps3.close();
                    ps3=null;
                }
                if(ps4!=null)
                {
                    ps4.close();
                    ps4=null;
                }
                if(ps5!=null)
                {
                    ps5.close();
                    ps5=null;
                }
            }
            catch(Exception ee)
            {

            }
        }
        return result;
    }
}
