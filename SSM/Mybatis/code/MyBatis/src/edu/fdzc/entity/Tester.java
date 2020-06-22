/**
 * 
 */
package edu.fdzc.entity;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 测试类
 * 
 * @author CAI
 *
 */
public class Tester {

	/**
	 * 根据 id 查询人
	 * 
	 * @throws IOException
	 */
	public static void queryPersonById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper.queryPersonById";
		Person person = session.selectOne(statement, 1);

		System.out.println(person);
		session.close();
	}

	/**
	 * 查询所有人
	 * 
	 * @throws IOException
	 */
	public static void queryAllPerson() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "queryAllPersons";
		List<Person> persons = session.selectList(statement);

		System.out.println(persons);
		session.close();
	}

	/**
	 * 根据 id 删除人物
	 * 
	 * @throws IOException
	 */
	public static void addPerson() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "addPerson";
		Person persons = new Person(10, "嬴政", 44);
		int count = session.insert(statement, persons);
		session.commit();// 提交事务
		System.out.println("增加 " + count + " 个学生");
		session.close();
	}

	/**
	 * 根据 id 删除学生
	 * 
	 * @throws IOException
	 */
	public static void deletePersonById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "deletePersonById";
		int count = session.delete(statement, 1);

		session.commit(); //提交事务
		
		System.out.println("删除 " + count + " 个学生");
		session.close();

	}

	/**
	 * 根据 id 修改人物
	 * 
	 * @throws IOException
	 */
	public static void updatePersonById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "updatePersonById";
		Person person = new Person(); // 修改的对象
		person.setId(1); // 指定修改的 id
		
		// 修改的参数
		person.setName("凯文");
		person.setAge(54);
		
		// 执行
		int count = session.update(statement, person);
		
		session.commit(); //提交事务
		
		System.out.println("修改 " + count + " 个学生");
		session.close();
	}

	public static void main(String[] args) throws IOException {
		updatePersonById();
	}
}
