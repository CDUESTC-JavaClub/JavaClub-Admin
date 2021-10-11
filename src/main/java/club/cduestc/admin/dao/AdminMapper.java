package club.cduestc.admin.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select student_id, name, password, role from app_admin where student_id = #{id}")
    UserAccount getAccountByStudentId(String id);

    @Select("select id, name, host, local, min_salary, max_salary, unit, time, type from app_jobs where publisher = #{id}")
    List<JobEntity> getPublishedJob(String id);

    @Delete("delete from app_jobs where id = #{id} and publisher = #{userId}")
    void deleteJob(int id, String userId);

    @Insert("insert into app_jobs (name, `desc`, host, local, min_salary, max_salary, unit, type, time, contact, publisher) " +
            "value (#{title}, #{desc}, #{host}, #{local}, #{min}, #{max}, #{unit}, #{type}, NOW(), #{contact}, #{userId})")
    void addJob(String title, String host, String local, String desc, String type, int min, int max, String unit, String contact, String userId);

    @Select("select id, name, qq_link, price, user_id, time, type from app_market where status = 0")
    List<ItemEntity> getAllItem();

    @Select("select id, name, `desc`, images, qq_link, price, user_id, time, type from app_market where status = 0 and id = #{id}")
    ItemDetailEntity getDetailItem(int id);

    @Update("update app_market set status = 1 where id = #{id} and status = 0")
    void confirmItem(int id);

    @Delete("delete from app_market where id = #{id} and status = 0")
    void cancelItem(int id);
}
