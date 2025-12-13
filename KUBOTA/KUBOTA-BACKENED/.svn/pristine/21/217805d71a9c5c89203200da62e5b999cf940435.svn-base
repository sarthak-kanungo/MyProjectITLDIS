package com.i4o.dms.kubota.utils.exception;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class TestClass {

	public static void main(String[] args) {


		 String[] fruits = {"apple","orange","banana","mango","kiwi"};
	        Integer[] numbers = {1,2,3,4,5,6,7,10,11,12,13,14};
	//sorting desc order
	        Arrays.asList(fruits).stream().sorted((s1,s2) -> s2.toString().compareTo(s1.toString())).forEach(System.out::println);
	        
	       

	//replace mango with grapes

	        Arrays.asList(fruits).stream().map(m -> {if(m.equals("mango"))  return "grapes"; else return m;} )
	        .forEach(System.out::println);
	 

	    	//delete banana

	        Arrays.asList(fruits).stream().filter(m -> !m.equals("banana"))
	        .forEach(System.out::println);
	        
//	    List<String> l = Arrays.asList(fruits);
//	    l.remove("banana");
//	    System.out.println(l);
	    
	    
	//missing consecutive two number 1,2,5,6

	        int traceNumber=numbers[0];
	        for(int i=0;i<numbers.length;i++){
	        	if(numbers[i]!=traceNumber){
	                System.out.println(traceNumber);
	                i=i-1;
	            }
	            traceNumber = traceNumber+1;
	        }
	        
	}
}
