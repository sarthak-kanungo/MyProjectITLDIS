Option Explicit

dim command_line_args

set command_line_args = wscript.Arguments
dim file_name 
file_name = command_line_args(0)
'MsgBox(file_name)
Xls2HTML file_name

Sub Xls2HTML(myFile)
' This subroutine opens a Excel Workbook,
' then saves it as HTML, and closes Excel.
' If the HTML file exists, it is overwritten.
' If Excel was already active, the subroutine
' will leave the other Workbook(s) alone and
' close only its "own" Workbook.
'
' Written by Rob van der Woude
' http://www.robvanderwoude.com
    ' Standard housekeeping
    Dim objXls, objFile, objFSO, objExcel, strFile, strHTML

    
    Const xlHtml = 44 '&H2c
    
	on error Resume Next
	
    ' Create a File System object
    Set objFSO = CreateObject("Scripting.FileSystemObject")

    ' Create a Excel object
    Set objExcel = CreateObject("Excel.Application")
    
    With objExcel
        ' True: make Excel visible; False: invisible
        .Visible = True
		.DisplayAlerts = False
        ' Check if the Excel Workbook exists
        If objFSO.FileExists(myFile) Then
            Set objFile = objFSO.GetFile(myFile)
            strFile = objFile.Path
        Else
            WScript.Echo "FILE OPEN ERROR: The file does not exist" & vbCrLf
            ' Close Excel
            .Quit
            Exit Sub
        End If
        ' Build the fully qualified HTML file name
        strHTML = objFSO.BuildPath(objFile.ParentFolder, _
                  objFSO.GetBaseName(objFile) & ".html")

        ' Open the Excel Workbook
        .Workbooks.Open strFile

       
		' Make the opened file the active Workbook
        Set objXls = .ActiveWorkbook
        
       
	'on error Resume next
	'save again to update links  
        objXls.Save    

        ' Save as HTML
        objXls.SaveAs strHTML, xlHtml
	
	if  Err.Number <> 0  then  
	 WScript.Echo "Error : " & Err.Description & vbCrLf
	End if

       
		' Close the active Workbook
        objXls.Close

        ' Close Excel
        .Quit
    End With
End Sub