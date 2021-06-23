package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranking {
    private Integer id;
    private String name;
    private Float discountPercent;
    private Float totalSpent;
}
