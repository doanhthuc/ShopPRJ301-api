package mapper;

import model.Ranking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RankMapper implements RowMapper<Ranking> {

    @Override
    public Ranking mapRow(ResultSet resultSet) {
        try {
            Ranking rank = new Ranking();
            rank.setId(resultSet.getInt("id"));
            rank.setDiscountPercent(resultSet.getFloat("discount_percent"));
            rank.setTotalSpent(resultSet.getFloat("total_spent"));
            return rank;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
