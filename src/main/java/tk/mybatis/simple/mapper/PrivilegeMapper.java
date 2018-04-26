package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.simple.mapper.provider.PrivilegeProvider;
import tk.mybatis.simple.model.SysPrivilege;

public interface PrivilegeMapper {

	@SelectProvider(type = PrivilegeProvider.class, method = "selectById")
	public SysPrivilege selectById(Long id);
}
