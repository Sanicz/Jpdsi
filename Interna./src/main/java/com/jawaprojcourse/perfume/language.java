package com.jawaprojcourse.perfume;

import java.io.Serializable;
import java.util.Locale;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class language implements Serializable {

    private static final long serialVersionUID = 1L;

    private Locale locale = Locale.ENGLISH; 

    public void setPolish() {
        locale = new Locale("pl");
    }

    public void setEnglish() {
        locale = Locale.ENGLISH;
    }
   

    public Locale getLocale() {
        return locale;
    }
}