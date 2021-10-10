package club.cduestc.admin.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAccount {
    String student_id;
    String name;
    String password;
    String role;
}
