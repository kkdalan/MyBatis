package tk.mybatis.generator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {

	public static void main(String[] args) throws Exception {
		
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;

		//讀取MBG配置文件
		InputStream is = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		is.close();
		
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		//創建MBG
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		//執行生成代碼
		myBatisGenerator.generate(null);
		//輸出警告訊息
		for (String warning : warnings) {
			System.out.println(warning);
		}
	}
}
