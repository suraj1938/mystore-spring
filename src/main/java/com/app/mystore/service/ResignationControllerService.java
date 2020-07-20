package com.app.mystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mystore.dao.ResignationDao;
import com.app.mystore.dto.Resignation;

@Service("ResignationControllerService")
public class ResignationControllerService {
	@Autowired
	public ResignationDao resignationDao;

	public int apply(Resignation applyresignation, int empid) 
	{
		int row= resignationDao.apply(applyresignation, empid);
		System.out.print(row);
		return row;
	}
	
	public Resignation ResignationDetails(int empid)
	{
		Resignation resign= new Resignation();
		resign=resignationDao.ResignationDetails(empid);
		return resign;
	}
	
	public int DeleteResignation(int empid)
	{
		int result=0;
		result=resignationDao.DeleteResignation(empid) ;
		return result;
		
	}
	public List<Resignation> viewResignation()
	{
		List<Resignation> allresignations=resignationDao.GetAllResignation();	  
		return allresignations;	
	}
	
	public String acceptResignation(Resignation resign,int empid) {
		String result=resignationDao.acceptResignation(resign, empid);
		return result;
	}
	
	public String UpdateResignation(Resignation editresign, int empid) {
		String result =resignationDao.UpdateDetails(editresign, empid);
		return result;
	}
	
	public String rejectResignation(Resignation resign, int empid)
	{
      String result=resignationDao.rejectResignation(resign, empid); 
		return result;
	}

	public String inactiveEmployee(int empid) 
	{
		String result=resignationDao.inactiveEmployee(empid);
		return null;
	};
	

}
