package com.EAMG.common;

/**
 * @author avinash.pandey
 *
 */
public interface WebConstants{

     public static final String TARGET = "target";
     public static final String ALL = "all";
     public static final String CREATE = "create";
     public static final String ADDED = "added";
     public static final String UPDATE = "update";
     public static final String DELETE = "delete";
     public static final String VIEW = "view";
     public static final String TRUE = "true";
     public static final String FALSE = "false";
     public static final String ERROR = "error";
     public static final String ERROR_MESSAGE = "errorMessage";
     public static final String SUCCESS = "success";
     /* Start EAMG*/
     public static final String FORWARD_HOME = "home";
     public static final String FORWARD_LOGIN = "login";
     public static final String FORWARD_LOGOUT = "logout";
     public static final String FORWARD_INIT = "init";
     public static final String FORWARD_SUCCESS = "success";
     public static final String FORWARD_FAILURE = "failure";
     public static final String FORWARD_CREATE = "create";
     public static final String FORWARD_UPDATE = "update";
     public static final String FORWARD_DELETE = "delete";
     /*End*/
     /* Start EAMG*/
     public static final String REQ_ATTR_MESSAGE_TYPE = "messageType";
     public static final String REQ_ATTR_ERROR_MESSAGES_TYPE = "errorMessages";
     public static final String REQ_OBJECTTYPE = "objectType";
     public static final String REQ_OPTION_LINK_NAME = "optionLinkName";
     public static final String REQ_OPTION_LINK_URL = "optionLinkURL";
     public static final String REQ_ATTR_LABEL_TYPE = "labelType";
     public static final String REQ_ATTR_SEARCH_RESULT = "searchResult";
     public static final String REQ_ATTR_SUCCESS_MESSAGE = "successMessage";
     public static final String REQ_ATTR_FILE_UPLOAD = "fileUpload";
     public static final String REQ_ATTR_KEY_WORD = "keyWord";
 
     /*End*/

     /* Start EAMG*/
     public static final String FORWARD_SUCCESS_PAGE = "successPage";
     public static final String FORWARD_ERROR_PAGE = "errorPage";
     public static final String FORWARD_OPTIONLINK = "optionLink";
     public static final String FORM_PROPERTY_FORWARD = "forward";
     public static final String FORM_PROPERTY_SEARCH_TYPE = "searchType";
     public static final String FORWARD_INIT_PART = "initPart";
     public static final String FORWARD_OME_PART = "getOMEAPart";
    
     /*End*/

     /* Change Various Status of User*/
     public static final String FORWARD_INIT_CHANGE_PASSWORD = "init_userName";
     public static final String FORWARD_INIT_BLOCK_USER = "initBlockUser";
     public static final String FORWARD_INIT_BLOCK_USER_STATUS_AJAX = "blockUserStatus";
     public static final String FORWARD_INIT_DELETE = "init_delete";
     public static final String FORWARD_INIT_DELETE_STATUS_AJAX = "deleteStatus";
     public static final String FORWARD_INIT_VARIOUS_STATUS = "initVariousStatus";
     public static final String FORWARD_MODIFY_USER_AJAX = "modifyUserAjax";
     public static final String FORWARD_DELETE_USER_STATUS_AJAX = "deleteUserStatus";
     public static final String INIT_ASSIGN_ROLES = "initAssignRoles";
     public static final String ASSIGN_ROLES = "assignRoles";
     public static final String ASSIGN_ROLES_SUCCESS = "assignRolesSuccess";
     public static final String FORWORD_INIT_ASSIGN_ROLESBYUSER = "initAssignRolesByUser";
     public static final String FORWORD_ASSIGN_ROLESBYUSER = "assignRolesByUser";
     public static final String FORWARD_USER_SEARCH = "userSearch";
     //END//
     /*ERROR MASSAGE*/
     public static final String logException = "Caught in Exception";
     public static final String fLogException = "Caught in Final Exception";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FROM_PLATFORM = "Please Upload only GIF image in Platform";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_MODEL = "Please Upload only GIF image in Model";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_AGGREGATE = "Please Upload only JPG image in Group";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_VARIANT = "Please Upload only GIF image in variant.\n";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_VARIANT_JPG_IMAGE = "Please Upload only JPG image in variant";
     public static final String REQ_REQ_ATTR_IMAGE_SIZE = "Image Size: 175 x 175 (gif)";
     public static final String REQ_REQ_ATTR_IMAGE_SIZE_AGGREGATE = "Image Size: 105 x 105 (Jpg)";
     public static final String REQ_REQ_ATTR_IMAGE_RESIZE = "Resized Image should be 95 x 95 (Jpg)";
     public static final String REQ_REQ_ATTR_PLATFORM_IMAGE_SIZE = "Image Size: 384 x 175 (gif)";
     public static final String REQ_REQ_ATTR_VARIANT_IMAGE_SIZE = "Image Size: 230 x 175 (gif)\n";
     public static final String REQ_REQ_ATTR_VARIANT_IMAGE_SIZE_JPG = "Image Size: 230 x 175 (jpg)";
     public static final String REQ_REQ_ATTR_RENAME_TO = "RenameTo did not success";
     public static final String REQ_REQ_ATTR_DELETE_TO = " File Remove did not success";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_REPAIR_PROCEDURE = " Repair Procedure content should be only in .ZIP file format.";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FROM_ADDMESSAGE = "Please upload only valid file format ";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_NEW_LANG_CONTENT = " New language content should be only in .ZIP file format.";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_CREATE_REVISION = " Create Revision should be only in .ZIP file format.";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_COMMAN_MESSAGE = " File size can not exceed 1024 MB .";
     public static final String REQ_REQ_ATTR_ERROR_MSG_FORM_BROWSE_ZIP_FILE = " Heading should be only in .ZIP file format.";
     public static final String REQ_ATTR_ERROR_ERROR_PUBLICATION = "Please insert a Dot before  Extension";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_MSG_FORM_PUBLICATION_FILE = " Publication File should be only in .ZIP format.";
     public static final String REQ_ATTR_ERROR_INDEX_FILE_WITH_EXTENSION  = " Index File is not present in given ZIP file. Please upload valid Zip file.";
     public static final String REQ_ATTR_ERROR_MSG_FORM_AGGREGATE_BROWSE_PDF_FILE = " Group should be only in .PDF file format.";
     public static final String REQ_REQ_ATTR_ERROR_ERROR_COMMAN_PDF_FILE_MESSAGE = " PDF File size can not exceed 512 MB .";

     //QUESTIONS
     public static final String FORWARD_ASK_QUES = "askQuestion";
     /*End*/
}
