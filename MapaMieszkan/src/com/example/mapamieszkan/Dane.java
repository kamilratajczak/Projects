package com.example.mapamieszkan;

import com.google.gson.annotations.SerializedName;

public class Dane{

	@SerializedName("imie")
	public String imie;
	
	@SerializedName("nazwisko")
	public String nazwisko;
	
	@SerializedName("numer_telefonu")
	public String numer_telefonu;
	
	@SerializedName("email")
	public String email;
}