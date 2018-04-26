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
}
