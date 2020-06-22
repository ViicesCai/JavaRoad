package edu.fdzc.test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.fdzc.entity.Address;
import edu.fdzc.entity.Community;
import edu.fdzc.entity.Communitys;
import edu.fdzc.entity.Person;
import edu.fdzc.entity.PersonBusiness;
import edu.fdzc.mapper.CommunityMapper;
import edu.fdzc.mapper.PersonMapper;

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

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
				
		// 通过接口方法 执行对应的 SQL 语句
		Person person =  mapper.queryPersonById(2);

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

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryAllPersons();

		System.out.println(persons);
		session.close();
	}

	/**
	 * 根据姓名或年龄 模糊查询
	 * 
	 * @throws IOException
	 */
	public static void queryPersonsByAgeOrName() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		Person person = new Person();
		person.setAge(54);
		person.setName("z");
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonsByAgeOrName(person);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 根据地址查询人物
	 * 
	 * @throws IOException
	 */
	public static void queryPersonsByAddress() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		Address address = new Address();
		address.setHomeAddress("泉州");
		address.setWorkAddress("厦门");
		
		Person person = new Person();
		person.setAddress(address);;
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonsByAddress(person);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 根据 HashMap 查询
	 * 
	 * @throws IOException
	 */
	public static void queryPersonByAgeOrNameWithHashMap() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("age", 54);
		personMap.put("name", "zs");
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonByAgeOrNameWithHashMap(personMap);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 增加人物
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
		
		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		// 通过接口方法 执行相应的 SQL 语句
		int count = mapper.addPerson(new Person(4, "Cai", 22));

		session.commit();// 提交事务
		System.out.println("增加 " + count + " 个学生");
		session.close();
	}

	/**
	 * 增加人物
	 * 
	 * @throws IOException
	 */
	public static void addPersonWithConverter() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();
		
		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		// 通过接口方法 执行相应的 SQL 语句
		int count = mapper.addPersonWithConverter(new Person(4, "Cai", 22, false));

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

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
								
		// 通过接口方法 执行相应的 SQL 语句
		int count = mapper.deletePersonById(1);

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

		Person person = new Person(); // 修改的对象
		person.setId(1); // 指定修改的 id
		
		// 修改的参数
		person.setName("凯文");
		person.setAge(54);
		
		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
								
		// 通过接口方法 执行相应的 SQL 语句
		int count = mapper.updatePersonById(person);
		
		session.commit(); //提交事务
		
		System.out.println("修改 " + count + " 个学生");
		session.close();
	}
	
	/**
	 * 根据 id 查询人
	 * 
	 * @throws IOException
	 */
	public static void queryPersonByIdWithConverter() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
				
		// 通过接口方法 执行对应的 SQL 语句
		Person person =  mapper.queryPersonByIdWithConverter(3);

		System.out.println(person);
		session.close();
	}
	
	/**
	 * 查询所有人按 id 排序
	 * 
	 * @throws IOException
	 */
	public static void queryAllPersonsOrderById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryAllPersonsOrderByColumn("id");

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 查询人物总数
	 * 
	 * @throws IOException
	 */
	public static void queryPersonCount() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		// 通过接口方法 获取 SQL语句
		int count = mapper.queryPersonCount();

		System.out.println("当前人物总数：" + count);
		session.close();
	}
	
	/**
	 * 根据 id 查询结果为  HashMap
	 * 
	 * @throws IOException
	 */
	public static void queryPersonByIdOutByHashMap() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
	
		// 通过接口方法 获取 SQL语句
		HashMap<String, Object> persons = mapper.queryPersonByIdOutByHashMap(1);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 查询返回结果为 HashMap
	 * 
	 * @throws IOException
	 */
	public static void queryAllPersonsOutByHashMap() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
	
		// 通过接口方法 获取 SQL语句
		List<HashMap<String, Object>> persons = mapper.queryAllPersonsOutByHashMap();

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 使用 SQL 标签查询
	 * 
	 * @throws IOException
	 */
	public static void queryPersonByIdOrAgeWishSQLTag() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
	
		Person person = new Person(2, "Cai", 22);
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonByIdOrAgeWishSQLTag(person);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 使用 SQL 的遍历标签
	 * 
	 * @throws IOException
	 */
	public static void queryPersonsWithIdInCommunity() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
	
		Community community = new Community();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		
		community.setIds(ids);
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonsWithIdInCommunity(community);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 使用 SQL 的遍历标签
	 * 
	 * @throws IOException
	 */
	public static void queryPersonsWithArray() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		int[] ids = {1, 2, 3};
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonsWithArray(ids);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 使用 SQL 的遍历标签
	 * 
	 * @throws IOException
	 */
	public static void queryPersonsWithList() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonsWithList(ids);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 使用 SQL 的遍历标签
	 * 
	 * @throws IOException
	 */
	public static void queryPersonsWithObjectArray() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		Person[] ids = {
				new Person(1, "", 1),
				new Person(2, "", 1),
				new Person(3, "", 1),
		};
		
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryPersonsWithObjectArray(ids);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 一对一关联查询
	 * 
	 * @throws IOException
	 */
	public static void queryPersonByIdWithOneToOne() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		// 通过接口方法 获取 SQL语句
		PersonBusiness persons = mapper.queryPersonByIdWithOneToOne(1);

		System.out.println(persons);
		session.close();
	}
	
	/**
	 * 一对多关联查询
	 * 
	 * @throws IOException
	 */
	public static void queryCommunityAndPersons() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		// 通过接口方法 获取 SQL语句
		Communitys communitys = mapper.queryCommunityAndPersons(1);

		System.out.println(communitys);
		session.close();
	}
	
	public static void queryPersonByIdWithLazyLoad() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		// 通过接口方法 获取 SQL语句
		Person person = mapper.queryPersonByIdWithLazyLoad(1);

		System.out.println(person);
		System.out.println(person.getCard());
		session.close();
	}
	
	public static void queryCommunityAndPersonsWithLazyLoad() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		CommunityMapper mapper = session.getMapper(CommunityMapper.class);
		
		// 通过接口方法 获取 SQL语句
		List<Communitys> communitys = mapper.queryCommunityAndPersonsWithLazyLoad();

		System.out.println(communitys);
		session.close();
	}
	
	public static void main(String[] args) throws IOException {
		queryCommunityAndPersonsWithLazyLoad();
	}
}
