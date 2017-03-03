package com.burizado.mapper;

import com.burizado.model.Bank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by BurizaDo on 3/3/17.
 */
@Mapper
public interface BankMapper {
    @Select("select * from bank where address like #{address}")
    List<Bank> findBankByAddress(@Param("address") String address);

    @Select("select * from bank where city = #{address}")
    List<Bank> findBankByCity(@Param("address") String address);

}
