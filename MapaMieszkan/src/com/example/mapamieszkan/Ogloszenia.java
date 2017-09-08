package com.example.mapamieszkan;

import com.google.gson.annotations.SerializedName;

public class Ogloszenia{
	
	@SerializedName("id_ogloszenia")
	public int id_ogloszenia;
	
	@SerializedName("login")
	public String login;

	@SerializedName("x")
	public Double x;
	
	@SerializedName("y")
	public Double y;
	
	@SerializedName("adres")
	public String adres;
	
	@SerializedName("miasto")
	public String miasto;
	
	@SerializedName("ulica")
	public String ulica;
	
	@SerializedName("numer")
	public String numer;
	
	@SerializedName("ilosc_pokoi")
	public String ilosc_pokoi;
	
	@SerializedName("metraz")
	public String metraz;
	
	@SerializedName("cena")
	public String cena;
	
	@SerializedName("url")
	public String url;
	
	@SerializedName("data")
	public String data;
}