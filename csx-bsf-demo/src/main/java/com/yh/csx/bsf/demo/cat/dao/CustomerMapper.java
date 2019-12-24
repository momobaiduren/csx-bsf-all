package com.yh.csx.bsf.demo.cat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yh.csx.bsf.demo.cat.model.Customer;
import com.yh.csx.bsf.demo.cat.model.CustomerExample;

public interface CustomerMapper {
    int countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExampleWithBLOBs(CustomerExample example);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExampleWithBLOBs(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKeyWithBLOBs(Customer record);

    int updateByPrimaryKey(Customer record);
    
    
    int selectCountByCustomerName(@Param("customerName") String customerName, @Param("customerId") Long customerId);

}