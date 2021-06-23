package dao.impl;

import dao.IRankDAO;
import mapper.RankMapper;
import model.Ranking;

import java.util.List;

public class RankDAO extends AbstractDAO<Ranking> implements IRankDAO {
    @Override
    public List<Ranking> findAll() {
        String sql = "SELECT * FROM ranking ORDER BY total_spent";
        return query(sql, new RankMapper());
    }
}
