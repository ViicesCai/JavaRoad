package edu.fdzc.mapper;

import edu.fdzc.entiry.Personcard;
import edu.fdzc.entiry.PersoncardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersoncardMapper {
    long countByExample(PersoncardExample example);

    int deleteByExample(PersoncardExample example);

    int deleteByPrimaryKey(Integer cardid);

    int insert(Personcard record);

    int insertSelective(Personcard record);

    List<Personcard> selectByExample(PersoncardExample example);

    Personcard selectByPrimaryKey(Integer cardid);

    int updateByExampleSelective(@Param("record") Personcard record, @Param("example") PersoncardExample example);

    int updateByExample(@Param("record") Personcard record, @Param("example") PersoncardExample example);

    int updateByPrimaryKeySelective(Personcard record);

    int updateByPrimaryKey(Personcard record);
}