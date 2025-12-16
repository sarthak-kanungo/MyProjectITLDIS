package viewEcat.comEcat;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveSessionVariable extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        try
        {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (session_id.equals(getSession))
            {
                String varName = req.getParameter("varName");
                if (varName != null)
                {
                    String[] varArr = varName.split("@@");
                    for (int i = 0; i < varArr.length; i++)
                    {                       
                        session.removeValue(varArr[i]);
                    }
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
