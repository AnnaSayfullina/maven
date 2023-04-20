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
        employeeDAO.createEmployee(new Employee("Матвей", "Матвеев", "муж", 45, 6));


        /**
         * Получение конкретного объекта Employee по id.
         */
        System.out.println("Введите id сотрудника для поиска в базе");
        int id = scanner.nextInt();
        Employee employee = employeeDAO.getEmployeeById(id);
        System.out.println(employee);

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
        employeeDAO.updateEmployeeById(id);

        /**
         * Удаление конкретного объекта Employee в базе по id.
         */
        System.out.println("Введите id сотрудника для удаления в базе");
        id = scanner.nextInt();
        employeeDAO.deleteEmployeeById(id);

    }
}
