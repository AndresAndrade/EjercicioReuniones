package com.linkedin.learning.dao;

import com.linkedin.learning.dominio.Persona;
import com.linkedin.learning.dominio.Reunion;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReunionDao extends AbstractDao <Reunion> {

    public ReunionDao() {
        setClazz(Reunion.class);
    }

    public Reunion proximaReunion() {
        String qlString = "FROM " + Reunion.class.getName() + " WHERE fecha > now() order by fecha ";
        Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
        return (Reunion) query.getSingleResult();
    }

    public List<Reunion> reunionesManana() {
        String qlString = "FROM " + Reunion.class.getName() + " WHERE fecha between ?1 and ?2";
        Query query = getEntityManager().createQuery(qlString);
        LocalDate manana = LocalDate.now().plus(1, ChronoUnit.DAYS);
        query.setParameter(1, manana.atStartOfDay());
        query.setParameter(2, manana.plus(1, ChronoUnit.DAYS).atStartOfDay());
        return query.getResultList();
    }

    public List<Reunion> reuinonesParticipante(String numEmpl) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Reunion> criteriaQuery = cb.createQuery(Reunion.class);

        Root<Persona> fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.where(cb.equal(fromPersona.get("numeroEmpleado"), numEmpl));

        Join<Persona, Reunion> joinReunion = fromPersona.join("reuniones", JoinType.INNER);

        CriteriaQuery<Reunion> cq = criteriaQuery.multiselect(joinReunion);
        TypedQuery<Reunion> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
}