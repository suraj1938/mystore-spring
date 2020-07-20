package com.app.mystore.dao;
import java.util.List; 

import com.app.mystore.dto.Resignation;

public interface ResignationDao {
	
	
	public int apply(Resignation resign, int empid);
	public Resignation ResignationDetails(int empid);
	public int DeleteResignation(int empid);
	public List<Resignation> GetAllResignation();
	public String UpdateDetails(Resignation resign, int empid);
	
	public String rejectResignation(Resignation resign, int empid);
	public String inactiveEmployee(int empid);
	public String acceptResignation(Resignation resign, int empid);
	
}
