package viewEcat.comEcat;

import java.sql.Connection;

public class Session extends Exception
{
    public static String dsnPath = null;
    public static String servletURL = null;
    public static String getType = null;
    public static String date_OR_serial = null;
    public static String animURL = null;
    public static String imagesURL = null;
    public static String group_imagesURL = null;
    public static String ecatPATH = null;
    public static String dbUserName = null;
    public static String dbPassword = null;
    public static Connection conn = null;

    Session()
    {
    }

    ////////////////////////////// GET FUNCTIONS //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    public String getDsnPath()
    {
        return dsnPath;
    }

    public String getServletURL()
    {
        return servletURL;
    }

    public String getGetType()
    {
        return getType;
    }

    public String getDate_OR_serial()
    {
        return date_OR_serial;
    }

    public String getAnimURL()
    {
        return animURL;
    }

    public String getImagesURL()
    {
        return imagesURL;
    }

    public String getGroup_imagesURL()
    {
        return group_imagesURL;
    }

    public String getEcatPATH()
    {
        return ecatPATH;
    }

    public String getUserName()
    {
        return dbUserName;
    }

    public String getPassword()
    {
        return dbPassword;
    }
    
    public Connection getConnection()
    {
        return conn;
    }    
    /* #########################################*/

    ////////////////////////////// PUT FUNCTIONS //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    
    public void putConnection(Connection conn)
    {
        Session.conn = conn;
    }
    
    public void putDsnPath(String dsnPath)
    {
        Session.dsnPath = dsnPath;
    }

    public void putServletURL(String servletURL)
    {
        Session.servletURL = servletURL;
    }

    public void putGetType(String getType)
    {
        Session.getType = getType;
    }

    public void putDate_OR_serial(String date_OR_serial)
    {
        Session.date_OR_serial = date_OR_serial;
    }

    public void putAnimURL(String animURL)
    {
        Session.animURL = animURL;
    }

    public void putImagesURL(String imagesURL)
    {
        Session.imagesURL = imagesURL;
    }

    public void putGroup_imagesURL(String group_imagesURL)
    {
        Session.group_imagesURL = group_imagesURL;
    }

    public void putEcatPATH(String ecatPATH)
    {
        Session.ecatPATH = ecatPATH;
    }

    public void putUserName(String dbUserName)
    {
        Session.dbUserName = dbUserName;
    }

    public void putPassword(String dbPassword)
    {
        Session.dbPassword = dbPassword;
    }
    /* #########################################*/
}
