package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private Double kwota;
	private Double oproc;
	private int lat;
	private Double result;

	@Inject
	FacesContext ctx;




	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Double getOproc() {
		return oproc;
	}

	public void setOproc(Double oproc) {
		this.oproc = oproc;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public Double getResult() {
		return result;
	}

	public boolean Calculate() {
		
		
		try {
			Double kwota=getKwota();
			Double oproc=getOproc();
			int lat=getLat();
			
			
			result = kwota + ((kwota*(oproc/100))*lat)*12;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return false;
		}
		
		
	}
	
	// Go to "showresult" if ok
	public String calc() {
		
		if (Calculate()) {
			return "showresult";
		}
		
		return null;
	}



}
