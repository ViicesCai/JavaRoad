/**
 * 
 */
package edu.fdzc.mapper;

import java.util.List;

import edu.fdzc.entity.Communitys;

/**
 * Community 表映射接口
 * 
 * @author CAI
 *
 */
public interface CommunityMapper {
	
	/**
	 * 查询全部社区
	 * 
	 * @return
	 */
	List<Communitys> queryCommunityAndPersonsWithLazyLoad();
}
