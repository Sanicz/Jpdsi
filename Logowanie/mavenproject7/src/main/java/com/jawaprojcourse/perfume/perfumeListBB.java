package com.jawaprojcourse.perfume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;

import com.jawaproj.dao.perfumeDAO;
import com.jawaproj.entities.Perfume;

@Named
@RequestScoped
public class perfumeListBB {

    private static final String PAGE_PERFUME_EDIT = "perfumeEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String brandName;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    perfumeDAO perfumeDAO;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Perfume> getFullList() {
        return perfumeDAO.getFullList();
    }

    public List<Perfume> getList() {
        Map<String, Object> searchParams = new HashMap<>();

        if (brandName != null && !brandName.isEmpty()) {
            searchParams.put("brandName", brandName);
        }

        return perfumeDAO.getList(searchParams);
    }

    public String newPerfume() {
        Perfume perfume = new Perfume();

        flash.put("perfume", perfume);

        return PAGE_PERFUME_EDIT;
    }

    public String editPerfume(Perfume perfume) {
        flash.put("perfume", perfume);

        return PAGE_PERFUME_EDIT;
    }

    public String deletePerfume(Perfume perfume) {
        perfumeDAO.remove(perfume);
        return PAGE_STAY_AT_THE_SAME;
    }
}
