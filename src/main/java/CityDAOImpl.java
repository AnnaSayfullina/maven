import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class CityDAOImpl implements CityDAO {
    @Override
    public void createCity(City city) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(city);
        List<Employee> employees = city.getEmployees()
                .stream()
                .peek(employee -> employee.setCity(city))
                .peek(entityManager::persist)
                .collect(Collectors.toList());

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public City getCityById(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class, id);
        entityManager.getTransaction().commit();

        entityManager.close();
        return city;
    }

    @Override
    public List<City> getAllCities() {

        EntityManager entityManager = HibernateUtil.getEntityManager();

        entityManager.getTransaction().begin();
        String jpqlQuery = "SELECT e FROM City e";
        TypedQuery<City> query = entityManager.createQuery(jpqlQuery, City.class);
        List<City> cities =query.getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();
        return cities;
    }

    @Override
    public void updateCityById(int cityId,Employee employee ) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        entityManager.getTransaction().begin();

        City city = entityManager.find(City.class, cityId);
        List<Employee> employees = city.getEmployees();
        Employee employeeNew = employees.stream()
                        .filter(s-> s.getId() == employee.getId())
                                .findFirst().get();
        employeeNew.setFirstName(employee.getFirstName());
        employeeNew.setLastName(employee.getLastName());
        employeeNew.setAge(employee.getAge());
        employeeNew.setGender(employee.getGender());
        entityManager.merge(city);

        entityManager.getTransaction().commit();

        entityManager.close();

    }

    @Override
    public void deleteCityById(City city) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        entityManager.getTransaction().begin();

        City cityID = entityManager.find(City.class, city.getCityId());
        entityManager.remove(cityID);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
