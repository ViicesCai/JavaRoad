/**
 * 
 */
package edu.fdzc.test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


/**
 * 测试类
 * 
 * @author CAI
 *
 */
public class Tester {
	
	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		
		File file = new File("src/generator.xml"); // 配置文件
		List<String> warnings = new ArrayList<String>(); // 保存警告信息
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(file);
		DefaultShellCallback callback = new DefaultShellCallback(true); // 回调函数
		
		// 逆向工程的核心类
		MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
		generator.generate(null);
	}
}
