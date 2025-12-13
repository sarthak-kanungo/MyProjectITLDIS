/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EAMG.common;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 *
 * @author nikhil.gothankar
 */
public class commonUtilMethods{

     private Logger logger = Logger.getLogger ( this.getClass () );
     /**
      *
      * @param inputDate
      * @param dateFormat
      * @return
      */
     public String getFormattedDateS ( Date inputDate , SimpleDateFormat dateFormat ) {
          String dateS = "--";
          if ( inputDate != null ) {
               try {
                    dateS = dateFormat.format ( inputDate );

               }
               catch ( Exception ex ) {
                    logger.error ( "Caught in Exception" , ex );
                    dateS = "--";
               }
          }
          return dateS;
     }
     /**
      *
      * @param inputTime
      * @param dateFormat
      * @return
      */
     public String getFormattedTimeS ( Time inputTime , SimpleDateFormat dateFormat ) {
          String timeS = "--";
          if ( inputTime != null ) {
               try {
                    timeS = dateFormat.format ( inputTime );

               }
               catch ( Exception ex ) {
                    logger.error ( "Caught in Exception" , ex );
                    timeS = "--";
               }
          }
          return timeS;
     }
     /**
      *
      * @param str
      * @return
      */
     public static boolean isEntry_NonEmptyNonNull_String ( String str ) {
          boolean ret = false;
          if ( (str == null) || (str.equals ( "" )) || (str.length () == 0) || (str.equals ( "null" )) ) {
               ret = false;
          }
          else {
               ret = true;
          }
          return ret;
     }
     /**
      *
      * @param obj
      * @return
      */
     public String setTrimString ( String obj ) {
          String trimValueS = "";
          if ( obj != null ) {
               try {
                    trimValueS = obj.trim ();

               }
               catch ( Exception ex ) {
                    logger.error ( "Caught in Exception" , ex );

               }

          }
          return trimValueS;
     }
     /**	See if string is a regular expression.
      *
      *	@param	s	The string.
      *
      *	@return		true if the string appears to be a regular expression.
      *
      *	<p>
      *	A string is assumed to be a regular expression if it contains
      *	any of the followint characters:
      *	</p>
      *
      *	<pre>
      *	* + - [ ] . ^ & \ $ ? { } ? =
      *	</pre>
      */
     public static boolean isRegularExpression ( String s ) {
          final char[] regExpChars = new char[]{
               '*' , '+' , '[' , ']' , '.' , '^' , '&' , '\\' , '$' ,
               '?' , '{' , '}' , '=' , '@'
          };

          boolean result = false;

          if ( (s != null) && (s.length () > 0) ) {
               for ( int i = 0 ; i < regExpChars.length ; i++ ) {
                    result = (s.indexOf ( regExpChars[i] ) >= 0);
                    if ( result ) {
                         break;
                    }
               }
          }

          return result;
     }
     /**	Makes possibly null string safe for comparisons.
      *
      *	@param		s	Input string which may be null.
      *
      *	@return			The input string if not null,
      *					other an empty string.
      */
     public static String safeString ( String s ) {
          String string = s;

          if ( string == null ) {
               string = "";
          }

          return string;
     }
     /** Convert string to integer.
      *
      *	@param	strValue			The string to convert.
      *	@param	defaultValue		Default value if conversion error occurs.
      *
      *	@return						The string converted to an integer.
      */
     public static int stringToInt ( String strValue , int defaultValue ) {
          String str = safeString ( trim ( strValue ) );

          if ( str.length () == 0 ) {
               return 0;
          }
          else {
               int result = defaultValue;

               try {
                    result = Integer.parseInt ( strValue );
               }
               catch ( NumberFormatException e ) {
               }

               return result;
          }
     }
     /**	Returns true if two case-insensitive strings are equal.
      *
      *	<p>Nulls are permitted and are equal only to themselves.
      *
      *	@param	s1		String 1.
      *
      *	@param	s2		String 2.
      *
      *	@return			True if string 1 = string 2.
      */
     public static boolean equalsIgnoreCase ( String s1 , String s2 ) {
          if ( s1 == null ) {
               return s2 == null;
          }
          else {
               return s2 == null ? false : s1.equalsIgnoreCase ( s2 );
          }
     }
     /**	Searches a string for a case-insensitive whole word substring match.
      *
      *	@param	str1		The string to be searched.
      *
      *	@param	str2		The substring to search for, converted to lower case.
      *
      *	@param	fromIndex	The index to start the search from.
      *
      *	@return				The index of the match, or -1 if none.
      */
     public static int indexOfIgnoreCaseWholeWord (
         String str1 ,
         String str2 ,
         int fromIndex ) {
          if ( str1 == null ) {
               return -1;
          }

          str1 = str1.toLowerCase ();
          int str1len = str1.length ();
          int str2len = str2.length ();

          while ( fromIndex >= 0 ) {
               fromIndex = str1.indexOf ( str2 , fromIndex );

               if ( fromIndex < 0 ) {
                    return -1;
               }

               if ( (fromIndex > 0)
                   && Character.isLetterOrDigit ( str1.charAt ( fromIndex - 1 ) ) ) {
                    fromIndex++;
                    continue;
               }

               int k = fromIndex + str2len;

               if ( (k < str1len)
                   && Character.isLetterOrDigit ( str1.charAt ( k ) ) ) {
                    fromIndex++;
                    continue;
               }

               return fromIndex;
          }

          return -1;
     }
     /**	Trims a string.
      *
      *	@param	s		The string.
      *
      *	@return			The trimmed string. Leading and trailing white
      *					space characters are removed.  If s is null,
      *					null is returned.
      */
     public static String trim ( String s ) {
          if ( s == null ) {
               return null;
          }

          s = s.trim ();

          return s;
     }
     public static boolean isPhoneNumberValid ( String phoneNumber ) {
          boolean isValid = false;

          //Initialize reg ex for phone number.
          String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
          CharSequence inputStr = phoneNumber;
          Pattern pattern = Pattern.compile ( expression );
          Matcher matcher = pattern.matcher ( inputStr );
          if ( matcher.matches () ) {
               isValid = true;
          }
          return isValid;
     }
     public static boolean isNumeric ( String number ) {
          boolean isValid = false;

          //Initialize reg ex for numeric data.
          String expression = "^[-+]?[0-9]*\\.?[0-9]+$";
          CharSequence inputStr = number;
          Pattern pattern = Pattern.compile ( expression );
          Matcher matcher = pattern.matcher ( inputStr );
          if ( matcher.matches () ) {
               isValid = true;
          }
          return isValid;
     }
     public static java.sql.Date getCurrentSqlDate () {
          java.sql.Date sqlDate = null;
          java.util.Date today = new java.util.Date ();
          sqlDate = new java.sql.Date ( today.getTime () );
          return sqlDate;
     }
     public static boolean isRegularExpression1 ( String s ) {
          final char[] regExpChars = new char[]{
               '"' , '^', };

          boolean result = false;

          if ( (s != null) && (s.length () > 0) ) {
               for ( int i = 0 ; i < regExpChars.length ; i++ ) {
                    result = (s.indexOf ( regExpChars[i] ) >= 0);
                    if ( result ) {
                         break;
                    }
               }
          }

          return result;
     }
     public static boolean isFileExtension ( String s ) {
          final String[] fileExtArr = new String[]{
               "doc","docx", "xls","pdf","xlsx","ppt","txt"
          };
          boolean result = false;
          if ( (s != null) && (s.length () > 0) ) {
               for ( int i = 0 ; i < fileExtArr.length ; i++ ) {
                    result = (s.indexOf ( fileExtArr[i] ) >= 0);
                    if ( result ) {
                         break;
                    }
               }
          }

          return result;
     }
     //Craete BY Avinash Pandey//////

