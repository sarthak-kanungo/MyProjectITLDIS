package webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.NamespaceConstants;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sreeja.vijayakumaran
 */
//import java.net.MalformedURLException;
//import java.rmi.RemoteException;
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
//import javax.xml.namespace.QName;
//import javax.xml.rpc.ParameterMode;
//import javax.xml.rpc.ServiceException;
//import javax.xml.rpc.encoding.XMLType;
//public class webservice
//{
//public static void main(String args[]) throws ServiceException, MalformedURLException, RemoteException
//{
//try
//{
//String endPoint ="http://202.164.37.227:7274/sapnetws/SAPBIWS.asmx";
////String endPoint ="http://ip/axis/services";
//Service service = new Service();
//Call call = (Call) service.createCall();
//String mailId = "World";
//call.setTargetEndpointAddress(new java.net.URL(endPoint));
//call.addParameter("mailId", XMLType.XSD_STRING, ParameterMode.IN);
//call.setSOAPActionURI("mywebservice/WelcomeUser");
//call.setOperationName(new QName("mywebservice","WelcomeUser"));
//System.out.println(call.getOperationName());
//call.setReturnType(XMLType.XSD_STRING);
//String ans = (String) call.invoke(new Object []{mailId});
//System.out.println("Result From webService : " + ans);
//
//}
//catch(Exception e)
//{
//e.printStackTrace();
//}
//
//}
//}
//
import dbConnection.dbConnection;

public class webservice {

    public void doWebServiceCall(String partno) throws ServiceException, MalformedURLException {
        try {
            String strEndPoint = dbConnection.SAPWebService;
            String strSoapAction = "http://sonalika.com/GetChassis";
            String strAction = "http://sonalika.com/";
            String strSchemaURL = "http://www.w3.org/2001/XMLSchema";
            String strParamName1 = "chno";
            String strParamName2 = "Hitech@iTL$";
           // String strParamName1 = "ITLHitech";
           // String strParamName2 = "Hitech@iTL$";
            String strParameterVal1 = partno;
            Service service = null;
            String strResult = null;
            //String strParameterVal2 = form_password;

            String strFunctionName = "GetChassis";

            service = new Service();

            Call call = (Call) service.createCall();

            call.setEncodingStyle(NamespaceConstants.NSURI_SOAP_ENCODING);

            call.setTargetEndpointAddress(new URL(strEndPoint));

            call.setOperationName(new QName(strAction, strFunctionName));

            call.setTimeout(90000);

            ////////////////////////////////////////////////////////////////////
            // Set the name of the parameter and the return type
            call.setReturnType(XMLType.XSD_STRING);
            call.addParameter(new QName(strAction, strParamName1), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(strAction, strParamName2), XMLType.XSD_STRING, ParameterMode.IN);

            //
            ////////////////////////////////////////////////////////////////////
            // Catch the result and print it
            // Parameter count is now ONE (1).
            // call.setUseSOAPAction(true);//Not Necessary
            call.setSOAPActionURI(strSoapAction);//Yes Necessary

            strResult = (String) call.invoke(
                    new Object[]{
                        new String(strParameterVal1), //new String(strParameterVal2)
                    });
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
    }
}
