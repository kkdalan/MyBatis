package tk.mybatis.simple.mapper;

import java.util.List;

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
}
