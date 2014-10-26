/**
 * 
 */
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.LinkInfo;

/**
 * LinkInfoDao class
 * 
 * DAO для работы с LinkInfo.
 * 
 * 
 * @author Balukova Elena
 * @created 26 окт. 2014 г.
 * 
 */

public class LinkInfoDao {

    private EntityManager entityManager;

    /**
     * Конструктор по умолчанию
     */
    public LinkInfoDao() {

        EntityManagerFactory emf =
                Persistence
                        .createEntityManagerFactory("shortLinkPersistenceUnit");
        entityManager = emf.createEntityManager();
    }

    /**
     * Получение объекта по идентификатору
     * 
     * @param id
     *            идентификатор
     * @return объект
     */
    public LinkInfo get(Long id) {

        return entityManager.find(LinkInfo.class, id);
    }

    /**
     * Сохранение объекта
     * 
     * @param link
     *            объект
     * @return сохрененный объект
     */
    public LinkInfo save(LinkInfo link) {

        entityManager.getTransaction().begin();

        entityManager.persist(link);
        entityManager.flush();
        entityManager.refresh(link);

        entityManager.getTransaction().commit();
        return link;
    }

    /**
     * Поиск объекта по хэшу
     * 
     * @param hash
     *            хэш
     * @return найденный объект
     */
    public List<LinkInfo> findByHash(String hash) {

        Query query =
                entityManager.createQuery(
                        "SELECT l FROM LinkInfo l WHERE l.urlHash = :hash",
                        LinkInfo.class);
        query.setParameter("hash", hash);

        return query.getResultList();
    }
}
