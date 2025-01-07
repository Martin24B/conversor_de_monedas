package com.myproyect.conversor.data;

import com.google.gson.annotations.SerializedName;

public record CoinData(
		@SerializedName("supported_codes")
		String[][] codes) 
		{}
