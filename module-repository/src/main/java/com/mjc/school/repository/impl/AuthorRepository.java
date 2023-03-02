package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
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
        if (!existById(entity.getId())) {
            return null;
        }
        AuthorModel model = em.getReference(AuthorModel.class, entity.getId());
        model.setName(entity.getName());
        em.flush();
        return model;
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
