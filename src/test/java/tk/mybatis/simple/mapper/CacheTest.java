package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysUser;

public class CacheTest extends BaseMapperTest{
	
	@Test
	public void testL1Cache(){
		SqlSession sqlSession = getSqlSession();
		SysUser user1  = null;
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			user1 = userMapper.selectById(1L);
			user1.setUserName("New Name");
			
			SysUser user2 = userMapper.selectById(1L);
			
			Assert.assertEquals("New Name", user2.getUserName());
			Assert.assertEquals(user1, user2);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		
		System.out.println("---- 開啟新的SqlSession ----");
		sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user2 = userMapper.selectById(1L);
			
			Assert.assertNotEquals("New Name", user2.getUserName());
			Assert.assertNotEquals(user1, user2);
			
			userMapper.deleteById(2L);
			SysUser user3 = userMapper.selectById(1L);
			Assert.assertNotEquals(user2, user3);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
}
