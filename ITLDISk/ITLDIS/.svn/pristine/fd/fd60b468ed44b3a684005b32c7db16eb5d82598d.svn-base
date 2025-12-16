<%@page import="java.util.ArrayList"  contentType="text/html" pageEncoding="UTF-8"%>

<%
		 ArrayList<ArrayList> dataList = (ArrayList) request.getAttribute("dataList");
		 ArrayList<String> assignedList = dataList.get(0);
		 ArrayList<String> unAssinedList = dataList.get(1);
		 out.println("                               <select class=\"links_txt\" name=\"compList\" id=\"compList\" size=\"12\" multiple=\"multiple\" style=\"width:300px\" ondblclick=\"moveDualList(document.form1.compList,  document.form1.attachedlist, false);\"  >");
		 for (int j = 0; j < unAssinedList.size();j++) {
			  out.println("				<option value='" + unAssinedList.get(j) + "'>" + unAssinedList.get(j) + "</option>");
			  out.println();
		 }
		 out.println("$#$");
		 out.println("                                </select>");

		 out.println("                                <select class=\"links_txt\" name=\"attachedlist\" id=\"attachedlist\" size=\"12\" multiple=\"multiple\" style=\"width:300px\" ondblclick=\"moveDualList( document.form1.attachedlist, document.form1.compList, false);\">");

		 for (int j = 0; j < assignedList.size();j++ ) {
			  out.println("				<option value='" + assignedList.get(j) + "'>" + assignedList.get(j) + "</option>");
			  out.println();
		 }

		 out.println("                                </select>");
		 out.println("$#$");

%>