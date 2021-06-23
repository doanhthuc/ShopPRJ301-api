package service;

import model.Ranking;

public interface IRankService {
    Ranking classifyRank(Float totalSpent);
}
