import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAOImpl implements EmployeeDAO {
    final String user = "postgres";
    final String password = "Anna_098!";
    final String url = "jdbc:postgresql://localhost:5432/skyprosql";


    @Override
    public void createEmployee(Employee employee) {
    String sql = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity_id());

            statement.executeUpdate();
            System.out.println("Сотрудник занесен в базу");
        } catch (
                SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Employee employee = null;
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {
                statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int city_id = resultSet.getInt("city_id");

                employee = new Employee(id, first_name, last_name, gender, age, city_id);
            }

        } catch (
                SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int city_id = resultSet.getInt("city_id");

                employeeList.add(new Employee(id, first_name, last_name, gender, age, city_id));
            }

        } catch (
                SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void updateEmployeeById(int id) {
        String sql = "UPDATE employee SET first_name = ?, last_name =?, gender = ?, age = ?, city_id = ? WHERE id = ?";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {
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
            int city_id = scanner.nextInt();

                    statement.setString(1, first_name);
                    statement.setString(2, last_name);
                    statement.setString(3, gender);
                    statement.setInt(4, age);
                    statement.setInt(5, city_id);
                    statement.setInt(6, id);
            statement.executeUpdate();
            System.out.println("Изменение внесены");
        } catch (
                SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Сотрудник удален");

        } catch (
                SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }
}
