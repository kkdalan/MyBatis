package tk.mybatis.simple.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public interface UserMapper {

	public SysUser selectById(Long id);

	public List<SysUser> selectAll();

	public List<SysRole> selectRolesByUserId(Long userId);

	public int insert(SysUser user);

	public int insert2(SysUser user);
	
	public int insert3(SysUser user);

	public int updateById(SysUser user);

	public int deleteById(Long id);

	public int deleteById(SysUser user);

	public List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

	public List<SysRole> selectRolesByUserAndRole(@Param("user") SysUser user, @Param("role") SysRole role);

	public List<SysUser> selectByUser(SysUser user);
	
	public List<SysUser> selectByUserWithOGNLBind(SysUser user);
	
	public List<SysUser> selectByUserWithOGNLMethod(SysUser user);
	
	public List<SysUser> selectByUserWithDatabaseProvider(SysUser user);
	
	public int updateByIdSelective(SysUser user);
	
	public int insert2Selective(SysUser user);
	
	public SysUser selectByIdOrUserName(SysUser user);
	
	public List<SysUser> selectByIdList(List<Long> idList);
	
	public List<SysUser> selectByIdArray(Long[] idArray);
	
	public int insertList(List<SysUser> userList);
	
	public int updateByMap(Map<String, Object> map);
	
	public int updateByMapWithPrint(Map<String, Object> map);
	
	public SysUser selectUserAndRoleById(Long id);
	
	public SysUser selectUserAndRoleById2(Long id);
	
	public SysUser selectUserAndRoleByIdSelect(Long id);
	
	public List<SysUser> selectAllUserAndRoles();

	public SysUser selectAllUserAndRolesSelect(Long id);
	
	public void selectUserById(SysUser user);
	
	public List<SysUser> selectUserPage(Map<String, Object> params);

	public int insertUserAndRoles(@Param("user") SysUser user, @Param("roleIds") String roleIds);

	public int deleteUserById(Long id);
}
