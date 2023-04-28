import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Scanner scanner = new Scanner(System.in);

        /**
        * Создание (добавление) сущности Employee в таблицу.
         */
        System.out.println("Для добавления сотрудника введите следующие данные:");
        Employee employee = enterInformationWithScanner();
        employeeDAO.createEmployee(employee);

        /**
         * Получение конкретного объекта Employee по id.
         */
        System.out.println("Введите id сотрудника для поиска в базе");
        int id = scanner.nextInt();
        Employee employee2 = employeeDAO.getEmployeeById(id);
        if(employee2 == null){
            System.out.println("сотрудник в базе не найден");
        } else {
            System.out.println(employee2);
        }

        /**Получение списка всех объектов Employee из базы.
         */
        System.out.println("Все сотрудники");
        List<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee emp: employees){
            System.out.println(emp);
        }

        /**
         * Изменение конкретного объекта Employee в базе по id.
         */
        System.out.println("Введите id сотрудника для изменения в базе");
        id = scanner.nextInt();
        Employee employee5 = employeeDAO.getEmployeeById(id);
        Employee employee1 = enterInformationWithScanner();
        employee1.setId(employee5.getId());
        employeeDAO.updateEmployeeById(employee1);


        /**
         * Удаление конкретного объекта Employee в базе по id.
         */
        System.out.println("Введите id сотрудника для удаления в базе");
        id = scanner.nextInt();
        Employee employee4 = employeeDAO.getEmployeeById(id);
        employeeDAO.deleteEmployeeById(employee4);

        HibernateUtil.closeEntityManagerFactory();


    }
    public static Employee enterInformationWithScanner(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String first_name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String last_name = scanner.nextLine();
        System.out.println("Введите пол");
        String gender = scanner.nextLine();
        System.out.println("Введите возраст");
        int age = scanner.nextInt();
        System.out.println("Введите id города");
        int cityId = scanner.nextInt();
        Employee employee = new Employee(first_name, last_name, gender,age,cityId);
        return employee;
    }
}
