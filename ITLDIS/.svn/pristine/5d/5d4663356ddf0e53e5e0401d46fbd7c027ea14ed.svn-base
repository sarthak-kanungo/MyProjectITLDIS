package viewEcat.comEcat;

/*
File Name: EcatAuthorisation.java
PURPOSE: File having various fuctions to find the functionalities depending on different criterias.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class EcatAuthorisation
{

    /** Creates a new instance of EcatAuthorisation */
    public EcatAuthorisation()
    {
    }

    /** Function Used:
    1. getUserFunctionalties			-		To get all functionalities for a particular user type
    2. getFileFunctionalities			-		To get all fuctionalities in a particular file.	
    3. getAllOrderingFunctionalities	-		To get all ordering functionalities	
    4. subFuncExists					-		To find all sub-funtionalities in a main functioanlity	
     */
    // To get all functionalities for a particular user type
    public Vector getUserFunctionalties(Connection conn, String userType)
    {
        Vector userFun = new Vector();
        try
        {
            String query = "Select FUNC_ID from ecat103(NOLOCK) where USER_TYPE_ID=(Select USER_TYPE_ID from ecat101(NOLOCK) where USER_TYPE='" + userType + "')";
//System.out.println("query..."+query);


            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = null;
            rs = pstmt.executeQuery();

            int temp;

            if (rs.next())
            {
                do
                {
                    temp = rs.getInt(1);
                    if (temp != 0)
                    {
                        userFun.add("" + temp);
                    }
                }
                while (rs.next());
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return userFun;
    }

    // To get all fuctionalities in a particular file.
//    public static Vector getFileFunctionalities(Connection conn, String fileName)
//    {
//        Vector fileFun = new Vector();
//        try
//        {
//            String query = "Select FUNC_ID from ecat105 where FILE_ID=(Select FILE_ID from ecat104 where FILE_DESC=?)";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            fileName="%"+fileName;
//            pstmt.setString(1, fileName);
//            ResultSet rs = null;
//            rs = pstmt.executeQuery();
//            String fun;
//            while (rs.next())
//            {
//                fun = rs.getString(1);
//                if (fun != null)
//                {
//                    fileFun.add(fun);
//                }
//            }
//            rs.close();
//            pstmt.close();
//        }
//        catch (SQLException ex)
//        {
//            ex.printStackTrace();
//        }
//        return fileFun;
//    }
// To get all ordering functionalities
    public static Vector getAllEcatFunctionalities(Connection conn)
    {
        Vector fileFun = new Vector();
        try
        {
            fileFun.add("1");
            fileFun.add("2");
            PreparedStatement pstmt = conn.prepareStatement("Select SUB_FUNC_ID from UM_spas104(NOLOCK) where MAIN_FUNC_ID in (1,2)");
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            String fun;
            while (rs.next())
            {
                fun = rs.getString(1);
                if ((fun != null) && !fileFun.contains(fun))
                {
                    fileFun.add(fun);
                    fileFun=EcatAuthorisation.subFuncExists(fun, conn, fileFun);
                }
            }
            rs.close();
            pstmt.close();

            //   fileFun.add("34");		// Add To cart
            //   fileFun.add("49");		// Group Orderable View
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return fileFun;
    }

// To find all sub-funtionalities in a main functioanlity
    static Vector subFuncExists(String func, Connection conn, Vector fileFun)
    {
        try
        {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
            ResultSet rs;
            //rs = stmt.executeQuery("select SUB_FUNC_ID from UM_spas104 where MAIN_FUNC_ID = " + func + " ");
           String query = ("select SUB_FUNC_ID from UM_spas104(NOLOCK) where MAIN_FUNC_ID = " + func + " ");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
           
           String fun;
            while (rs.next())
            {
                fun = rs.getString(1);
                if (fun != null)
                {
                    if (!fileFun.contains(fun))
                    {
                        fileFun.add(fun);
                        fileFun=EcatAuthorisation.subFuncExists(fun, conn, fileFun);
                    }

                }
            }
            rs.close();
            stmt.close();
            return fileFun;
        }
        catch (Exception e)
        {
           // System.out.println(e);
            e.printStackTrace();
            return fileFun;
        }
    }
}
