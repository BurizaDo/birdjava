package com.burizado.mapper;

import com.burizado.model.Bank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by BurizaDo on 3/3/17.
 */
@Mapper
public interface BankXMLMapper {
    List<Bank> getBank(String address);
}
