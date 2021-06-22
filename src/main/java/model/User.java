package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String fullName;
    private String username;
    private String password;
    private String address;
    private String phone;

    private Integer roleId;
    private Integer rankId;
    private Role role;
    private Ranking rank;
}
