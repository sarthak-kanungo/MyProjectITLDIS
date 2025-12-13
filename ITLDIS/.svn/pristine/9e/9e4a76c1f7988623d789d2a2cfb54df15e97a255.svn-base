package viewEcat.comEcat;

/*
File Name: GetLanId.java
PURPOSE: FILE FOR GETTING THE LAN ID OF THE DEALER
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetLanId
{

    public String getLanId()
//	public static void main(String args[])
    {
        String line = "";
        String desc = "";
        String MAC = "";
        String returnLan = "";
        String[][] lan_id = new String[10][2];
        int counter = 0;
        try
        {
          //  System.out.println("*********************************************");
            // Get the Runtime and Process
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("ipconfig /all");
            // Read the response from the "ping" program
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            // Now parse the response to see if a reply or timeout was received
            while ((line = in.readLine()) != null)
            {
                // Check For "Ethernet adapter" in the line
                if (line.indexOf("Ethernet adapter") > -1)
                {
                    if (line.indexOf("Wireless") == -1)
                    {
                        while ((line = in.readLine()) != null)
                        {
                            // Check If Media Is Connected Or Not
                            if (line.indexOf("Media disconnected") > -1)
                            {
                                lan_id[counter][0] = "Disconnected";
                            }

                            // Check For String "Physical" In The Line
                            if (line.indexOf("Physical") > -1)
                            {
                                // Get the MAC Address Only If It Neither A Loop Back Address Nor "00-53-45-00-00-00"
                                MAC = line.substring(line.indexOf(":") + 2, line.length());
                                if ((!MAC.equals("00-53-45-00-00-00")) && (!MAC.equals("02-00-4C-4F-4F-50")))
                                {
                                    lan_id[counter][1] = MAC;
                                    counter++;
                                }
                                break;
                            }
                        }
                    }
                }
            }

            int check = 0;
            String media = "";

            // IF ONLY ONE MAC ADDRESS IS FOUND
            if (counter == 1)
            {
                if (lan_id[0][0] != null)
                {
                    check = 1;
                    media = lan_id[0][0];
                    returnLan = lan_id[0][1];
                  //  System.out.println("Media is " + media + " and LAN ID is " + returnLan);
                }
                else
                {
                    check = 1;
                    returnLan = lan_id[0][1];
                  //  System.out.println("LAN ID is " + returnLan);
                }

            }
            else
            {
                int total_disconnect = 0;
                int total_connect = 0;

                for (int i = 0; i < counter; i++)
                {
                    if (lan_id[i][0] != null)
                    {
                        total_disconnect++;
                    }
                    else
                    {
                        total_connect++;
                    }
                }
                // IF MEDIA IS DISCONNECTED FOR ALL MAC ADDRESS
                if (total_disconnect == counter)
                {
                    check = 1;
                    media = lan_id[0][0];
                    returnLan = lan_id[0][1];
                  //  System.out.println("Media is " + media + " and LAN ID is " + returnLan);
                }
                else
                {
                    for (int i = 0; i < counter; i++)
                    {
                        // TAKE THE FIRST MAC ADDRESS FOR WHICH MEDIA IS CONNECTED
                        if (lan_id[i][0] == null)
                        {
                            check = 1;
                            returnLan = lan_id[i][1];
                          //  System.out.println("LAN ID is " + returnLan);
                            break;
                        }
                    }
                }
            }

            if (check == 0)
            {
                returnLan = "NA";
               // System.out.println("LAN ID is " +returnLan);
            }

           // System.out.println("*********************************************");

            p.destroy();
            in.close();
            return returnLan;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "NA";
        }
    }
}
