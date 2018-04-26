package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysRole;

public interface RoleMapper {
	
	public SysRole selectById(Long id);
	
}
