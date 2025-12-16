package com.jawaproj.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jawaproj.entities.Perfume;

@Stateless
public class perfumeDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Perfume perfume) {
        em.persist(perfume);
    }

    public Perfume merge(Perfume perfume) {
        return em.merge(perfume);
    }

    public void remove(Perfume perfume) {
        em.remove(em.merge(perfume));
    }

    public Perfume find(Object id) {
        return em.find(Perfume.class, id);
    }

    public List<Perfume> getFullList() {
        try {
            return em.createQuery("select p from Perfume p order by p.brandName asc, p.perfumeName asc", Perfume.class)
                     .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Perfume> getList(Map<String, Object> searchParams) {
        String select = "select p ";
        String from = "from Perfume p ";
        String where = "";
        String orderby = "order by p.brandName asc, p.perfumeName asc";

        String brandName = (String) searchParams.get("brandName");
        String perfumeName = (String) searchParams.get("perfumeName");

        if (brandName != null && !brandName.isEmpty()) {
            where = where.isEmpty() ? "where " : where + "and ";
            where += "p.brandName like :brandName ";
        }

        if (perfumeName != null && !perfumeName.isEmpty()) {
            where = where.isEmpty() ? "where " : where + "and ";
            where += "p.perfumeName like :perfumeName ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (brandName != null && !brandName.isEmpty()) {
            query.setParameter("brandName", brandName + "%");
        }
        if (perfumeName != null && !perfumeName.isEmpty()) {
            query.setParameter("perfumeName", perfumeName + "%");
        }

        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
