package club.cduestc.admin.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class JobEntity {
    int id;
    String name;
    String host;
    String local;
    int min_salary;
    int max_salary;
    String unit;
    Date time;
    String type;
}
