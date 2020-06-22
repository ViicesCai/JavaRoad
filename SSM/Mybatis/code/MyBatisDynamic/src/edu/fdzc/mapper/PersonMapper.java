/**
 * 
 */
package edu.fdzc.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fdzc.entity.Community;
import edu.fdzc.entity.Communitys;
import edu.fdzc.entity.Person;
import edu.fdzc.entity.PersonBusiness;

/**
 * Person 表映射接口
 * 
 * @author CAI
 *
 */
public interface PersonMapper {
	/**
	 * 约定
	 *	1.方法名 和 mapper.xml 文件中的标签 id 值相同
	 *  2.方法的输入参数 和 mapper.xml 文件中标签的 parameterType 类型一致
	 *  3.方法的返回值 和  mapper.xml 文件中标签的 resultType 类型一致
	 */

	/**
	 * 通过 id 查找人物
	 * 
	 * @param id
	 * @return 人物对象
	 */
	Person queryPersonById(int id);
	
	/**
	 * 通过 id 查找人物
	 * 
	 * @param id
	 * @return 人物对象
	 */
	Person queryPersonByIdWithConverter(int id);
	
	/**
	 * 查找所有人物
	 * 
	 * @return 人物集合
	 */
	List<Person> queryAllPersons();

	/**
	 * 查找所有人物 并根据字段排序
	 * 
	 * @param column 字段名
	 * @return 人物集合
	 */
	List<Person> queryAllPersonsOrderByColumn(String column);

	/**
	 * 根据年龄或姓名模糊查询
	 * 
	 * @param person
	 * @return 人物集合
	 */
	List<Person> queryPersonsByAgeOrName(Person person);
	
	/**
	 * 根据地址查询
	 * 
	 * @param address
	 * @return 人物集合
	 */
	List<Person> queryPersonsByAddress(Person person);
	
	/**
	 * 根据 HashMap 的值查询人物
	 * 
	 * @param map
	 * @return 人物集合
	 */
	List<Person> queryPersonByAgeOrNameWithHashMap(Map<String, Object> map);
	
	/**
	 * 增加人物
	 * 
	 * @param person 
	 * @return 受影响行数
	 */
	int addPerson(Person person);

	/**
	 * 增加人物使用类型转换器
	 * 
	 * @param person
	 * @return 受影响行数
	 */
	int addPersonWithConverter(Person person);

	/**
	 * 通过 id 删除人物
	 * 
	 * @param id
	 * @return 受影响行数
	 */
	int deletePersonById(int id); 
	
	/**
	 * 修改人物
	 * 
	 * @param person
	 * @return 受影响行数
	 */
	int updatePersonById(Person person);
	
	/**
	 * 查询人物总数
	 * @return
	 */
	int queryPersonCount();
	
	/**
	 * 根据 id 查询的返回结果为 HashMap
	 * 
	 * @return
	 */
	HashMap<String, Object> queryPersonByIdOutByHashMap(int id);
	
	/**
	 * 查询返回结果为 HashMap
	 * 
	 * @return
	 */
	List<HashMap<String, Object>>queryAllPersonsOutByHashMap();
	
	/**
	 * 使用 SQL 标签查询人物
	 * 
	 * @param person
	 * @return
	 */
	List<Person> queryPersonByIdOrAgeWishSQLTag(Person person);
	
	/**
	 * 使用 SQL的遍历标签
	 */
	List<Person> queryPersonsWithIdInCommunity(Community community);
	
	/**
	 * 将多个元素放入数组进行查询
	 * 
	 * @param ids
	 * @return
	 */
	List<Person> queryPersonsWithArray(int[] ids);
	
	/**
	 * 将多个元素放入集合进行查询
	 * 
	 * @param list
	 * @return
	 */
	List<Person> queryPersonsWithList(List<Integer> list);
	
	/**
	 * 将多个元素放入对象集合进行查询
	 * 
	 * @param persons
	 * @return
	 */
	List<Person> queryPersonsWithObjectArray(Person[] persons);
	
	/**
	 * 一对一关联查询
	 * 
	 * @param id
	 * @return
	 */
	PersonBusiness queryPersonByIdWithOneToOne(int id);
	
	/**
	 * 一对多关联查询
	 * 
	 * @param id
	 * @return
	 */
	Communitys queryCommunityAndPersons(int communityid);
	
	/**
	 * 一对一查询：懒加载
	 * 
	 * @param id
	 * @return
	 */
	Person queryPersonByIdWithLazyLoad(int id);
}
