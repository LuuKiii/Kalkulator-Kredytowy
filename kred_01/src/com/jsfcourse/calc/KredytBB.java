package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String kwota;
	private String oproc;
	private String lat;
	private Double result;

	@Inject
	FacesContext ctx;




	public String getKwota() {
		return kwota;
	}

	public void setKwota(String kwota) {
		this.kwota = kwota;
	}

	public String getOproc() {
		return oproc;
	}

	public void setOproc(String oproc) {
		this.oproc = oproc;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Double getResult() {
		return result;
	}

	public boolean Calculate() {
		
		
		try {
			double kwota = Double.parseDouble(this.kwota);
			double oproc = Double.parseDouble(this.oproc);
			double lat = Double.parseDouble(this.lat);
			
			if(kwota<0 || oproc<0 || lat<0)
			{
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podane wartoœci nie mog¹ byæ mniejsze od 0", null));
				return false;
			}
			
			if( oproc>100 )
			{
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Oprocentowanie nie mo¿e byæ wiêksze ni¿ 100%", null));
				return false;
			}
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
