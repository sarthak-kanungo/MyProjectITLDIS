package viewEcat.comEcat;

/*
File Name: Delete_file.java
PURPOSE: To delete the files.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.File;

public class Delete_file
{

    public static void del_file(File ss)
    {
        int j = 0, u = 0;
        int total_items = 0;
        File kk = ss;
        File pp = ss;
        if (ss.exists() && ss.isDirectory())
        {
            String parts[] = ss.list();
            String parts_del[] = new String[parts.length];
            for (int i = 0; i < parts.length; i++)
            {
                if (parts[i].equals(""))
                {
                    break;
                }
                else
                {
                    parts_del[j] = parts[i];
                    j++;
                }
            }
            total_items = j;
            if (total_items > 0)
            {
                for (int i = 0; i < total_items; i++)
                {
                    ss = new File(pp + "/" + parts_del[i]);
                    if (ss.exists() && ss.isFile())
                    {
                        ss.delete();
                    }
                    else if (ss.exists() && ss.isDirectory())
                    {
                        del_file(ss);
                    }
                }
                ss.delete();
            }
            kk.delete();
        }
        else
        {
            if (ss.exists() && ss.isFile())
            {
                ss.delete();
            }
        }
    }
}


