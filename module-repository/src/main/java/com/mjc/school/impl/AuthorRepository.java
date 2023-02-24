package com.mjc.school.impl;

import com.mjc.school.BaseRepository;
import com.mjc.school.model.AuthorModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<AuthorModel> readAll() {
        return em.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        AuthorModel authorModel = em.find(AuthorModel.class, id);
        return Optional.ofNullable(authorModel);
    }

    @Override
    @Transactional
    public AuthorModel create(AuthorModel entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public AuthorModel update(AuthorModel entity) {
        if (existById(entity.getId())) {
            em.merge(entity);
            return entity;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return em.createQuery("delete from AuthorModel a where a.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public boolean existById(Long id) {
        return em.find(AuthorModel.class, id) != null;
    }
}
