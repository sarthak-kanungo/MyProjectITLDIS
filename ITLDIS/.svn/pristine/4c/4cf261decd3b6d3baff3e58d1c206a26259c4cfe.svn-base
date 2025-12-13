package viewEcat.comEcat;

/*
File Name: ConnectionHolder 
PURPOSE: File to get the connection.
HISTORY:
DATE		BUILD	AUTHOR			MODIFICATIONS
NA		v3.4	Deepak Mangal	$$0 Created
*/
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class ConnectionHolder implements HttpSessionBindingListener {
  private Connection con = null;

  public ConnectionHolder(Connection con) {
    // Save the Connection
    this.con = con;
    try {
      con.setAutoCommit(false);  // transactions can extend between web pages!
    }
    catch(SQLException e) {
      // Perform error handling
	//  System.out.println("Exception occured "+e);
    }
  }

  public Connection getConnection() {
    return con;  // return the cargo
  }

  public void valueBound(HttpSessionBindingEvent event) {
    // Do nothing when added to a Session
  }

  public void valueUnbound(HttpSessionBindingEvent event) {
    // Roll back changes when removed from a Session
    // (or when the Session expires)
    try {
      if (con != null) {
        con.rollback();  // abandon any uncomitted data
		Thread.sleep(2000);
        con.close();
      }
    }
    catch (Exception e) {
      // Report it
	//  System.out.println("Exception occured "+e);
    }
  }
}