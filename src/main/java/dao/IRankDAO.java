package dao;

import model.Ranking;

import java.util.List;

public interface IRankDAO extends GenericDAO<Ranking> {
    List<Ranking> findAll();
}
