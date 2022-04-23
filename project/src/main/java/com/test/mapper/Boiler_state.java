package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.entity.Boiler;

@Mapper
public interface Boiler_state {
	@Select("select boiler, state, tem from tbl_state")
	public List<Boiler> selectBoilerList();
	
	@Insert("insert into tbl_state(boiler, state, tem) values (\'${boiler}\', \'${state)\', \'${tem}\')")
	public void InsertBoiler(@Param("boiler") String boiler,
			@Param("state") String state, @Param("tem") int tem);
	
	@Update("update tbl_state set state = \'${state}\' where boiler=\'${boiler}\'")
	public void updateState(@Param("state") String state, @Param("boiler") String boiler);
}
