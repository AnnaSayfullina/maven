import java.sql.*;
public class Application {
    public static void main(String[] args) {
        final String user = "postgres";
        final String password = "Anna_098!";
        final String url = "jdbc:postgresql://localhost:5432/skyprosql";
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);
        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM employee JOIN city ON employee.city_id = city.city_id WHERE id = 1")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Имя: " + resultSet.getString("first_name"));
                System.out.println("Фамилия: " + resultSet.getString("last_name"));
                System.out.println("Id сотрудника: " + resultSet.getInt("id"));
                System.out.println("Пол: " + resultSet.getString("gender"));
                System.out.println("Город проживания: " + resultSet.getString("city_name"));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }
}
