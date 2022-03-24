package com.linkedin.learning.dao;

import com.linkedin.learning.dominio.Acta;
import com.linkedin.learning.dominio.Reunion;
import org.hibernate.Criteria;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

public class ActaDao extends AbstractDao<Acta>{

    public ActaDao() {

        setClazz(Acta.class);
    }

    public List<Acta> findActasreunionesAntiguasQuery() {
        String qlString = "FROM " + Acta.class.getName() + " a WHERE a.reunion.fecha < :ayer";

        LocalDateTime ayer = LocalDateTime.now().minusDays(1);

        Query query = getEntityManager().createQuery(qlString);
        query.setParameter("ayer", ayer);
        return query.getResultList();
    }

    public List<Acta> findActasReunionesAntiguasCriteria() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery< Acta> criteriaQuery = cb.createQuery(Acta.class);
        Root<Acta> rootActa = criteriaQuery.from(Acta.class);
        Join<Acta, Reunion> joinReunion = rootActa.join("reunion", JoinType.INNER);

        Predicate fechaAyer = cb.lessThan(joinReunion.get("fecha"), LocalDateTime.now().minusDays(1));
        criteriaQuery.where(fechaAyer);

        Query query = getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }
}
