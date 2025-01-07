package com.myproyect.conversor.data;

import com.google.gson.annotations.SerializedName;

public record QuotaData(
		@SerializedName ("plan_quota")
		String quotaLimit,
		@SerializedName ("requests_remaining")
		String requestRemaining) 
		{}
