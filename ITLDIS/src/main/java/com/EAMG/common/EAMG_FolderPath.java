/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.EAMG.common;


public class EAMG_FolderPath
{
    String server_name = null;
    String mainURL = null;
    String ecatPath = null;
    /** Creates a new instance of EAMG_FolderPath */

    public EAMG_FolderPath( )
    {

    }

    public EAMG_FolderPath(String server_name,String mainURL,String ecatPath)
    {
        this.server_name=server_name;
        this.mainURL=mainURL;
        this.ecatPath=ecatPath;
    }
    //to get Image path.
    public String imagesPath()
    {
        String imagesPath = ecatPath+"dealer/images/";
        return imagesPath;
    }
    //to get anim path.
    public String animPath()
    {
        String animPath = ecatPath+"dealer/anim/";
        return animPath;
    }
    //to get print path.
    public String printPath()
    {
        String printPath = ecatPath+"dealer/ecat_print/";
        return printPath;
    }
    //to get dealer path.
    public String ecatPath_dealer()
    {
        String ecatPath_dealer = ecatPath+"dealer/";
        return ecatPath_dealer;
    }
    //to get server path.
    public String org_imagesPath()
    {
        String org_imagesPath = "http://"+server_name+"/ecatalogue/sol_org/org_print/";
        return org_imagesPath;
    }
    //to get Backup Image path.
    public String backupimagePath()
    {
        String backupimagePath = ecatPath+"dealer/backup_image/";
        return backupimagePath;
    }
    //to get Model Image path.
    public String modelPath()
    {
        String modelPath = ecatPath+"dealer/ecat_print/model_images/";
        return modelPath;
    }
    //to get Model Index path.
    public String modelIndexPath()
    {
        String modelIndexPath = ecatPath+"dealer/ecat_print/block_index/";
        return modelIndexPath;
    }
    //to get Model Image path.
    public String modelImage()
    {
        String modelImage = ecatPath+"dealer/ecat_print/model_images/";
        return modelImage;
    }
    //to get Group Image path.
    public String groupJPG()
    {
        String groupJPG = ecatPath+"dealer/ecat_print/group_jpg/";
        return groupJPG;
    }
    //to get Part Image path.
    public String part_imagesPath()
    {
        String part_imagesPath = ecatPath+"dealer/ecat_print/part_image/";
        return part_imagesPath;
    }
    //to get Group Image path.
    public String group_imagesPath()
    {
        String group_imagesPath = ecatPath+"dealer/ecat_print/group_image/";
        return group_imagesPath;
    }
    //to get Group patch path.
    public String patchesPath()
    {
        String patchesPath = ecatPath+"dealer/patches/";
        return patchesPath;
    }
    //to get Group records path.
    public String recordsPath()
    {
        String recordsPath = ecatPath+"dealer/ecat_print/change_records/";
        return recordsPath;
    }
    //to get Group Kit path.
    public String kitImagePath()
    {
        String kitIMAGE = ecatPath+"dealer/ecat_print/kit_image/";
        return kitIMAGE;
    }
}
