package com.myproyect.conversor.data;

import com.google.gson.annotations.SerializedName;

public record JsonData( 
	@SerializedName("result") String result,
	@SerializedName("documentation") String documentation,
	@SerializedName ("terms_of_use") String termsOfUse) 
	{}

