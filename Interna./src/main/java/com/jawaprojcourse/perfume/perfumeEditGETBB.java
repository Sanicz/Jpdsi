package com.jawaprojcourse.perfume;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jawaproj.dao.perfumeDAO;
import com.jawaproj.entities.Perfume;

@Named
@ViewScoped
public class perfumeEditGETBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_PERFUME_LIST = "perfumeList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Perfume perfume = new Perfume();
    private Perfume loaded = null;

    @Inject
    private FacesContext context;

    @EJB
    private perfumeDAO perfumeDAO;

    public Perfume getPerfume() {
        return perfume;
    }

    public void onLoad() throws IOException {
        if (!context.isPostback()) {

            if (!context.isValidationFailed() && perfume.getId() != null) {
                loaded = perfumeDAO.find(perfume.getId());
            }

            if (loaded != null) {
                perfume = loaded;
            } else {
                context.addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Błędne użycie systemu",
                                null
                        )
                );
            }
        }
    }

    public String saveData() {

        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (perfume.getId() == null) {
                perfumeDAO.create(perfume);
            } else {
                perfumeDAO.merge(perfume);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Wystąpił błąd podczas zapisu",
                            null
                    )
            );
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_PERFUME_LIST;
    }
}
