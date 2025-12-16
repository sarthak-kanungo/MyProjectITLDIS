/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dbConnection.dbConnection;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author sreeja.vijayakumaran
 */
public class webserviceAction extends org.apache.struts.action.Action
{

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session = request.getSession();
        String ecatPath = session.getAttribute("ecatPATH").toString();
        String user_id = session.getAttribute("userCode").toString();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String context = request.getContextPath();
        webservice ws = new webservice();
        webserviceform wsf = (webserviceform) form;
        Connection conn = null;
        conn = holder.getConnection();
        String partno = wsf.getPartno();
        String chassisno = wsf.getChassisno();
        String engno = wsf.getEngineno();
        String desc = wsf.getDesc();
        String chNo = "", eng_no = "", part_code = "", partdesc = "", qty = "", bomcd = "", bomdesc = "";
        ArrayList<String> chassis_no = new ArrayList<String>();
        ArrayList<String> engine_no = new ArrayList<String>();
        ArrayList<String> partcode = new ArrayList<String>();
        ArrayList<String> partdescr = new ArrayList<String>();
        ArrayList<String> qtnty = new ArrayList<String>();
        ArrayList<String> bomcde = new ArrayList<String>();
        ArrayList<String> bomdescpt = new ArrayList<String>();
        try
        {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = dbConnection.SAPWebService;
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(partno, chassisno, engno, desc), url);
            System.out.print("\nResponse SOAP Message = ");
            soapResponse.writeTo(System.out);
            SOAPPart sp = soapResponse.getSOAPPart();
            SOAPEnvelope se = sp.getEnvelope();
            SOAPBody sb = se.getBody();
            // System.out.println("\n hiiiiiii " + se.getTextContent());

           // System.out.println("\n hi size of data " + soapResponse.getSOAPBody().getElementsByTagName("CH_NO").getLength());
            for (int i = 0; i < soapResponse.getSOAPBody().getElementsByTagName("CH_NO").getLength(); i++)
            {
                chNo = soapResponse.getSOAPBody().getElementsByTagName("CH_NO").item(i).getFirstChild().getNodeValue();
                eng_no = soapResponse.getSOAPBody().getElementsByTagName("ENGINE").item(i).getFirstChild().getNodeValue();
                part_code = soapResponse.getSOAPBody().getElementsByTagName("PARTCODE").item(i).getFirstChild().getNodeValue();
                partdesc = soapResponse.getSOAPBody().getElementsByTagName("MAKTX").item(i).getFirstChild().getNodeValue();
                qty = soapResponse.getSOAPBody().getElementsByTagName("QTY").item(i).getFirstChild().getNodeValue();
                bomcd = soapResponse.getSOAPBody().getElementsByTagName("P_BOMCD").item(i).getFirstChild().getNodeValue();
                bomdesc = soapResponse.getSOAPBody().getElementsByTagName("BOMDESC").item(i).getFirstChild().getNodeValue();
                chassis_no.add(chNo);
                engine_no.add(eng_no);
                partcode.add(part_code);
                partdescr.add(partdesc);
                qtnty.add(qty);
                bomcde.add(bomcd);
                bomdescpt.add(bomdesc);
            }

            wsf.setChassisno(wsf.getChassisno().toUpperCase());
            wsf.setEngineno(wsf.getEngineno().toUpperCase());
            wsf.setPartno(wsf.getPartno().toUpperCase());
            wsf.setDesc(wsf.getDesc().toUpperCase());
            soapConnection.close();
        }
        catch (Exception e)
        {
            //System.out.println(e);
            e.printStackTrace();
        }       
        request.setAttribute("chassisno", chassis_no);
        request.setAttribute("engineno", engine_no);
        request.setAttribute("partcode", partcode);
        request.setAttribute("partdescr", partdescr);
        request.setAttribute("qty", qtnty);
        request.setAttribute("bomcd", bomcde);
        request.setAttribute("bomdesc", bomdescpt);
        request.setAttribute("step", "2");
        return mapping.findForward("webservicefwd");
    }

    private static SOAPMessage createSOAPRequest(String partno, String chassisno, String engno, String desc) throws Exception
    {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String serverURI = "http://sonalika.com/";
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("xsd", serverURI);
        SOAPHeader SOAPHeader = envelope.getHeader();
        SOAPElement soapheadElem = SOAPHeader.addChildElement("AuthSoapHd", "xsd");
        SOAPElement soapheadElem1 = soapheadElem.addChildElement("strUserName", "xsd");
        soapheadElem1.addTextNode("ITLHitech");
        SOAPElement soapheadElem2 = soapheadElem.addChildElement("strPassword", "xsd");
        soapheadElem2.addTextNode("Hitech@iTL$");
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("GetChassis", "xsd");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("chno", "xsd");
        soapBodyElem1.addTextNode(chassisno.toUpperCase().trim());
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("engno", "xsd");
        soapBodyElem2.addTextNode(engno.toUpperCase().trim());
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("partcode", "xsd");
        soapBodyElem3.addTextNode(partno.toUpperCase().trim());
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("partdesc", "xsd");
        soapBodyElem4.addTextNode(desc.toUpperCase().trim());
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "GetChassis");
        soapMessage.saveChanges();
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);        
        return soapMessage;
    }
}
