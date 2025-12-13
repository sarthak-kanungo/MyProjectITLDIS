package viewEcat.orderEcat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Hashtable;

/**
For Getting the Part Details with UserCode,PriceList,Price_Ref_No.
@author  Deepak Mangal
@version v3.4
@since 
 */
public class PriceDetails
{

    Connection conn;

    /**
     * Constructor
     * @param conn Connection from the Database
     */
    public PriceDetails(Connection conn)
    {
        this.conn = conn;
    }

    /**************************************************************************************
     *  Function to get the Pricelist code attached with the user
     * <br><br><b>Steps:</b>
     * <br>1.If No Pricelist code is attached with user, then "not Found" value is Returned.
     * <br>2. If OrderType of the user starts with 'SE' means that an Export Dealer, corresponding pricelist code regarding the Dealer is returned
     * <br>3. Otherwise, Blank pricelist code will be returned for all domestic users.
     * <br><br><b>Author</b> - Deepak Mangal
     * @param userCode Login ID if the user.
     * @return  String Pricelist Code of Dealer
     ****************************************************************************************/

    public String getPriceListCode(String userCode)
    {
        String priceListCode = "";

        try
        {
            //$1
            PreparedStatement pstmt = conn.prepareStatement("Select PRICELIST_CODE from UM_USER_PRICELIST(NOLOCK) Where USER_ID=?");
            pstmt.setString(1, userCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                //$2
                priceListCode = rs.getString(1);
                //$3
                if (priceListCode == null)
                {
                    priceListCode = "";
                }
            }
            else
            {
                priceListCode = "MRP_INR";
            }
            rs.close();
            pstmt.close();
        }
        catch (Exception ee)
        {
            priceListCode = "";
        }
        return priceListCode;
    }

   
    public String getActualPriceListCode(String userCode)
    {
        String priceListCode = "PP1";
        String query;
        try
        {
            //$1
            query = "Select PRICELIST_CODE From ecat302(NOLOCK) Where CUST_CODE=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                //$2
                priceListCode = rs.getString(1);
                //$3
                if ((priceListCode == null))
                {
                    priceListCode = "PP1";
                }
            }
            rs.close();
            pstmt.close();

            return priceListCode;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    /**************************************************************************************
     * Function to get the currency for the Pricelist Code
     * <br><br><b>Steps:</b>
     * <br>1. Currency will be found from ecat207 with corresponding Price List Code.
     * <br>2. If Pricelist Code is null, "not Found", empty then currency will be INR.
     * <br><br><b>Author</b> - Deepak Mangal
     * @param priceListCode Price List Code to which currency is related.
     * @return String Currency of Price List Code.  
     ****************************************************************************************/
    public String getCurrency(String priceListCode)
    {
        String currencyType = "INR";
        //$1
        if ((priceListCode == null) || (priceListCode.equals("notFound")) || (priceListCode.equals(" ")) || (priceListCode.equals("")))
        {
            return currencyType;
        }

        String query = "Select CURRENCY_TYPE From SP_PRICELIST_CODE(NOLOCK) Where PRICELIST_CODE=?";
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, priceListCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                currencyType = rs.getString(1);
            }
            rs.close();
            pstmt.close();
            return currencyType;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }

    }

    /*	Hashtable getPriceListCurrencyforUser(String userCode)
    {
    Hashtable priceListCurr=new Hashtable();
    String query="Select PL.PRICELIST_CODE ,PL.CURRENCY_TYPE From ecat207 PL,ecat302 DP Where PL.PRICELIST_CODE=DP.PRICELIST_CODE And DP.CUST_CODE=?";
    try
    {
    PreparedStatement pstmt=conn.prepareStatement(query);
    pstmt.setString(1,userCode);
    ResultSet rs=pstmt.executeQuery();
    int priceListCode;
    String currencyType;
    while(rs.next())
    {
    priceListCode=rs.getInt(1);
    currencyType=rs.getString(2);
    priceListCurr.put(new Integer(priceListCode),currencyType);
    }
    rs.close();
    pstmt.close();
    return priceListCurr;
    }
    catch(SQLException ex)
    {
    ex.printStackTrace();
    return null;
    }
    }
     */
    /**************************************************************************************
     *  Function to get the price for particular part in Pricelist Code according to the date
     * <br><br><b>Steps:</b>
     * <br>1. Select part's Price from table(database) ecat208 corresponding to PriceList Code(if Exists or search for null) , Effective Date and item No.
     * <br><br><b>Author</b> - Deepak Mangal
     * @param partNo String Part No.
     * @param priceListCode Price list Code of User.
     * @param effDate java.util.Date Date from which the price is effective .
     * @return String Part's Price.  
     ****************************************************************************************/
    public String getPartPrice(String partNo, String priceListCode, java.util.Date effDate)
    {
        java.sql.Date date = new java.sql.Date(effDate.getTime());
        String price = null;
        String query = "";
        //$1 2
        if ((priceListCode == null) || (priceListCode.equals("notFound")) || (priceListCode.equals(" ")) || (priceListCode.equals("")))
        {
            query = "Select PRICE from SP_PRICE_MASTER(NOLOCK) where (PRICELIST_CODE is null) and EFF_DATE<={d'" + date + "'} and (EXP_DATE>={d'" + date + "'} or EXP_DATE is null) and ITEM=? order by EFF_DATE desc";
        }
        else
        {
            query = "Select PRICE from SP_PRICE_MASTER(NOLOCK) where PRICELIST_CODE='" + priceListCode + "' and EFF_DATE<={d'" + date + "'} and (EXP_DATE>={d'" + date + "'} or EXP_DATE is null) and ITEM=? order by EFF_DATE desc";
        }
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, partNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                price = "" + rs.getDouble(1);
            }
            rs.close();
            pstmt.close();
            return price;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public String getPartDesc(String partNo)
    {

        String desc = null;
        String query = "";
        //$1 2
        query = "Select p1 from CAT_PART(NOLOCK) where part_no=?";

        try
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, partNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                desc = "" + rs.getString(1);
            }
            rs.close();
            pstmt.close();
            return desc;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    /**************************************************************************************
     * Function to get the price for all parts in  according to Pricelist Code and Effective date.
     * <br><br><b>Steps:</b>
     * <br>1. Retrieve part's Price and component number from table ecat208(Database), Corresponding to price List Code(if Exists or null) and Effective Date
     * <br>2. If price is not Null, than add the Price with Part No. as key in Hash Map.
     * <br><br><b>Author</b> - Deepak Mangal
     * @param priceListCode  Price list Code of User.
     * @param effDate java.util.Date Date from which the price is effective.
     * @return Hashtable Putting the part's price as value with Part No as key.  
     ****************************************************************************************/
    public Hashtable getPartPrice(String priceListCode, java.util.Date effDate)
    {
        java.sql.Date date = new java.sql.Date(effDate.getTime());
        String price = "0.0";
        String partNo;
        Hashtable partPriceTable = new Hashtable();
        String query = "";
        //$1
        if ((priceListCode == null) || (priceListCode.equals("notFound")) || (priceListCode.equals(" ")) || (priceListCode.equals("")))
        {
            query = "Select ITEM,PRICE from SP_PRICE_MASTER(NOLOCK) where (PRICELIST_CODE is null) and EFF_DATE<={d'" + date + "'} and (EXP_DATE>={d'" + date + "'} or EXP_DATE is null) and (ITEM is not null) order by EFF_DATE";
        }
        else
        {
            query = "Select ITEM,PRICE from SP_PRICE_MASTER(NOLOCK) where PRICELIST_CODE='" + priceListCode + "' and EFF_DATE<={d'" + date + "'} and (EXP_DATE>={d'" + date + "'} or EXP_DATE is null) and (ITEM is not null) order by EFF_DATE";
        }
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                partNo = rs.getString(1);
                price = "" + rs.getDouble(2);
                //2
                if (price != null)
                {
                    partPriceTable.put(partNo, price);
                }
            }
            rs.close();
            pstmt.close();

            return partPriceTable;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return partPriceTable;
        }
    }

    /**************************************************************************************
     * Function to find whether the pricelist is Orderable.
     * <br><br><b>Steps:</b>
     * <br>1. If No Pricelist code is attached to an user, then Part is not Orderable.
     * <br>2. Find the Expiry Date from table ecat208(Database) corresponding to Price List Code(if Exist or Null) and effective Date of a part No.
     * <br>3. If the Part is Expired, than boolean is set to true and returned, As Orderable.
     * <br><br><b>Author</b> - Deepak Mangal
     * @param priceListCode Price list Code of User.
     * @return  Hashtable Putting the part's price as value with Part No as key.  
     ****************************************************************************************/
    public boolean isPriceListOrderable(String priceListCode)
    {
        return true;
        //$1
        /*if ((priceListCode != null) && priceListCode.equals("notFound"))
        {
        return false;
        }

        String query = "";
        java.sql.Date today = new java.sql.Date(new java.util.Date().getTime());
        boolean orderable = false;

        //2
        if ((priceListCode == null) || (priceListCode.equals(" ")) || (priceListCode.equals("")))
        {
        query = "Select EXP_DATE from SP_PRICE_MASTER where (PRICELIST_CODE is null) and (EFF_DATE<={d'" + today + "'}) and (EXP_DATE>={d'" + today + "'} or EXP_DATE is null) ";
        }
        else
        {
        query = "Select EXP_DATE from SP_PRICE_MASTER where (PRICELIST_CODE='" + priceListCode + "') and (EFF_DATE<={d'" + today + "'}) and (EXP_DATE>={d'" + today + "'} or EXP_DATE is null) ";
        }

        try
        {
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
        //3
        orderable = true;
        }
        else
        {
        orderable = false;
        }
        rs.close();
        pstmt.close();

        return orderable;
        }
        catch (SQLException ex)
        {
        ex.printStackTrace();
        return false;
        }
         */
    }
   /**************************************************************************************
     *  Function to get the Selling Percentage attached with the dealer Code
     * <br><br><b>Steps:</b>
     * <br>1.If No Selling Percentage  is attached with dealer Code, then "0" value is Returned.
     * <br><br><b>Author</b> - Avinash Pandey
     * @param dealer Code Login ID if the user.
     * @return  String Selling Percentage of Dealer
     ****************************************************************************************/
    public Double getSellingPercentageByDealerCode(String dealerCode,Connection con)
    {
        double sellingPercentage = 0.0;
        PreparedStatement pstmt=null;
        ResultSet rs =null;
        try
        {
            //$1
            pstmt = con.prepareStatement("Select SELLING_PERCENTAGE from UM_USER_SELLING_PERCENTAGE(NOLOCK) Where DEALER_CODE=?");
            pstmt.setString(1, dealerCode);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
               sellingPercentage = rs.getDouble(1);
            }
        }
        catch (Exception e)
        {
            sellingPercentage = 0.0;
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {
            }
        }
        return sellingPercentage;
    }
    public  int loginTrakerByUserId(String userCode, int usageType, String remoteIPAddr, Connection conn) throws Exception, SQLException {
        int isCreated = 0;
        PreparedStatement pstmt = null, pstmt1 = null;
        ResultSet rs = null;
        Calendar currenttime = Calendar.getInstance();
        java.sql.Date sqldate = new java.sql.Date((currenttime.getTime()).getTime());
        int count = 1;

        try {
            pstmt1 = conn.prepareStatement("select COUNT from UM_LOGIN_TRACK(NOLOCK) where USER_ID=? and LOGIN_DATE=? and USAGE_TYPE_ID=?");
            pstmt1.setString(1, userCode);
            pstmt1.setDate(2, sqldate);
            pstmt1.setInt(3, usageType);
            rs = pstmt1.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT");
                count++;
                pstmt = conn.prepareStatement("update UM_LOGIN_TRACK set COUNT=?,IP=? where USER_ID=? and LOGIN_DATE=? and USAGE_TYPE_ID=?");
                pstmt.setInt(1, count);
                pstmt.setString(2, remoteIPAddr);
                pstmt.setString(3, userCode);
                pstmt.setDate(4, sqldate);
                pstmt.setInt(5, usageType);
                pstmt.executeUpdate();
            } else {
                pstmt = conn.prepareStatement("insert into UM_LOGIN_TRACK values (?,?,?,?,?)");
                pstmt.setString(1, userCode);
                pstmt.setString(2, remoteIPAddr);
                pstmt.setDate(3, sqldate);
                pstmt.setInt(4, count);
                pstmt.setInt(5, usageType);
                pstmt.executeUpdate();
            }
            rs.close();
            pstmt.close();
            pstmt = null;
            isCreated = 1;
            pstmt1.close();
            conn.commit();
        } catch (Exception ex) {
            isCreated = 2;
            conn.rollback();
            ex.printStackTrace();
            //logger.error ( WebConstants.logException , ex );
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
            } catch (Exception e) {
               // System.out.println("i m here in finally");
                e.printStackTrace();
                //logger.error ( WebConstants.fLogException , e );
            }
        }
        return isCreated;
    }
   
}
