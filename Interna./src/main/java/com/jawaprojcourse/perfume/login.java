package com.jawaprojcourse.perfume;

import com.jawaproj.dao.userDAO;
import com.jawaproj.entities.User;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;

@Named
@SessionScoped
public class login implements Serializable {

    private String username;
    private String password;

    private User loggedUser;

    @Inject
    private userDAO userDAO;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isLoggedIn() {
        return loggedUser != null;
    }


    public String login() {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nie znaleziono użytkownika", null)
            );
            return null;
        }

        if (!user.getPassword().equals(password)) {  
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawne hasło", null)
            );
            return null;
        }

        loggedUser = user; 

        return "/perfumeList.xhtml?faces-redirect=true";
    }

 
    public String logout() {
        loggedUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}