     public static int getGeneratedkeyValue ( PreparedStatement ps) throws Exception {
          int value = 0;
          ResultSet keys = ps.getGeneratedKeys ();
          while ( keys.next () ) {
               value = keys.getInt ( 1 );
          }
          keys.close ();
          return value;
     }
     public static boolean isRegularExpressionPublication ( String s ) {
          final char[] regExpChars = new char[]{
               '*' , '+' ,'[' , ']', '&' , '\\' , '$' ,
                '{' , '}' , '=',' '
          };

          boolean result = false;

          if ( (s != null) && (s.length () > 0) ) {
               for ( int i = 0 ; i < regExpChars.length ; i++ ) {
                    result = (s.indexOf ( regExpChars[i] ) >= 0);
                    if ( result ) {
                         break;
                    }
               }
          }

          return result;
     }
     //Craeted By Amandeep Juneja/////
     public static long getDifferenceOfDates(Date fromDate, Date toDate)
     {
         long diffofDates=0;
         Calendar startDateCal = Calendar.getInstance();
         Calendar endDateCal = Calendar.getInstance();

        startDateCal.setTime(fromDate);
        endDateCal.setTime(toDate);

        long startDatemilis = startDateCal.getTimeInMillis();
        long endDatemilis = endDateCal.getTimeInMillis();

        long diffMills = endDatemilis - startDatemilis;
        diffofDates = diffMills / (24 * 60 * 60 * 1000);

        return diffofDates;
     }

     public static String decodeBase64(String encodedBase64String){
         String originalString = "";
         if(encodedBase64String!= null && encodedBase64String.trim().length()> 0){
            byte[] decodeStringBytes = Base64.decodeBase64(encodedBase64String.getBytes());
            originalString = new String(decodeStringBytes);
            return originalString;
         }   else{
            return ""; 
         }

     }

     public static boolean isBase64String(String base64String){
         return Base64.isBase64(base64String);
     }

     public static String encodeToBase64(String orgString){
         if(orgString!=null && orgString.trim().length()>0){
          byte[] encoded = Base64.encodeBase64(orgString.getBytes());
          return new String(encoded);
         }else{
             return "";
         }
          
     }

     public static String roundUpDecimalToTwoPlace(double value){
         DecimalFormat decimalFormat = new DecimalFormat("#.##");
         String strValue = decimalFormat.format(value);
         String decimalPart = strValue.substring(strValue.indexOf(".")+1);
         if(decimalPart.length() > 1 ){
             return strValue;
         }else{
             return strValue+"0";

         }
     }

}
