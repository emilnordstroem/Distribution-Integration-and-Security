package lektion20Password;

import java.sql.*;

public class Server {

    public boolean createUser (String name, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=DIS_Lektion20_Password;user=sa;password=131202;")) {

            PreparedStatement insertUserCredentialsStatement = connection.prepareCall("INSERT INTO Users VALUES (?, ?)");

            insertUserCredentialsStatement.setString(1, name);
            insertUserCredentialsStatement.setString(2, password);

            ResultSet resultSet = insertUserCredentialsStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean isLoginValid (String name, String password) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=DIS_Lektion20_Password;user=sa;password=131202;")) {

            CallableStatement isLoginValidStatement = connection.prepareCall("{ call isLoginValid(?, ?) }");

            isLoginValidStatement.setString(1, name);
            isLoginValidStatement.setString(2, password);

            ResultSet resultSet = isLoginValidStatement.executeQuery();

            if (resultSet.next()) {
                // read the returned 1 or 0
                int value = resultSet.getInt("isValid");
                return value == 1;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

}
