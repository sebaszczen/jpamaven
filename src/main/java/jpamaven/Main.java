package jpamaven;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //<persistence-unit name="myDatabase" z persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Man man = new Man("gienek", 23);
        Man man2=new Man("gienek", 32);

        entityManager.getTransaction().begin();
        entityManager.persist(man);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(man2);
        entityManager.getTransaction().commit();

        TypedQuery<Man> query = entityManager.createQuery("select a from Man a where a.name='gienek' ", Man.class);
        List<Man> manList = query.getResultList();
        for (Man man1 : manList) {
            System.out.println(man1.getName()+" "+man1.getAge());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
