/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EAMG.Other.Action;

/**
 *
 * @author satyaprakash.verma
 */
public class NumberToRoman
{
 final static Roman[] table = {
new Roman(1000, "M"),
new Roman( 900, "CM"),
new Roman( 500, "D"),
new Roman( 400, "CD"),
new Roman( 100, "C"),
new Roman( 90, "XC"),
new Roman( 50, "L"),
new Roman( 40, "XL"),
new Roman( 10, "X"),
new Roman( 9, "IX"),
new Roman( 5, "V"),
new Roman( 4, "IV"),
new Roman( 1, "I")
};
  public static String getRoman(int n)
  {
      if (n >= 5000 || n < 1)
  {
    //System.out.println("Numbers must be in range 1-4999");
  }
    StringBuffer buffer = new StringBuffer(10);
    for (Roman rvalue : table)
    {
     while (n >= rvalue.number) {
     n -= rvalue.number;
     buffer.append(rvalue.romanNumber);
    }
    }
    return buffer.toString();
    }
    }

class Roman
{
  int number;
  String romanNumber;
  Roman(int dec, String rom) {
   this.number = dec;
   this.romanNumber = rom;
}
}

