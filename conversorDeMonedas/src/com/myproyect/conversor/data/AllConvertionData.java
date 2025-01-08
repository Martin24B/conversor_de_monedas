package com.myproyect.conversor.data;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public record AllConvertionData(
	  @SerializedName("base_code")
      String code,

      @SerializedName("conversion_rates")
       Map<String, Double> exchange) 
	{}
