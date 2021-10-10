package club.cduestc.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select student_id, name, password, role from app_admin where student_id = #{id}")
    UserAccount getAccountByStudentId(String id);
}
