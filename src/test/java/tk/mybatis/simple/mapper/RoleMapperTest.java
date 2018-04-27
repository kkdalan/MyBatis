package tk.mybatis.simple.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class RoleMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(1L);
			Assert.assertNotNull(role);
			Assert.assertEquals("管理員", role.getRoleName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectById2() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById2(1L);
			Assert.assertNotNull(role);
			Assert.assertEquals("管理員", role.getRoleName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<SysRole> roleList = roleMapper.selectAll();
			//結果不為空
			Assert.assertNotNull(roleList);
			//角色數量大於0個
			Assert.assertTrue(roleList.size() > 0);
			//驗證下畫線字段是否映射成功
			Assert.assertNotNull(roleList.get(0).getRoleName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = new SysRole();
			role.setRoleName("進階用戶");
			role.setEnabled(1);
			role.setCreateBy("1");
			role.setCreateTime(new Date());
			int result = roleMapper.insert(role);
			
			Assert.assertEquals(1, result);
			Assert.assertNull(role.getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = new SysRole();
			role.setRoleName("進階用戶2");
			role.setEnabled(1);
			role.setCreateBy("1");
			role.setCreateTime(new Date());
			int result = roleMapper.insert2(role);
			
			Assert.assertEquals(1, result);
			Assert.assertNotNull(role.getId());
			System.out.println("role.id = " + role.getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert3() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = new SysRole();
			role.setRoleName("進階用戶3");
			role.setEnabled(1);
			role.setCreateBy("1");
			role.setCreateTime(new Date());
			int result = roleMapper.insert3(role);
			
			Assert.assertEquals(1, result);
			Assert.assertNotNull(role.getId());
			System.out.println("role.id = " + role.getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			
			//查詢一個Role對像
			SysRole role = roleMapper.selectById(1L);
			Assert.assertEquals("管理員", role.getRoleName());
			
			//修改Role數據
			role.setRoleName("管理員_test");
			
			//只更新一筆數據
			int result = roleMapper.updateById(role);
			
			//只更新一筆數據
			Assert.assertEquals(1, result);
			
			//檢查修改後的名字
			role = roleMapper.selectById(1L);
			Assert.assertEquals("管理員_test", role.getRoleName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteById() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			
			//查詢一個Role對像
			SysRole role = roleMapper.selectById(1L);
			Assert.assertNotNull(role);
			//確認只刪除一筆
			Assert.assertEquals(1, roleMapper.deleteById(1L));
			//再次查詢Role應該要null
			Assert.assertNull(roleMapper.selectById(1L));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAllRoleAndPrivileges() {
		SqlSession sqlSession = getSqlSession();
//		initMapper(sqlSession);
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
			 
			System.out.println("角色數: " + roleList.size());
			for (SysRole role : roleList) {
				System.out.println(" - 角色名: " + role.getRoleName());
				for (SysPrivilege privilege : role.getPrivilegeList()) {
					System.out.println("    - 權限名: " + privilege.getPrivilegeName());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	private static void initMapper(SqlSession sqlSession) {
		if(!sqlSession.getConfiguration().getMapperRegistry().getMappers().contains(RoleMapper.class)){
			sqlSession.getConfiguration().addMapper(RoleMapper.class);
		}
	}
}
