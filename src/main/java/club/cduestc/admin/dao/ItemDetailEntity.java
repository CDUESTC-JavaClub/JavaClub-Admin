package club.cduestc.admin.dao;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ItemDetailEntity {
    int id;
    String name;
    String desc;
    String images;
    String qq;
    double price;
    String user_id;
    Date time;
    String type;
}
