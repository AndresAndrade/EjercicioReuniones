package com.linkedin.learning.dao;

import com.linkedin.learning.dominio.Persona;

public class PersonaDao extends AbstractDao<Persona>{
    public PersonaDao() {
        setClazz(Persona.class);
    }
}
