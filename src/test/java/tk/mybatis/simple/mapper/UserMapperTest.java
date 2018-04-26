package tk.mybatis.simple.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(1L);

			Assert.assertNotNull(user);
			Assert.assertEquals("admin", user.getUserName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = userMapper.selectAll();

			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size() > 0);

			for (SysUser user : userList) {
				System.out.printf("%-8d%-8s%8s\n", user.getId(), user.getUserName(), user.getUserEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);

			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);

			for (SysRole role : roleList) {
				System.out.printf("%-4d%-8s%8s\n", role.getId(), role.getRoleName(), role.getUser().getUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			int result = userMapper.insert(user);
			
			Assert.assertEquals(1, result);
			Assert.assertNull(user.getId());

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
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			int result = userMapper.insert2(user);
			
			Assert.assertEquals(1, result);
			Assert.assertNotNull(user.getId());
			System.out.println("user.id = " + user.getId());

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
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			int result = userMapper.insert3(user);
			
			Assert.assertEquals(1, result);
			Assert.assertNotNull(user.getId());
			System.out.println("user.id = " + user.getId());

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
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//查詢一個User對像
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("admin", user.getUserName());
			
			//修改User數據
			user.setUserName("admin_test");
			user.setUserEmail("test@mybatis.tk");
			
			//只更新一筆數據
			int result = userMapper.updateById(user);
			
			//只更新一筆數據
			Assert.assertEquals(1, result);
			
			//檢查修改後的名字
			user = userMapper.selectById(1L);
			Assert.assertEquals("admin_test", user.getUserName());

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
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//查詢一個User對像
			SysUser user = userMapper.selectById(1L);
			Assert.assertNotNull(user);
			//確認只刪除一筆
			Assert.assertEquals(1, userMapper.deleteById(1L));
			//再次查詢User應該要null
			Assert.assertNull(userMapper.selectById(1L));
			
			//查詢一個User2對像
			SysUser user2 = userMapper.selectById(1001L);
			Assert.assertNotNull(user2);
			//確認只刪除一筆
			Assert.assertEquals(1, userMapper.deleteById(user2));
			//再次查詢User2應該要null
			Assert.assertNull(userMapper.selectById(1001L));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L,1);

			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesByUserAndRole() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//查詢一個User對像
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("admin", user.getUserName());
			
			//查詢一個Role對像
			SysRole role = new SysRole();
			role.setId(1L);
			
			//根據User對像和Role對像查詢Role清單
			List<SysRole> roleList = userMapper.selectRolesByUserAndRole(user, role);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByUser() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//只查詢用戶名時
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			
			//只查詢用戶郵箱時
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);

			//當同時查詢用戶名與用戶郵箱時
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("admin@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByIdSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			//創建一個User對像
			SysUser user = new SysUser();
			user.setId(1L);
			user.setUserEmail("test@mybatis.tk");
			int result = userMapper.updateByIdSelective(user);
			//只更新一筆數據
			Assert.assertEquals(1, result);
			
			//檢查修改後的名字
			user = userMapper.selectById(1L);
			Assert.assertEquals("admin", user.getUserName());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testInsert2Selective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			//插入數據庫
			userMapper.insert2Selective(user);
			//查詢插入的這筆數據
			user = userMapper.selectById(user.getId());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sqlSession.commit();
			sqlSession.rollback();
			sqlSession.close();
		}
	}

}
