package club.cduestc.admin.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ItemEntity {
    int id;
    String name;
    String qq;
    double price;
    String user_id;
    Date time;
    String type;
}
