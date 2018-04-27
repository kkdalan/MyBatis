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

	public int insert(SysUser sysUser);

	public int insert2(SysUser sysUser);
	
	public int insert3(SysUser sysUser);

	public int updateById(SysUser sysUser);

	public int deleteById(Long id);

	public int deleteById(SysUser sysUser);

	public List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

	public List<SysRole> selectRolesByUserAndRole(@Param("user") SysUser user, @Param("role") SysRole role);

	public List<SysUser> selectByUser(SysUser sysUser);
	
	public List<SysUser> selectByUserWithOGNLBind(SysUser sysUser);
	
	public List<SysUser> selectByUserWithOGNLMethod(SysUser sysUser);
	
	public List<SysUser> selectByUserWithDatabaseProvider(SysUser sysUser);
	
	public int updateByIdSelective(SysUser sysUser);
	
	public int insert2Selective(SysUser sysUser);
	
	public SysUser selectByIdOrUserName(SysUser sysUser);
	
	public List<SysUser> selectByIdList(List<Long> idList);
	
	public List<SysUser> selectByIdArray(Long[] idArray);
	
	public int insertList(List<SysUser> userList);
	
	public int updateByMap(Map<String, Object> map);
	
	public int updateByMapWithPrint(Map<String, Object> map);
	
	public SysUser selectUserAndRoleById(Long id);
	
	public SysUser selectUserAndRoleById2(Long id);
	
	public SysUser selectUserAndRoleByIdSelect(Long id);
	
}
