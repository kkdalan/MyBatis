package tk.mybatis.simple;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.decorators.SerializedCache;
import org.apache.ibatis.cache.decorators.SynchronizedCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.Test;

import tk.mybatis.simple.model.Country;

public class SimpleTest {

	@Test
	public void test() throws SQLException {

		LogFactory.useLog4JLogging();

		// 创建配置文件
		final Configuration config = new Configuration();
		config.setCacheEnabled(true);
		config.setLazyLoadingEnabled(false);
		config.setAggressiveLazyLoading(true);

		// 为了后续说明拦截器，这里添加两个简单例子
		SimpleInterceptor interceptor1 = new SimpleInterceptor("攔截器1");
		SimpleInterceptor interceptor2 = new SimpleInterceptor("攔截器2");
		config.addInterceptor(interceptor1);
		config.addInterceptor(interceptor2);

		// 创建DataSource
		UnpooledDataSource dataSource = new UnpooledDataSource();
		dataSource.setDriver("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?useSSL=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("0000");

		// 创建Executor
		// <transactionManager type="JDBC"/>
		Transaction transaction = new JdbcTransaction(dataSource, null, false);

		//config.newExecutor会将符合条件的拦截器添加到Executor代理链上
		final Executor executor = config.newExecutor(transaction);

		// cache是一个多层代理【装饰模式】的缓存对象，通过一级一级代理使得一个简单的缓存拥有了复杂的功能
		// <cache/>
		final Cache countryCache = new SynchronizedCache(// 同步缓存
				new SerializedCache(// 序列化缓存
						new LoggingCache(// 日志缓存
								new LruCache(// 最少使用缓存
										new PerpetualCache("country_cache")// 持久缓存
								))));
		//类型处理注册器
        //自己写TypeHandler的时候可以参考该注册器中已经存在的大量实现
        final TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        
        //创建静态sqlSource
        //最简单的，相当于从xml或接口注解获取SQL，创建合适的sqlSource对象
        StaticSqlSource sqlSource = new StaticSqlSource(config, "SELECT * FROM country WHERE id = ?");

        //由于上面的SQL有个参数id，这里需要提供ParameterMapping(参数映射)
        List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

        //通过ParameterMapping.Builder创建ParameterMapping
        parameterMappings.add(new ParameterMapping.Builder(config, "id", registry.getTypeHandler(Long.class)).build());
        
        //通过ParameterMap.Builder创建ParameterMap
        ParameterMap.Builder paramBuilder = new ParameterMap.Builder(config, "defaultParameterMap", Country.class, parameterMappings);

        //<resultMap>
//		final ResultMap resultMap = new ResultMap.Builder(config, "defaultResultMap", Country.class,
//				new ArrayList<ResultMapping>() {
//					{
//						add(new ResultMapping.Builder(config, "id", "id", Long.class).build());
//						add(new ResultMapping.Builder(config, "countryname", "countryname", String.class).build());
//						add(new ResultMapping.Builder(config, "countrycode", "countrycode", registry.getTypeHandler(String.class)).build());
//					}
//				}).build();

        //2：不设置具体的映射，只是用类型，相当于只配置resultType="tk.mybatis.simple.model.Country"
        final ResultMap resultMap = new ResultMap.Builder(config, "defaultResultMap", Country.class, new ArrayList<ResultMapping>()).build();

        //创建ms
        MappedStatement.Builder msBuilder = new MappedStatement.Builder(config, "tk.mybatis..simple.SimpleMapper.selectCountry", sqlSource, SqlCommandType.SELECT);
        msBuilder.parameterMap(paramBuilder.build());
        
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        resultMaps.add(resultMap);
        //设置返回值的resultMap和resultType
        msBuilder.resultMaps(resultMaps);
        //设置缓存
        msBuilder.cache(countryCache);
        //创建ms
        MappedStatement ms = msBuilder.build();
        
        //第一种使用executor执行
        List<Country> countries = executor.query(ms, 1L, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        for (Country country : countries) {
            System.out.println(country.getCountryname());
        }
        
        //---------------------------------------------------
        //第二种
        //首先添加ms到config
        config.addMappedStatement(ms);
        //创建sqlSession
        SqlSession sqlSession = new DefaultSqlSession(config, executor, false);
        //查询
        Country country = sqlSession.selectOne("selectCountry", 2L);
        System.out.println(country.getCountryname());
        //关闭
        sqlSession.close();
        
        //第三種
        //使用動態代理
//        MapperProxyFactory<SimpleMapper> mapperProxyFactory = new MapperProxyFactory<SimpleMapper>(SimpleMapper.class);
//        SimpleMapper simpleMapper = mapperProxyFactory.newInstance(sqlSession);
//        Country country2 = simpleMapper.selectCountry(3L);
//        System.out.println(country2.getCountryname());
        
	}
}
