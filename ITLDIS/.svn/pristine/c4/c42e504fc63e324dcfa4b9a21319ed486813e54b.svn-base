/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.common.MethodUtility;

import HibernateMapping.ApplicationMaster;
import HibernateMapping.CustomerCategoryDiscount;
import HibernateMapping.CustomerCategoryMaster;
import HibernateMapping.Jobcardstatusmaster;
import HibernateMapping.Joblocationmaster;
import HibernateMapping.Jobtypemaster;
import HibernateMapping.Nextservicemaster;
import HibernateMapping.Ownerdrivenmaster;
import HibernateMapping.Servicetypemaster;
import beans.masterForm;
import dao.masterDAO;
import dao.serviceDAO;
import dbConnection.dbConnection;

/**
 *
 * @author vandana.singla
 */
public class masterAction extends DispatchAction {

    private static final String SUCCESS = "success";
    String dbPATH = new dbConnection().dbPathAuth;

    public ActionForward initOptions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "masterOption";
        return mapping.findForward(forward);
    }

    public ActionForward initCmpMasters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "masterCmpOption";
        return mapping.findForward(forward);
    }

    public ActionForward initJCMasters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "masterJCOption";
        return mapping.findForward(forward);
    }

    ///////////////////////////////////////***Job Type MASTER***////////////////////////////////////////////////
    public ActionForward initJobType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> JobTypeList = obj.getJobTypeList(mf, nameSrch);
            request.setAttribute("JobTypeList", JobTypeList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("jobTypeMaster");
    }

    public ActionForward manageJobType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            name = name.contains("@@") == true ? name.replace("@@", "&") : name;
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String cTypeName = request.getParameter("jobTypeName");
            String flag = request.getParameter("flag");
            Jobtypemaster jtm = new Jobtypemaster();
            jtm.setJobTypeDesc(name);
            jtm.setIsActive('Y');
            jtm.setFreeService('N');
            jtm.setLastUpdatedDate(new Date(new java.util.Date().getTime()));

            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addJobType(jtm);
            } else {
                data_str = obj.UpdateJobType(jtm, status, id, type, cTypeName, flag);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***SERVICE_TYPE_MASTER***////////////////////////////////////////////////
    public ActionForward initServiceType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> serviceTypeList = obj.getServiceTypeList(mf, nameSrch);
            request.setAttribute("ServiceTypeList", serviceTypeList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("serviceTypeMaster");
    }

    public ActionForward manageServiceType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data_str = "";
        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String cTypeName = request.getParameter("serviceTypeName");

            if (type.equalsIgnoreCase("add")) {
                Servicetypemaster stm = new Servicetypemaster();
                stm.setServiceTypeDesc(mf.getServicesTypedesc());
                stm.setIsActive('Y');
                stm.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                stm.setLastUpdatedBy(user_id);
                data_str = obj.addServiceType(stm);
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initServiceType(mapping, form, request, response);
                return mapping.findForward("serviceTypeMaster");
            } else {
                Servicetypemaster stm = new Servicetypemaster();
                name = name.contains("@@") == true ? name.replace("@@", "&") : name;
                stm.setServiceTypeDesc(name);
                stm.setIsActive('Y');
                stm.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                stm.setLastUpdatedBy(user_id);
                data_str = obj.UpdateServiceType(stm, status, id, type, cTypeName);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***MECHANIC_MASTER***////////////////////////////////////////////////

    public ActionForward initMechanicMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            HttpSession session = request.getSession();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            mf.setDealer_code((String) session.getAttribute("dealerCode"));
            ArrayList<masterForm> mechanicList = obj.getMechanicList(mf, nameSrch);
            request.setAttribute("mechanicList", mechanicList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("mechanicMaster");

    }

    public ActionForward manageMechanic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String mechanicName = mf.getMechanic_Name();
            String mechanicCode = mf.getMechanic_Code();
            String workerType = mf.getWorker_type();

            //System.out.println("workerType" + workerType);
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addMechanicM(mechanicCode, mechanicName, workerType, user_id, (String) session.getAttribute("dealerCode"));
            } else {
                data_str = obj.UpdateMechanicM(status, id, user_id, mechanicCode, mechanicName, type, (String) session.getAttribute("dealerCode"));
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***BAY_MASTER***////////////////////////////////////////////////

    public ActionForward initBayMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> bayList = obj.getBayList(mf, nameSrch);
            request.setAttribute("bayList", bayList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("bayMaster");

    }

    public ActionForward manageBay(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {

            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String bayName = mf.getBayName();
            String bayCode = mf.getBayCode();
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addBayMaster(bayCode, bayName, user_id);
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initBayMaster(mapping, form, request, response);
                return mapping.findForward("bayMaster");

            } else {
                data_str = obj.UpdateBayMaster(status, id, user_id, bayCode, bayName, type);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***WAGE_MASTER***////////////////////////////////////////////////
    public ActionForward initWageMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> wageList = obj.getWageList(mf, nameSrch);
            LinkedHashSet<LabelValueBean> dealerCodeList = obj.getDealerCode();
            request.setAttribute("wageList", wageList);
            request.setAttribute("dealerCodeList", dealerCodeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("wageMaster");

    }

    public ActionForward manageWage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data_str = "";
        try {
            HttpSession session = request.getSession();

            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            String wageName = mf.getWageName();
            // String wageCode = mf.getWageCode();
            double wageValue = mf.getWageValue();
            String type = request.getParameter("type");
            // String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            String dealer = request.getParameter("dealer_code");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addWageMaster(wageName, wageValue, user_id, mf.getDealer_code());
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initWageMaster(mapping, form, request, response);
                return mapping.findForward("wageMaster");
            } else {
                data_str = obj.UpdateWageMaster(status, user_id, wageName, wageValue, type, dealer);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
/////////////////////////*Action Master*/////////////////////

    public ActionForward initManageAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> actionList = obj.getActionList(mf, nameSrch);
            request.setAttribute("actionList", actionList);
            request.setAttribute("complaintCodeList", obj.getComplaintCode());
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("actionMaster");

    }

    public ActionForward manageAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        String actionDesc = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String tempDesc = mf.getActionDesc();
            if (tempDesc != null) {
                actionDesc = tempDesc.replace('`', '&');
            }
            String actionCode = mf.getActionCode();
            String type = request.getParameter("type");
            String compCode = mf.getComplaintCode();
            float serviceHrs = mf.getServiceHrs();
            String acCodeId = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addActionMaster(actionCode, actionDesc, compCode, serviceHrs, user_id);
            } else {

                data_str = obj.UpdateActionMaster(acCodeId, actionCode, actionDesc, status, type, compCode, serviceHrs, user_id);

            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***AGGREGATE_MASTER***////////////////////////////////////////////////

    public ActionForward initAggregateMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> aggregateList = obj.getAggregateList(mf, nameSrch);
            request.setAttribute("aggregateList", aggregateList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("aggregateMaster");

    }

    public ActionForward manageAggregateMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String tempaggDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String aggDesc = mf.getAggDesc();
            if (aggDesc != null) {
                tempaggDesc = aggDesc.replace('`', '&');
            }
            String aggCode = mf.getAggCode();
            String type = request.getParameter("type");
            String id = mf.getId();

            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(aggCode, "Aggregatemaster", "aggCode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addAggregateMaster(aggCode, tempaggDesc, user_id);
            } else {
                data_str = obj.UpdateAggregateMaster(status, id, user_id, aggCode, tempaggDesc, type, mf.getPrimary_id());
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***COMPLAINT_CODE_MASTER***////////////////////////////////////////////////
    public ActionForward initComplaintCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> complaintCodeList = obj.getComplaintCodeList(mf, nameSrch);
            request.setAttribute("complaintCodeList", complaintCodeList);
            LinkedHashSet<LabelValueBean> assmCodeList = obj.getAssemblyCode();
            request.setAttribute("assmCodeList", assmCodeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("complaintCodeMaster");
    }

    public ActionForward manageComplaintCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String compDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String tempDesc = mf.getCompDesc();
            if (tempDesc != null) {
                compDesc = tempDesc.replace('`', '&');
            }
            String compCode = mf.getCompCode();
            String assmId = mf.getAssmId();
            String compid = mf.getCompid();
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(compCode, "Causalcodemaster", "compCode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addComplaintCodeMaster(compCode, compDesc, assmId, user_id);
            } else {
                data_str = obj.UpdateComplaintCode(status, id, user_id, compCode, compDesc, assmId, type, mf.getPrimary_id(), compid);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***CAUSAL_CODE_MASTER***////////////////////////////////////////////////

    public ActionForward initCausalCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> causalCodeList = obj.getCausalCodeList(mf, nameSrch);
            request.setAttribute("causalCodeList", causalCodeList);
            //System.out.println("before");
            LinkedHashSet<LabelValueBean> causalList = obj.getCausalCode();
            request.setAttribute("causalList", causalList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("causalCodeMaster");
    }

    public ActionForward manageCausalCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String causalDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String causalCode = mf.getCausalCode();
            String tempDesc = mf.getCausalDesc();
            if (tempDesc != null) {
                causalDesc = tempDesc.replace('`', '&');
            }
            String compCode = mf.getCompCode();
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(causalCode, "Causalcodevsconsequencecode", "causalCode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addCausalCodeMaster(causalCode, causalDesc, compCode, user_id);
            } else {
                data_str = obj.UpdateCausalCode(status, id, user_id, causalCode, causalDesc, compCode, type, mf.getPrimary_id());
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***APPLICATION_MASTER***////////////////////////////////////////////////
    public ActionForward initApplicationMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            List<masterForm> applicationList = obj.getApplicationList(mf, nameSrch);
            request.setAttribute("applicationList", applicationList);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("applicationMaster");

    }

    public ActionForward manageApplicationMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String appDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;


            ApplicationMaster app = new ApplicationMaster();
            String tempaggDesc = mf.getApplicationDesc();
            if (tempaggDesc != null) {
                appDesc = tempaggDesc.replace('`', '&');
            }

            String appCode = request.getParameter("applicationCode");
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String userId = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addApplicationMaster(mf, appCode, appDesc, userId);
            } else {
                data_str = obj.UpdateApplicationMaster(id, appCode, appDesc, type, status, userId);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***JOB CARD***////////////////////////////////////////////////

    public ActionForward initManageJobCard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("manageJobCard");
    }

    public ActionForward initComplaintJobCard(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            LinkedHashSet<LabelValueBean> aggregateList = obj.getAggregateCode();
            request.setAttribute("aggregateList", aggregateList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initComplaintJobCard");
    }

    public ActionForward manageCompJobCard(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            // masterForm mf = (masterForm) form;
            String[] compVerbatim = request.getParameterValues("compVerbatim");
            String[] aggCode = request.getParameterValues("aggCode");
            String[] compCode = request.getParameterValues("compCode");
            String[] causalCode = request.getParameterValues("causalCode");
            String[] consequenceCode = request.getParameterValues("consequenceCode");
            String user_id = (String) session.getAttribute("user_id");
            data_str = obj.addCompJobCard(compVerbatim, aggCode, user_id, compCode, causalCode, consequenceCode);

            request.setAttribute("show_message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0]);

            if (data_str.split("@@")[0].equalsIgnoreCase("success")) {
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initComplaintJobCard");
            } else {
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "masterAction.do?option=initComplaintJobCard");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("message");
    }

    public ActionForward initActionJobCard(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            LinkedHashSet<LabelValueBean> compJCList = obj.getComplaintJobCardList();
            request.setAttribute("compJCList", compJCList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initActionJobCard");
    }

    public ActionForward getServiceHrsAjax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;

        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            PrintWriter pw = response.getWriter();
            String nameObj = request.getParameter("nameObj");
            String objjCode = request.getParameter("objjCode");
            float result = obj.getServiceHrsById(objjCode, nameObj);
            pw.print(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward(null);
    }

    public ActionForward manageActionJobCard(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            // masterForm mf = (masterForm) form;
            String[] complaintCode = request.getParameterValues("complaintCode");
            String[] actionCode = request.getParameterValues("actionCode");
            String[] serviceHrs = request.getParameterValues("serviceHrs");

            String user_id = (String) session.getAttribute("user_id");
            data_str = obj.addeActionJobCard(complaintCode, actionCode, user_id, serviceHrs);

            request.setAttribute("show_message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0]);

            if (data_str.split("@@")[0].equalsIgnoreCase("success")) {
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initActionJobCard");
            } else {
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "masterAction.do?option=initActionJobCard");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("message");
    }
    ////////////////////////////////***LANGUAGE CONTENT***///////////////////////////////////////////////////

    public ActionForward initLanguageContent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("languageContent");
    }

    ///////////////////////////////////////***JOB CARD STATUS MASTER***////////////////////////////////////////////////
    public ActionForward initJobCardStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> JobCardStatusList = obj.getJobCardStatusList(mf, nameSrch);
            request.setAttribute("JobCardStatusList", JobCardStatusList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("jobCardStatus");
    }

    public ActionForward manageJobCardStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String statusDesc = request.getParameter("statusDesc");
            Jobcardstatusmaster jcs = new Jobcardstatusmaster();
            jcs.setStatusDesc(name);
            jcs.setIsActive('Y');
            jcs.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addJobCardStatus(jcs);
            } else {
                data_str = obj.UpdateJobCardStatus(jcs, type, status, id, statusDesc);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***JOB LOCATION MASTER***////////////////////////////////////////////////
    public ActionForward initJobLocation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> JobLocationList = obj.getJobLocationList(mf, nameSrch);
            request.setAttribute("JobLocationList", JobLocationList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("jobLocation");
    }

    public ActionForward manageJobLocation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String locationDesc = request.getParameter("locationDesc");
            if (type.equalsIgnoreCase("add")) {
                Joblocationmaster joblocationmaster = new Joblocationmaster();
                joblocationmaster.setLocationDesc(mf.getLocationDesc());
                joblocationmaster.setIsActive('Y');
                joblocationmaster.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                joblocationmaster.setLastUpdatedBy(user_id);
                data_str = obj.addJobLocation(joblocationmaster);
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initJobLocation(mapping, form, request, response);
                return mapping.findForward("jobLocation");
            } else {
                name = name.contains("@@") == true ? name.replace("@@", "&") : name;
                Joblocationmaster joblocationmaster = new Joblocationmaster();
                joblocationmaster.setLocationDesc(name);
                joblocationmaster.setIsActive('Y');
                joblocationmaster.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
                joblocationmaster.setLastUpdatedBy(user_id);
                data_str = obj.UpdateJobLocation(joblocationmaster, status, id, type, locationDesc);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***Next Service From MASTER***////////////////////////////////////////////////
    public ActionForward initNextServiceFrom(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> nextServiceList = obj.getNextServiceList(mf, nameSrch);
            request.setAttribute("NextServiceList", nextServiceList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("nextserviceFrom");
    }

    public ActionForward manageNextServiceFrom(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String cTypeName = request.getParameter("nextServiceDesc");
            Nextservicemaster nsm = new Nextservicemaster();
            nsm.setNextServiceDesc(name);
            nsm.setIsActive('Y');
            nsm.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addNextService(nsm);
            } else {
                data_str = obj.UpdateNextService(nsm, status, id, type, cTypeName);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***Owner Driven MASTER***////////////////////////////////////////////////
    public ActionForward initOwnerDriven(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> OwnerDrivenList = obj.getOwnerDrivenList(mf, nameSrch);
            request.setAttribute("OwnerDrivenList", OwnerDrivenList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("ownerDriven");
    }

    public ActionForward manageOwnerDriven(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String ownerDrivenDesc = request.getParameter("ownerDrivenDesc");
            Ownerdrivenmaster odm = new Ownerdrivenmaster();
            odm.setOwnerDrivenDesc(name);
            odm.setIsActive('Y');
            odm.setLastUpdatedDate(new Date(new java.util.Date().getTime()));
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addOwnerDriven(odm);
            } else {
                data_str = obj.UpdateOwnerDriven(odm, status, id, type, ownerDrivenDesc);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***BILL MASTER***////////////////////////////////////////////////

    public ActionForward initBillMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> billList = obj.getBillList(mf, nameSrch);
            request.setAttribute("billList", billList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("billMaster");
    }

    public ActionForward manageBillMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            name = name.contains("@@") == true ? name.replace("@@", "&") : name;
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String billDesc = request.getParameter("billDesc");
            String percentage = request.getParameter("percentage");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addBillMaster(name, percentage, user_id);
            } else {
                data_str = obj.UpdateBillMaster(status, id, name, type, user_id, billDesc, percentage);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***Other Job Work MASTER***////////////////////////////////////////////////

    public ActionForward initOtherJobWork(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> otherJobWorkList = obj.getOtherJobWorkList(mf, nameSrch);
            request.setAttribute("otherJobWorkList", otherJobWorkList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("otherJobWork");
    }

    public ActionForward manageOtherJobWork(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            name = name.contains("@@") == true ? name.replace("@@", "&") : name;
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String workDesc = request.getParameter("workDesc");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addOtherJobWork(name, user_id);
            } else {
                data_str = obj.UpdateOtherJobWork(status, id, name, type, user_id, workDesc);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***CONSEQUENCE MASTER***////////////////////////////////////////////////
    public ActionForward initConsequenceMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> consequenceList = obj.getConsequenceList(mf, nameSrch);
            request.setAttribute("consequenceList", consequenceList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("consequenceMaster");

    }

    public ActionForward manageConsequenceMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        String consDesc = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String tempDesc = mf.getConsequenceDesc();
            if (tempDesc != null) {
                consDesc = tempDesc.replace('`', '&');
            }
            String consCode = mf.getConsequenceCode();
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(consCode, "Causalcodevsconsequencecode", "Consequencecode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addConsequenceMaster(consCode, consDesc, user_id);
            } else {
                data_str = obj.UpdateConsequenceMaster(status, id, user_id, consCode, consDesc, type, mf.getPrimary_id());
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***CAUSAL VS CONSEQUENCE CODE MASTER***////////////////////////////////////////////////

    public ActionForward initCausalVsConsequence(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            serviceDAO obj1 = new serviceDAO(conn);
            masterForm mf = (masterForm) form;
            LinkedHashSet<LabelValueBean> causalCodeList = obj1.getCausalCode();
            request.setAttribute("causalCodeList", causalCodeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("causalVsConsequence");

    }

    public ActionForward getAssignedConsequenceCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;

            String MHparamStr = obj.getAssignedConsequenceCode(mf);

            PrintWriter pw = response.getWriter();
            pw.write(MHparamStr);
            pw.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward assignConsequenceCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            String cntxpath = request.getContextPath();
            masterForm mf = (masterForm) form;
            String results = "";
            String causalCodeItm = mf.getCausalCode();
            if (causalCodeItm != null) {
                causalCodeItm = causalCodeItm.trim();
            }
            String cons_param[] = request.getParameterValues("assigned_consequence");
            String user_id = (String) session.getAttribute("user_id");
            results = obj.setCausalcodeVSconsequenceCode(causalCodeItm, cons_param, user_id);
            request.setAttribute("show_message", results.split("@@")[1]);
            request.setAttribute("result", results.split("@@")[0].toUpperCase());
            if (results.split("@@")[0].toUpperCase().equals("SUCCESS")) {
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initCausalVsConsequence");
            } else {
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "masterAction.do?option=initCausalVsConsequence");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("message");
    }

    public ActionForward initManageFunc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "managefunction";
        Connection conn = null;
        conn = new dbConnection().getConnection();
        masterDAO obj = new masterDAO(conn);
        masterForm mf = (masterForm) form;
        obj.getFunctionalityList(mf);
        request.setAttribute("functionlists", mf.getFunctionlist());
        return mapping.findForward(forward);
    }

    public ActionForward getTableInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String functionName = request.getParameter("funcname");
            ArrayList<masterForm> tableinfo = obj.gettableDesc(mf, functionName);
            session.setAttribute("tableinfo", tableinfo);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("getfunctionAjax");
    }

    public ActionForward updateData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            Vector tempVector = new Vector();
            String[] tabledata = request.getParameterValues("tabledata");
            String tablename = mf.getTablename();

            //System.out.println(tabledata);
            for (int j = 0; j < tabledata.length; j++) {
                //System.out.println(tabledata[j]);
                tempVector.add(tabledata[j]);
            }
            obj.updateTable(tempVector, tablename);
            request.setAttribute("testval", 1);
            obj.getFunctionalityList(mf);
            request.setAttribute("functionlists", mf.getFunctionlist());
            // String functionName = request.getParameter("funcname");

            //ArrayList<masterForm> tableinfo = obj.gettableDesc(mf, functionName);
            //session.setAttribute("tableinfo", tableinfo);


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("managefunction");
    }

    ///////////////////////////////////////***CONTENT MASTER***////////////////////////////////////////////////
    public ActionForward initContentMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> contentList = obj.getContentList(mf, nameSrch);
            request.setAttribute("contentList", contentList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("contentMaster");
    }

    public ActionForward manageContentMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String contentDesc = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            //System.out.println("contents" + contentDesc);
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addContentMaster(mf.getContentList(), user_id);
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initContentMaster(mapping, form, request, response);
                return mapping.findForward("contentMaster");
            } else {
                contentDesc = contentDesc.contains("@@") == true ? contentDesc.replace("@@", "&") : contentDesc;
                data_str = obj.UpdateContentMaster(status, id, contentDesc, type, user_id);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward uploadContentMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String file = request.getParameter("file");
            //System.out.println("file" + file);
            data_str = obj.uploadContentMaster(file, user_id);

            PrintWriter pw = response.getWriter();
            pw.write(data_str);

            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward initSubContentMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> subContentList = obj.getSubContentList(mf, nameSrch);
            request.setAttribute("subContentList", subContentList);
            LinkedHashSet<LabelValueBean> contentList = obj.getContentList();
            request.setAttribute("contentList", contentList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("subContentMaster");
    }

    public ActionForward manageSubContentMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String contentDesc = request.getParameter("subContentDesc");
            String contentID = request.getParameter("contentID");
            //System.out.println("**" + contentID);
            if (type.equalsIgnoreCase("add")) {
                contentID = mf.getContent_id();
                data_str = obj.addSubContentMaster(mf.getSubContent(), user_id, contentID);
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initSubContentMaster(mapping, form, request, response);
                return mapping.findForward("subContentMaster");
            } else {
                contentDesc = contentDesc.contains("@@") == true ? contentDesc.replace("@@", "&") : contentDesc;
                data_str = obj.UpdateSubContentMaster(status, id, name, type, user_id, contentDesc, contentID);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward uploadSubContentMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String file = request.getParameter("file");
            // System.out.println("file" + file);
            data_str = obj.uploadContentMaster(file, user_id);

            PrintWriter pw = response.getWriter();
            pw.write(data_str);

            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    ///////////////////////////////////////***VENDOR MASTER***////////////////////////////////////////////////
    //initSubAssemblyMaster
    public ActionForward initSubAssemblyMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            // System.out.println("nameSrch==" + nameSrch);
            ArrayList<masterForm> subassemblyList = obj.getSubAssemblyList(mf, nameSrch);
            request.setAttribute("subassemblyList", subassemblyList);
            LinkedHashSet<LabelValueBean> aggList = obj.getCommonLabelValues("Subaggregatemaster", "subAggCode", "subAggDesc", "subAggDesc", "where isActive='Y'");//obj.getAggCode();
            request.setAttribute("aggList", aggList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("subAssemblyMaster");
    }

    public ActionForward manageSubAssemblyMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String subAssemblyDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            String subAssemblyCode = mf.getSubAssemblyCode();
            String tempaggDesc = mf.getSubAssemblyDesc();
            if (tempaggDesc != null) {
                subAssemblyDesc = tempaggDesc.replace('`', '&');
            }
            String aggCode = mf.getAggCode();
            String idpk = request.getParameter("primary_id");
            String assemblyid = request.getParameter("assemblyid");
            // System.out.println("aggCode inside the action==" + aggCode);
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                // data_str = obj.check_in_Db(subAssemblyCode, "VendorMaster", "VendorCode", "");
            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addSubAssemblyMaster(subAssemblyCode, subAssemblyDesc, aggCode, user_id);
            } else {
                data_str = obj.UpdateSubAssemblyMaster(status, id, user_id, subAssemblyCode, subAssemblyDesc, aggCode, type, idpk, assemblyid);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    //lABOUR_CODE_MASTER INIT METHOD created on 13/05/14 by Megha

    public ActionForward initLabourCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            String compcodefilter = request.getParameter("compcodefilter");
            ArrayList<masterForm> labourCodeList = obj.getLabourCodeList(mf, nameSrch, compcodefilter);
            request.setAttribute("labourCodeList", labourCodeList);
            LinkedHashSet<LabelValueBean> compCodeList = obj.getDefectCode();
            request.setAttribute("compCodeList", compCodeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("labourCodeMaster");
    }

    //manageLaborCode created on 14/05/14 by Megha
    public ActionForward manageLaborCode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String labourDesc = mf.getLabourDesc();
            String labourCode = mf.getLabourCode();
            String compCode = mf.getCompCode();
            int labourHrs = mf.getLabourhrs();
//            System.out.println("in action:"+labourHrs);
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(labourCode, "labour_hrs_master", "LabourCodeId", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addLabourCodeMaster(labourCode, labourDesc, compCode, user_id, labourHrs);
            } else {
                data_str = obj.UpdateLaborCode(status, id, user_id, labourCode, labourDesc, compCode, type, mf.getPrimary_id(), labourHrs);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    //Sub-Aggregate MASTER INIT METHOD created on 14/05/14 by Megha
    public ActionForward initSubAggregateMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            String aggcodefilter = request.getParameter("aggcodefilter");
            ArrayList<masterForm> SubAggregateCodeList = obj.getSubAggregateList(mf, nameSrch, aggcodefilter);
            request.setAttribute("SubAggregateCodeList", SubAggregateCodeList);
            LinkedHashSet<LabelValueBean> AggregateCodeList = obj.getAggregateCodeWithId();
            request.setAttribute("AggregateCodeList", AggregateCodeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("SubAggregateMaster");
    }

    //manage Sub-Aggregate created on 14/05/14 by Megha
    public ActionForward manageSubAggregate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String subaggDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String tempaggDesc = mf.getSubaggDesc();
            if (tempaggDesc != null) {
                subaggDesc = tempaggDesc.replace('`', '&');
            }
            String subaggCode = mf.getSubaggCode();
            String aggCode = mf.getAggCode();
            String type = request.getParameter("type");
            String oldsubaggcode = mf.getId();//oldsubaggcode
            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");

            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(subaggCode, "Subaggregatemaster", "subAggCode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addSubAggregateMaster(subaggCode, subaggDesc, aggCode, user_id);
            } else {
                data_str = obj.UpdateSubAggregateCode(status, oldsubaggcode, user_id, subaggCode, subaggDesc, aggCode, type, mf.getPrimary_id());
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    /**
     * check dealer code in db for wage master  created on 31/05/14 by Megha
     */
    public ActionForward checkdealerCodeAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Connection conn1 = null;
        PrintWriter pw = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String result = obj.check_in_Db(request.getParameter("dealer_code"), "Wagemaster", "dealerCode", "");
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
                pw.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    public ActionForward initFormCheckList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;


            LinkedHashSet<LabelValueBean> jobTypeList = obj.getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc", "jobTypeDesc", "");                           //obj.getJobtypeList();
            if (request.getParameter("dataflag") != null) {
                obj.getFormCheckList(mf);
            }
            request.setAttribute("jobTypeList", jobTypeList);
            request.setAttribute("masterForm", mf);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("formCheckListMaster");
    }

    public ActionForward addFormCheckList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String forwardto = "";
        String data_str = "";

        try {

            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String user_id = (String) session.getAttribute("user_id");
            // System.out.println("model code" + mf.getModelCode() + " jobtype" + mf.getJobType());
            int contentI;
            ArrayList<String> dataList = null;
            TreeMap<Integer, ArrayList<String>> tMap = new TreeMap();

            String[] contentId = mf.getContentId();

            String subContentId[] = null;

            if (contentId != null) {
                for (int c = 0; c < contentId.length; c++) {
                    dataList = new ArrayList();

                    contentI = Integer.valueOf(contentId[c]);

                    tMap.put(contentI, dataList);

                    subContentId = request.getParameterValues(contentId[c] + "SubContent");

                    for (int s = 0; s < subContentId.length; s++) {
                        dataList.add(subContentId == null ? "0" : subContentId[s]);

                    }
                }
            }
            data_str = obj.addFormCheckListData(mf, tMap, user_id);


            request.setAttribute("show_message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0].toUpperCase());

            if (data_str.split("@@")[0].toUpperCase().equals("SUCCESS")) {
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initFormCheckList");
                forwardto = "message";
            } else {
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initFormCheckList");
                forwardto = "message";
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forwardto);
    }

    /**
     * init part master created on 4/6/14 by Megha
     */
    public ActionForward initPartMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            ArrayList<masterForm> partList = null;
            LinkedHashSet<LabelValueBean> jobTypeList = obj.getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc", "jobTypeDesc", "");
            if (mf.getJobtype() != null) {
                //   System.out.println("for job type data");
                partList = obj.GetPartDetails4jobtype(mf.getJobtype(), mf.getModelNo());
                mf.setName(mf.getName() + "@@" + mf.getModelNo());
            }
            request.setAttribute("partList", partList);
            request.setAttribute("masterform", mf);
            // System.out.println("size:" + jobTypeList.size());
            request.setAttribute("jobTypeList", jobTypeList);
//            request.setAttribute("masterForm", mf);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initPartMaster");
    }

    /**
     *  getPartNumberAjax for part suggestion box detail of managemaster for parts/lubes created on 5/6/14 by Megha
     */
    public ActionForward getPartNumberAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Connection conn1 = null;
        PrintWriter pw = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj1 = new masterDAO(conn);
            String tableName = "CatPart";
            String partno = request.getParameter("partno");
            String comptype = request.getParameter("comptype");
            String result = obj1.getComponentList(partno, comptype, "partNo", tableName, "partType");
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
                pw.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    /**
     * getPartNumberAjax for description suggestion box detail of managemaster for parts/lubes created on 5/6/14 by Megha
     */
    public ActionForward getPartDescAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pw = null;
        try {

            masterDAO obj1 = new masterDAO();
            String partno = request.getParameter("partDesc").trim();
            String comptype = request.getParameter("comptype");
            String tableName = "CatPart";
            String result = obj1.getComponentList(partno, comptype, "p1", tableName, "partType");
            pw = response.getWriter();
            pw.write(result);//IDLER GEAR BUSH

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    /**
     * getPartPriceBypartNo of manage master for parts/lubes created on 5/6/14 by Megha
     */
    public ActionForward getPartPriceBypartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj1 = new masterDAO(conn);
            HttpSession session = request.getSession();
            String priceListCode = (String) session.getAttribute("priceListCode");
            String partno = request.getParameter("partno");
            String result = obj1.getPriceByPartNo(partno, priceListCode);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    /**
     * getPartPriceBypartDesc of manage master for parts/lubes created on 5/6/14 by Megha
     */
    public ActionForward getPartPriceBypartDesc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj1 = new masterDAO(conn);
            String partDesc = request.getParameter("partDesc");
            HttpSession session = request.getSession();
            String priceListCode = (String) session.getAttribute("priceListCode");
            String partno = obj1.getPart_in_db(partDesc);
            String result = obj1.getPriceByPartNo(partno, priceListCode);//, "p1", TablePartprice, TablePartmaster, "price", "partNo");
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    /**
     * getPartCheck for parts/lubes created on 5/6/14 by Megha
     */
    public ActionForward getPartCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj1 = new masterDAO(conn);
            //serviceForm mf = (serviceForm) form;
            String partno = request.getParameter("partno");
            String result = obj1.getPartNoCheck(partno);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    /**
     * manage part/lube master created on 5/6/14 by Megha
     */
    public ActionForward managePartLubeMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        String forwardto = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String data_str = obj.addPartLubes4Master(mf);
            request.setAttribute("message", data_str.split("@@")[1]);
            request.setAttribute("show_message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
            if (data_str.split("@@")[0].toUpperCase().equals("SUCCESS")) {
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initPartMaster");
                forwardto = "message";
            } else {
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "masterAction.do?option=initPartMaster");
                forwardto = "message";
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forwardto);
    }

    public ActionForward initWarrantyModel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            LinkedHashSet<LabelValueBean> warranylist = obj.getCommonLabelValues("WarrantyModel", "id", "modelCode", "", "nil");
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> warrantymodellist = obj.getWarrantyModelList(mf, nameSrch);

            request.setAttribute("warrantymodellist", warrantymodellist);
            request.setAttribute("warranylist", warranylist);


            //  System.out.println("warrantylist" + warrantymodellist);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("warrantymodel");
    }

//manageWarrantyModel
    public ActionForward manageWarrantyModel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        String forwardto = "";
        String type = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String data_str = "";
            type = request.getParameter("type");

            //  System.out.println("action" + type);

            if (type != null && type.equals("add")) {

                data_str = obj.addWarrantyModel(mf);

                LinkedHashSet<LabelValueBean> warranylist = obj.getCommonLabelValues("WarrantyModel", "id", "modelCode", "", "nil");
                String nameSrch = request.getParameter("nameSrch");
                ArrayList<masterForm> warrantymodellist = obj.getWarrantyModelList(mf, nameSrch);

                request.setAttribute("warrantymodellist", warrantymodellist);
                request.setAttribute("warranylist", warranylist);
            } else if (type != null && type.equals("edit")) {

                data_str = obj.UpdateWarrantyModel(mf, request.getParameter("modelid"));
            }

            //System.out.println("outside");

            request.setAttribute("show_message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
            if (type != null && type.equals("edit")) {
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (type != null && type.equals("edit")) {
            return mapping.findForward(null);
        } else {

            return mapping.findForward("warrantymodel");
        }
    }

    public ActionForward initWarrantyTax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;

            if (mf.getTaxTypeName() == null || mf.getTaxTypeName().equals("")) {
                mf.setTaxTypeName(null);
            }
            if (mf.getStateName() == null || mf.getStateName().equals("")) {
                mf.setStateName(null);
            }
            request.setAttribute("taxTypeList", MethodUtility.getCommonLabelValueHiber("MSWwarrantytaxtypes", "typeID", "typeDescription", "typeDescription", " "));
            request.setAttribute("stateList", MethodUtility.getCommonLabelValueHiber("GENstates", "stateID", "stateName", "stateName", " "));
            ArrayList<masterForm> warrantyTaxPercList = obj.getWarrantyTaxPercList(mf);
            request.setAttribute("warrantyTaxPercList", warrantyTaxPercList);
            request.setAttribute("masterForm", mf);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("warrantyTaxPercMaster");
    }

    /**
     * warrantytextPer
     */
    public ActionForward manageWarrantyTax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            mf.setUserId(session.getAttribute("user_id").toString());
            mf.setDealer_code(session.getAttribute("dealerCode").toString());
            String type = request.getParameter("type");
            String data_str = "";
            ActionMessages messages = new ActionMessages();
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addWarrantytaxPer(mf);
                if (data_str.equalsIgnoreCase("Success")) {
                    messages.add("SUCCESS", new ActionMessage("label.common.warrantytaxpercSuccess"));
                } else if (data_str.equalsIgnoreCase("Exist")) {
                    messages.add("EXIST", new ActionMessage("label.common.warrantytaxpercExist"));
                } else {
                    messages.add("FAILURE", new ActionMessage("label.common.warrantytaxpercFailure"));
                }
                saveErrors(request, messages);
                initWarrantyTax(mapping, form, request, response);
                return mapping.findForward("warrantyTaxPercMaster");

            } else {
                data_str = obj.updateWarrantytaxPer(mf);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    /**
     * init PDI/INS Parameter Vs Make created on 31/10/2014 by Megha
     */
    public ActionForward initpdi_ins_parameterVsmake(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            mf.setPartNo(mf.getPartNo() == null ? "" : mf.getPartNo());
            mf.setMakeShow(mf.getMakeShow() == null ? "" : mf.getMakeShow());
            ArrayList<masterForm> pdi_insparamVsmakeList = obj.getpdi_insparamVsmakeList(mf);
            request.setAttribute("pdi_insparamVsmakeList", pdi_insparamVsmakeList);
            request.setAttribute("masterForm", mf);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("pdi_ins_parameterVsmake");
    }

    /**
     * Manage PDI/INS Parameter Vs Make created on 31/10/2014 by Megha
     */
    public ActionForward managepdi_ins_parameterVsmake(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            mf.setUserId(session.getAttribute("user_id").toString());
            mf.setDealer_code(session.getAttribute("dealerCode").toString());
            String type = request.getParameter("type");
            String data_str = "";
            ActionMessages messages = new ActionMessages();
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addparameterVsmake(mf);
                if (data_str.equalsIgnoreCase("Success")) {
                    messages.add("SUCCESS", new ActionMessage("label.common.partvsmakeSuccess"));
                } else if (data_str.equalsIgnoreCase("Exist")) {
                    messages.add("EXIST", new ActionMessage("label.common.partvsmakeExist"));
                } else {
                    messages.add("FAILURE", new ActionMessage("label.common.partvsmakeFailure"));
                }
                saveErrors(request, messages);
                initpdi_ins_parameterVsmake(mapping, form, request, response);
                return mapping.findForward("pdi_ins_parameterVsmake");

            } else {
                data_str = obj.updateparameterVsmake(mf);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    /**
     * init Rejection Code Master
     */
    public ActionForward initRejectionCodeMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
//            mf.setRejCode(mf.getRejCode()==null?"":mf.getRejCode());
            mf.setRejDesc(request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch").toString());
            ArrayList<masterForm> rejectionCodeList = obj.getRejectionCodeList(mf);
            request.setAttribute("rejectionCodeList", rejectionCodeList);
            request.setAttribute("masterForm", mf);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initRejectionMaster");
    }

    /**
     * manage Rejection Master
     */
    public ActionForward manageRejectionMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        try {
            masterDAO obj = new masterDAO();
            masterForm mf = (masterForm) form;
            mf.setRejCode(mf.getRejCode() == null ? "" : mf.getRejCode());
            mf.setRejDesc(mf.getRejDesc() == null ? "" : mf.getRejDesc());
            mf.setUserId(session.getAttribute("user_id").toString());
            String type = request.getParameter("type").toString();
            String data_str = "";
            //System.out.println("****" + type);
            if (type.equalsIgnoreCase("add")) {

                data_str = obj.addRejectionCode(mf.getRejCode(), mf.getRejDesc(), mf.getUserId());
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initRejectionCodeMaster(mapping, form, request, response);
                return mapping.findForward("initRejectionMaster");
            } else {

                data_str = obj.UpdateRejectionCode(mf.getStatus(), type, mf.getRejCode(), mf.getRejDesc(), mf.getUserId());
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initRejectionMaster");
    }

    public ActionForward initSales(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String forward = "initSales";
        return mapping.findForward(forward);
    }

    public ActionForward initUploadSAPPartMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "uploadSAPPart";
        return mapping.findForward(forward);
    }

    public ActionForward initPricelistExcelMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "pricelistExcel";
        return mapping.findForward(forward);
    }

    public ActionForward initManageUserVsPriceList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String forward = "manageUserVsPriceList";
        try {
            masterDAO obj = new masterDAO();
            LinkedHashSet<LabelValueBean> userTypeList = obj.getExportUserTypeList();
            request.setAttribute("userTypeList", userTypeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward saveOrUpdateManageUserType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String userId;
        String priceCode;
        String mssg;
        ActionMessages messagess = new ActionMessages();
        try {
            HttpSession session = request.getSession();
            String updatedBy = (String) session.getAttribute("user_id");
            userId = request.getParameter("user");
            priceCode = request.getParameter("price");
            masterDAO obj = new masterDAO();
            mssg = obj.saveOrUpdateManageUserType(userId, priceCode, updatedBy);
            if (mssg.equals("Updated")) {
                messagess.add("SUCCESS", new ActionMessage("label.common.manageUserVsPriceUpdated"));
                request.setAttribute("show_message", userId);
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initManageUserVsPriceList");
            } else if (mssg.equals("Created")) {
                messagess.add("SUCCESS", new ActionMessage("label.common.manageUserVsPriceCreated"));
                request.setAttribute("show_message", userId);
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initManageUserVsPriceList");
            } else {
                messagess.add("FAILURE", new ActionMessage("label.common.manageUserVsPriceFailure"));
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initManageUserVsPriceList");
            }
            saveErrors(request, messagess);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("message");
    }

    public ActionForward initManageSellingPercentage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String forward = "manageSellingPercentage";
        try {
            HttpSession session = request.getSession();

            String dealerCode = (String) session.getAttribute("dealerCode");
            masterDAO obj = new masterDAO();
            Float sellingPercentage = obj.getSellingPercentage(dealerCode);
            request.setAttribute("sellingPercentage", sellingPercentage);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward saveOrUpdateManageSellingPercentage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String mssg;
        ActionMessages messagess = new ActionMessages();
        try {
            HttpSession session = request.getSession();
            String updatedBy = (String) session.getAttribute("user_id");
            String dealerCode = (String) session.getAttribute("dealerCode");
            String percent = request.getParameter("percentage");
            Float percentage = new Float(percent);
            masterDAO obj = new masterDAO();
            mssg = obj.saveOrUpdateManageSellingPercentage(dealerCode, percentage, updatedBy);
            if (mssg.equals("Updated")) {
                messagess.add("SUCCESS", new ActionMessage("label.common.manageSellingPercentageUpdated"));
                request.setAttribute("show_message", updatedBy);
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initManageSellingPercentage");
            } else if (mssg.equals("Created")) {
                messagess.add("SUCCESS", new ActionMessage("label.common.manageSellingPercentageCreated"));
                request.setAttribute("show_message", updatedBy);
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initManageSellingPercentage");
            } else {
                messagess.add("FAILURE", new ActionMessage("label.common.manageSellingPercentageFailure"));
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initManageSellingPercentage");
            }
            saveErrors(request, messagess);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("message");
    }

    public ActionForward initManageUserHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "manageUserHierarchy";
        return mapping.findForward(forward);
    }

    public ActionForward manageUserHierarchyExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        String xls_validate_result = "";
        Connection conn = null;
        File xlsfile = null;
        String ext = "";
        String realPath = getServlet().getServletContext().getRealPath("/");
        String dealerCode = session.getAttribute("dealerCode").toString();

        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            masterDAO mDAO = new masterDAO();
            masterForm mForm = (masterForm) form;
            dest_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }
            dest = realPath + "dealer/tempExcels/" + dealerCode;
            xls = mForm.getExcelFile();
            filename = xls.getFileName();
            xlsfile = new File(dest + "/" + filename);
            isFileUploaded = mDAO.uploadXls(filename, dest, xls, realPath);
            ext = filename.substring(filename.indexOf("."));
            if (ext.equals(".xlsx")) {
                xlsfile = mDAO.uploadXlsxImage(filename, dest, realPath);
                isFileUploaded = mDAO.uploadXls(filename.substring(0, filename.length() - 1), dest, xls, realPath);
            }
            if (isFileUploaded) {
                xls_validate_result = mDAO.validatedManageUserHierarchyExcel(xlsfile, conn);
                if (xls_validate_result.equals("success")) {
                    Vector insertionresult = mDAO.insertManageUserHierarchyExcel(xlsfile, conn, dealerCode);

                    if (insertionresult.get(1).equals("Add More")) {
                        request.setAttribute("show_message", insertionresult.get(0));
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "masterAction.do?option=initManageUserHierarchy");
                    } else {
                        request.setAttribute("show_message", insertionresult.get(0));
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("optionLinkURL", "masterAction.do?option=initManageUserHierarchy");
                    }
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");

                } else if (xls_validate_result.equals("readingerr")) {
                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Attach valid Excel to complete the Process successfully.";
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "masterAction.do?option=initManageUserHierarchy");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                } else {
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", xls_validate_result);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "masterAction.do?option=initManageUserHierarchy");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be uploaded.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "masterAction.do?option=initManageUserHierarchy");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "masterAction.do?option=initManageUserHierarchy");//delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        } finally {
        }
    }

    public ActionForward initCustCategory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> CustCategoryList = obj.getCustCategoryList(mf, nameSrch);
            request.setAttribute("CustCategoryList", CustCategoryList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("custCategory");
    }

    public ActionForward manageCustCategory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String name = mf.getName();
            String id = mf.getId();
            String status = request.getParameter("status");
            String type = request.getParameter("type");
            String Desc = request.getParameter("Desc");
            if (type.equalsIgnoreCase("add")) {
                CustomerCategoryMaster customerCategoryMaster = new CustomerCategoryMaster();
                customerCategoryMaster.setCustCategory(mf.getDescription());
                customerCategoryMaster.setIsActive('Y');
                customerCategoryMaster.setCreatedOn(new Date(new java.util.Date().getTime()));
                customerCategoryMaster.setCreatedBy(user_id);
                data_str = obj.addCustCategory(customerCategoryMaster);
                request.setAttribute("successmsg", data_str.split("@@")[1]);
                initCustCategory(mapping, form, request, response);
                return mapping.findForward("custCategory");
            } else {
                name = name.contains("@@") == true ? name.replace("@@", "&") : name;
                CustomerCategoryMaster customerCategoryMaster = new CustomerCategoryMaster();
                customerCategoryMaster.setCustCategory(name);
                customerCategoryMaster.setIsActive('Y');
                customerCategoryMaster.setModifiedOn(new Date(new java.util.Date().getTime()));
                customerCategoryMaster.setModifiedBy(user_id);
                data_str = obj.UpdateCustCategory(customerCategoryMaster, status, id, type, Desc);
                PrintWriter pw = response.getWriter();
                pw.write(data_str);
                pw.close();
            }


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward initDiscountCategory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        HttpSession session = request.getSession();
        String dealerCode = session.getAttribute("dealerCode").toString();
        ActionMessages messages = new ActionMessages();
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;

            if (dealerCode == null || dealerCode.equals("null") || dealerCode.equals("-") || dealerCode.equals("")) {
                messages.add("FAILURE", new ActionMessage("dealerNotExistMsg"));
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "");
//                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "masterAction.do?option=initOptions");
                saveErrors(request, messages);
                return mapping.findForward("message");

            } else {
                ArrayList<masterForm> DiscountCategoryList = obj.getDiscountCategoryList(mf, dealerCode);
                request.setAttribute("DiscountCategoryList", DiscountCategoryList);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("discountCategory");
    }

    public ActionForward manageDiscountCategory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String sno = request.getParameter("sno");
            String dealerCode = session.getAttribute("dealerCode").toString();
            ArrayList ccdArr = new ArrayList();

            CustomerCategoryDiscount ccd = null;
            for (int i = 1; i < Integer.parseInt(sno); i++) {
                ccd = new CustomerCategoryDiscount();
                ccd.setCustcategoryID(Integer.parseInt(request.getParameter("id" + i)));
                ccd.setDealerCode(dealerCode == null ? "" : dealerCode);
                ccd.setDiscountPercentage(request.getParameter("discount" + i));
                ccd.setCreatedOn(new Date(new java.util.Date().getTime()));
                ccd.setCreatedBy(user_id);

                ccdArr.add(ccd);
                data_str = obj.addDiscountCategory(ccdArr, dealerCode);
            }

            request.setAttribute("successmsg", data_str.split("@@")[1]);
            initDiscountCategory(mapping, form, request, response);
            return mapping.findForward("discountCategory");


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward iniTaxChargeOEM(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            String codeSrch = request.getParameter("codeSrch");
            ArrayList<masterForm> taxOemList = obj.getTaxOEMList(mf, nameSrch, codeSrch);
            request.setAttribute("taxOemList", taxOemList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("taxChargeOEMMaster");
    }

    public ActionForward manageTaxChargeOEMMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        String temptaxDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;

            String taxDesc = mf.getTaxDesc();
            if (taxDesc != null) {
                temptaxDesc = taxDesc.replace('`', '&');
            }
            String taxCode = mf.getTaxCode();
            String type = request.getParameter("type");
            int id = 0;
            if (!request.getParameter("primary_id").equals("")) {
                id = Integer.parseInt(request.getParameter("primary_id"));
            }

            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(taxCode, "CmTaxCharg_OEM", "taxChargeCode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addTaxChargeOemMaster(taxCode, temptaxDesc, user_id);
            } else {
                data_str = obj.UpdateTaxChargeOemMaster(status, id, user_id, taxCode, temptaxDesc, type, mf.getTaxChgOemId());
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward initManageChargeBranch(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            String codeSrch = request.getParameter("codeSrch");
            LinkedHashSet<LabelValueBean> taxList = obj.getTaxTypeList();
            request.setAttribute("taxList", taxList);
            ArrayList<masterForm> chargeBranchList = obj.getChargeBranchList(mf, nameSrch, codeSrch);
            request.setAttribute("chargeBranchList", chargeBranchList);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initManageChargeBranch");
    }

    public ActionForward manageChargeBranchMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        String tempchargeDesc = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;

            String type = request.getParameter("type");
            mf.setDealer_code((String) session.getAttribute("user_id"));
            if (request.getParameter("taxType") != null) {
                mf.setTaxChgOemId(Integer.parseInt(request.getParameter("taxType")));
            }
            if (request.getParameter("chargeRate") != null) {
                mf.setChargeRate(Double.parseDouble(request.getParameter("chargeRate")));
            }
            int id = 0;
            if (!request.getParameter("primary_id").equals("")) {
                id = Integer.parseInt(request.getParameter("primary_id"));
                mf.setId(request.getParameter("primary_id"));
            }
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(mf.getChargeCode(), "CmChargeBranch", "chargeCode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addChargeBranchMaster(mf);
            } else {
                data_str = obj.updateChargeBranchMaster(id, type, mf);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward initManageTaxCtgryBranch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "searchManageTaxCtgryBranchOption";
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            String dealerCode = request.getSession().getAttribute("user_id").toString();
            masterForm mForm = (masterForm) form;
            masterDAO obj = new masterDAO(conn);
            request.setAttribute("taxCtgryBranchList", obj.getTaxCtgryBranchList(mForm));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    public ActionForward addNewTaxCategories(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "masterTaxCtgryBranchOption";
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            //mForm.setTaxTypeList(MethodUtility.getCommonLabelValueHiber("CmTaxCharg_OEM", "taxOemId", "taxChargeCode", "taxChargeDesc", "where e.isActive='Y'"));
            request.setAttribute("taxList", new masterDAO(conn).getTaxTypeBranchList());
            saveToken(request);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    public ActionForward getTaxChargeByTaxTypeId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterForm mForm = (masterForm) form;
            mForm.setTaxChgOemId(Integer.parseInt(request.getParameter("taxchargeOemId")));
            String result = new masterDAO(conn).getTaxChargeByTaxTypeId(mForm);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    public ActionForward saveTaxCategories(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "";
        String result = "fail", message = "";
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterForm mForm = (masterForm) form;
            masterDAO obj = new masterDAO(conn);
            request.setAttribute("taxList", obj.getTaxTypeBranchList());
            String dealerCode = request.getSession().getAttribute("user_id").toString();
            String taxctgry_branch_id = String.valueOf(request.getParameter("taxctgry_branch_id"));
            if (taxctgry_branch_id != null && taxctgry_branch_id.length() > 0) {
                if (isTokenValid(request)) {
                    obj.deleteTaxCategoriesDetail(mForm, taxctgry_branch_id);
                    result = obj.saveTaxCategoriesDetail(mForm, dealerCode);
                    if (result.equals("success")) {
                        message = "Category Code " + "'" + mForm.getTaxCtgryCode() + "'" + " updated successfully.";
                        resetToken(request);
                    } else if (result.equals("failure")) {
                        message = "Category Code " + "'" + mForm.getTaxCtgryCode() + "'" + " already Exist, Please Enter other Code.";
                        request.setAttribute("message", message);
                    } else {
                        message = "Error Occured. Please contact administrator.";
                        request.setAttribute("message", message);
                    }
                }
            } else {
                if (isTokenValid(request)) {
                    result = obj.saveTaxCategoriesDetail(mForm, dealerCode);
                    if (result.equals("success")) {
                        message = "Category Code " + "'" + mForm.getTaxCtgryCode() + "'" + " added successfully.";
                        resetToken(request);
                    } else if (result.equals("failure")) {
                        message = "Category Code " + "'" + mForm.getTaxCtgryCode() + "'" + " already Exist, Please Enter other Code.";
                        request.setAttribute("message", message);
                    } else {
                        message = "Error Occured. Please contact administrator.";
                        request.setAttribute("message", message);
                    }
                }
            }
            if (result.equals("success")) {
                forward = "searchManageTaxCtgryBranchOption";
            } else {
                forward = "masterTaxCtgryBranchOption";
            }
            request.setAttribute("taxCtgryBranchList", obj.getTaxCtgryBranchList(mForm));
            request.setAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    public ActionForward editTaxCtgryBranch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "masterTaxCtgryBranchOption";
        Connection conn = null;
        try {
            String userId = request.getSession().getAttribute("user_id").toString();
            Integer taxctgry_branch_id = Integer.parseInt(request.getParameter("taxctgry_branch_id"));
            conn = new dbConnection().getConnection();
            masterForm mForm = (masterForm) form;
            masterDAO obj = new masterDAO(conn);
            obj.getTaxCtgryBranchDetail(mForm, userId, taxctgry_branch_id);
            request.setAttribute("taxList", obj.getTaxTypeBranchList());
            request.setAttribute("cmTaxDetails", mForm.getCmTaxDetails());
            saveToken(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward manageTaxCtgryBranch(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String data_str = "";
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            String userId = request.getSession().getAttribute("user_id").toString();
            Integer taxctgry_branch_id = Integer.parseInt(request.getParameter("taxctgry_branch_id"));
            data_str = obj.UpdateTaxCtgryBranch(request.getParameter("status"), taxctgry_branch_id, userId);
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward initTaxAssignToDealerMasters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initTaxAssignToDealerMasters";
        try {
            String dealerCode = request.getSession().getAttribute("dealerCode").toString();
            masterForm mf = (masterForm) form;
            String dealerSearch = request.getParameter("dealerCode");
            String stateSearch = request.getParameter("stateName");
            LinkedHashSet<LabelValueBean> stateList = MethodUtility.getCommonLabelValueHiber("Dealervslocationcode", "stateName", "stateName", "dealerName", "where dealerCategory='DOMESTIC'");
            mf.setStateNameList(stateList);
            request.setAttribute("taxAssignedList", new masterDAO().getTaxAssignedList(mf, dealerCode, dealerSearch, stateSearch));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward addTaxAssignedToDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "addTaxAssignToDealerMasters";

        try {
            masterForm mf = (masterForm) form;
            mf.setStateNameList(MethodUtility.getCommonLabelValueHiber("Dealervslocationcode", "stateName", "stateName", "dealerName", "where dealerCategory='DOMESTIC'"));
            mf.setCommodityCodeList(new masterDAO().getCommodityCodeList());
            mf.setTaxCategoryList(new masterDAO().getCMTaxCategoryBranchList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward saveTaxAssignedToDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "successPath";
        String result = "failure", message = "";
        try {
            String userId = request.getSession().getAttribute("user_id").toString();
            masterForm masterForm = (masterForm) form;
            result = new masterDAO().saveTaxAssignedToDealer(masterForm, userId);
            if (result.equals("success")) {
                message = "Assign Tax to Dealer Added Successfully.";
            } else {
                message = "Assign Tax to Dealer Added Unsuccessful.";
            }
            request.setAttribute("message", message);
            request.setAttribute("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward deleteTaxAssignedToDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initTaxAssignToDealerMasters";
        //String forward = "success";
        String result = "failure", message = "";
        try {
            String dealerCode = request.getSession().getAttribute("dealerCode").toString();

            masterForm masterForm = (masterForm) form;
            masterForm.setDealerCodeArr(request.getParameter("dealerCode").split(","));
            masterForm.setCommodityCodeArr(request.getParameter("commodityCodeTax").split(","));
            masterForm.setEffectiveDateArr(request.getParameter("effectiveDate").split(","));

            result = new masterDAO().deleteTaxAssignedToDealer(masterForm);
            if (result.equals("success")) {
                message = "Assign Taxes to Dealer Deleted Successfully.";
            } else {
                message = "Assign Taxes to Dealer Deleted Unsuccessful.";
            }
            request.setAttribute("message", message);
            masterForm.setStateNameList(MethodUtility.getCommonLabelValueHiber("Dealervslocationcode", "stateName", "stateName", "dealerName", "where dealerCategory='DOMESTIC'"));
            request.setAttribute("taxAssignedList", new masterDAO().getTaxAssignedList(masterForm, dealerCode, "", ""));
            masterForm.setDealerCode("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward initDownloadPricelistMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "downloadPricelistExcel";
        try {

            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            ArrayList<String> priceList = null;
            if (userFunctionalities.contains("101")) {
                priceList = MethodUtility.getCommonDistinctListHiber("Userpricelist ", "pricelistCode", "pricelistCode", " where  e.userId = '" + user_id + "'");
                if (priceList.size() == 0) {
                    priceList.add("MRP_INR");
                }
            } else if (userFunctionalities.contains("102") || userFunctionalities.contains("103") || userFunctionalities.contains("104")) {
                priceList = MethodUtility.getCommonDistinctListHiber("Pricelistcode ", "pricelistCode", "pricelistCode", "");
            }
            request.setAttribute("priceList", priceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward priceListReoprt(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String forward = "PricelistDownloadExcel";

        try {
            masterDAO obj = new masterDAO();
            HttpSession session = request.getSession();
           // Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            //String user_id = (String) session.getAttribute("user_id");
            String priceList_code = request.getParameter("priceList_code");
            String partNo = request.getParameter("partNo");
            ArrayList<String> priceList = null;
//            if (userFunctionalities.contains("101")) {
//                priceList = MethodUtility.getCommonDistinctListHiber("Userpricelist ", "pricelistCode", "pricelistCode", " where  e.userId = '" + user_id + "'");
//            } else if (userFunctionalities.contains("102") || userFunctionalities.contains("103") || userFunctionalities.contains("104")) {
//                priceList = MethodUtility.getCommonDistinctListHiber("Pricelistcode ", "pricelistCode", "pricelistCode", "");
//            }
            request.setAttribute("priceArrayList", obj.getpriceArrayList(priceList_code,partNo, priceList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward initDownloadPartlistMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("initDownloadPartlist");
    }

    public ActionForward exportToExcelPartlistMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "downloadPartlistExcel";
        try {
            String partNo = request.getParameter("partNo");
            masterDAO obj = new masterDAO();
            request.setAttribute("partArrayList", obj.getpartArrayList(partNo));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    
    public ActionForward initVendorCodeMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<masterForm> vendorCodeList = obj.getVendorCodeList(mf, nameSrch);
            request.setAttribute("vendorCodeList", vendorCodeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("vendorCodeMaster");

    }
    
    public ActionForward manageVendorCodeMaster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            masterDAO obj = new masterDAO(conn);
            masterForm mf = (masterForm) form;
            String vendorCode = mf.getVendorCode();
            String vendorDesc = mf.getVendorDesc();
           
            String type = request.getParameter("type");
            String id = mf.getId();

            String status = request.getParameter("status");
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("check")) {
                data_str = obj.check_in_Db(vendorCode, "MSWvendormaster", "vendorCode", "");

            } else if (type.equalsIgnoreCase("add")) {
                data_str = obj.addVendorCodeMaster(vendorCode, vendorDesc, user_id);
            } else {
                data_str = obj.UpdateVendorCodeMaster(status, id, user_id, vendorCode, vendorDesc, type, mf.getPrimary_id());
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

}
