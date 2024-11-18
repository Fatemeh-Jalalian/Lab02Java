package dao;

import model.IndyWinner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndyWinnerDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/IndyWinners";
    private static final String DB_USER = "test";
    private static final String DB_PASS = "Test9900";

    public List<IndyWinner> getWinners(int offset, int limit) {
        List<IndyWinner> winners = new ArrayList<>();
        String query = "SELECT * FROM IndyWinners ORDER BY year DESC LIMIT ? OFFSET ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, limit);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                winners.add(new IndyWinner(
                        resultSet.getInt("year"),
                        resultSet.getString("driver"),
                        resultSet.getDouble("averageSpeed"),
                        resultSet.getString("country")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return winners;
    }
}
