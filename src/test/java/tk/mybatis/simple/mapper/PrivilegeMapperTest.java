package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;

public class PrivilegeMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		initMapper(sqlSession);
		try {
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			SysPrivilege privilege = privilegeMapper.selectById(1L);
			
			Assert.assertNotNull(privilege);
			Assert.assertEquals("用戶管理", privilege.getPrivilegeName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	private static void initMapper(SqlSession sqlSession) {
		if(!sqlSession.getConfiguration().getMapperRegistry().getMappers().contains(PrivilegeMapper.class)){
			sqlSession.getConfiguration().addMapper(PrivilegeMapper.class);
		}
	}
}
