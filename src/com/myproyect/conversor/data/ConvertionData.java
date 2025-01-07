package com.myproyect.conversor.data;

import com.google.gson.annotations.SerializedName;

public record ConvertionData(
		@SerializedName("base_code")
	    String code,
		
	    @SerializedName ("target_code")
	    String code2, 
	        
	    @SerializedName ("conversion_rate")
	    double exchange, 
	        
	    @SerializedName ("conversion_result")
	    double convertionResult) 
		{}
