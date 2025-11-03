package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private Double kwota;
	private Double termin;
        private Double procentowanie;
	private Double result;

	@Inject
	FacesContext ctx;

	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}
        public Double getTermin() {
		return termin;
	}

	public void setTermin(Double termin) {
		this.termin = termin;
	}

	public Double getProcentowanie() {
		return procentowanie;
	}

	public void setProcentowanie(Double procentowanie) {
		this.procentowanie = procentowanie;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
//			double kwota = Double.parseDouble(this.kwota);
//			double termin = Double.parseDouble(this.termin);
//                        double procentowanie = Double.parseDouble(this.procentowanie);
                        if(kwota<=0||termin<=0||procentowanie<0){
                        ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "parametry nie mogą być 0 lub mniej", null));
                        return false;}
                        
                        double proc_mies = procentowanie/1200;
                        double kwota_ogolem=kwota*(1+proc_mies*termin);
                        result=kwota_ogolem/termin;
                        


			

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
