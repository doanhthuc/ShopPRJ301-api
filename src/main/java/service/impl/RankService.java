package service.impl;

import dao.IRankDAO;
import model.Ranking;
import service.IRankService;

import javax.inject.Inject;
import java.util.List;

public class RankService implements IRankService {
    
    @Inject
    IRankDAO rankDAO;
    
    @Override
    public Ranking classifyRank(Float totalSpent) {
        List<Ranking> ranks = rankDAO.findAll();
        for (int i = ranks.size() - 1; i >= 0; i--) {
            if (totalSpent >= ranks.get(i).getTotalSpent()) {
                return ranks.get(i);
            }
        }
        return ranks.get(0);
    }
}
