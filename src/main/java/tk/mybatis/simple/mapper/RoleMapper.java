package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.simple.model.SysRole;

public interface RoleMapper {
	
	@Select({"select id, role_name roleName, enabled, create_by createBy, create_time createTime from sys_role where id = #{id}"})
	public SysRole selectById(Long id);
	
	@Select({"select id, role_name, enabled, create_by, create_time from sys_role where id = #{id}"})
	@Results( id="roleResultMap", value={
			@Result(property = "id", column = "id", id = true),
			@Result(property = "roleName", column = "role_name"),
			@Result(property = "enabled", column = "enabled"),
			@Result(property = "createBy", column = "create_by"),
			@Result(property = "createTime", column = "create_time")	
	})
	public SysRole selectById2(Long id);
	
	@Select({"select * from sys_role"})
	@ResultMap("roleResultMap")
	public List<SysRole> selectAll();
	
	@Insert({"insert into sys_role(id, role_name, enabled, create_by, create_time) values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	public int insert(SysRole sysRole);
	
	@Insert({"insert into sys_role(role_name, enabled, create_by, create_time) values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert2(SysRole sysRole);
	
	@Insert({"insert into sys_role(role_name, enabled, create_by, create_time) values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Long.class, before = false)
	public int insert3(SysRole sysRole);
	
	@Update({"update sys_role",
		     "set role_name = #{roleName},",
		     "    enabled = #{enabled},",
		     "    create_by = #{createBy},",
		     "    create_time = #{createTime, jdbcType=TIMESTAMP}",
		     "where id = #{id}"
	})
	public int updateById(SysRole sysRole);
	
	@Delete({"delete from sys_role where id = #{id}"})
	public int deleteById(Long id);
	
	public List<SysRole> selectAllRoleAndPrivileges();

}
