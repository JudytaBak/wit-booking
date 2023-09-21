package pl.sdacademy.booking.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.util.DatabaseUtil;

import java.time.LocalDateTime;
import java.util.List;

//tu mamy rzeczywistą implementacje metod zdefiniowanych w interfejsie
public class EventRepositoryImpl implements EventRepository {
    private EntityManager entityManager;

    public EventRepositoryImpl() {
        entityManager = DatabaseUtil.getEntityManager();
    }

    @Override
    public List<EventEntity> findEvents() {
        TypedQuery<EventEntity> query = entityManager
                .createQuery("SELECT event FROM EventEntity event", EventEntity.class);
        return query.getResultList();
    }

    @Override
    public void addEvent(EventEntity event) {
        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }

    @Override
    public Long findEventByDate(LocalDateTime date) {
        TypedQuery<Long> query = entityManager
                .createQuery("SELECT * FROM EventEntity where time_from <=:dataParam and time_to>=:dataParam",
                        Long.class);
        query.setParameter("dataParam", date);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return -1L;
        }
    }
}
