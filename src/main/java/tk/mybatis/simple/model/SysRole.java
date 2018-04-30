package tk.mybatis.simple.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import tk.mybatis.simple.type.Enabled;

public class SysRole implements Serializable{
	
	private static final long serialVersionUID = -4932328774224713416L;
	
	private Long id;
	private String roleName;
	private Enabled enabled;
	private String createBy;
	private Date createTime;

	private CreateInfo createInfo;

	private SysUser user;
	private List<SysPrivilege> privilegeList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Enabled getEnabled() {
		return enabled;
	}

	public void setEnabled(Enabled enabled) {
		this.enabled = enabled;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public CreateInfo getCreateInfo() {
		return createInfo;
	}

	public void setCreateInfo(CreateInfo createInfo) {
		this.createInfo = createInfo;
	}

}
