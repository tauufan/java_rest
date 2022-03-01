package com.bjb.api.helper;

public class HelperClass {
	
	public HelperClass() {
		
	}

	public String ConvertFromCentury(String tanggal) {
		String result =null;
		if(tanggal == null || tanggal == "" || tanggal.isEmpty() || tanggal.length() < 1 ) {
			result = tanggal;
		}else {
			if (!tanggal.substring(0, 1).equalsIgnoreCase("1") && tanggal.length() > 6){
                result=tanggal.substring(0, 5);
                String year = tanggal.substring(0, 2);
                String month = tanggal.substring(2, 4);
                String day = tanggal.substring(4, 6);
                result = day+"-"+month+"-19"+year;
            }else if (tanggal.substring(0, 1).equalsIgnoreCase("1")) {
            	result=tanggal.substring(1, 6);
                String year = tanggal.substring(1, 3);
                String month = tanggal.substring(3, 5);
                String day = tanggal.substring(5, 7);
                result = day+"-"+month+"-20"+year;
			}else{
                result=tanggal;
            }
		}
		return result;
	}
}
