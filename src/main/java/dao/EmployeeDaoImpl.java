package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import models.Employee;
import util.EmployeeUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	public boolean insert(Employee employee) {
		// TODO Auto-generated method stub
		boolean status=false;
		SqlSession session=EmployeeUtil.getSqlSessionFactory().openSession();
		try {
			session.insert("Employee.insert", employee);
			session.commit();
			status=true;
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			// TODO: handle exception
		return status;
	}

	public Employee getById(int id) {
		// TODO Auto-generated method stub
		Employee employee = null;
		SqlSession session=EmployeeUtil.getSqlSessionFactory().openSession();
		try {
			employee=session.selectOne("Employee.getById",id);
			session.commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		List<Employee> employees = null;
		SqlSession session=EmployeeUtil.getSqlSessionFactory().openSession();
		try {
			employees=session.selectList("Employee.getAll");
			session.commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	public List<Employee> getBySearch(String search) {
		// TODO Auto-generated method stub
		List<Employee> employees = null;
		search="%"+search+"%";
		SqlSession session=EmployeeUtil.getSqlSessionFactory().openSession();
		try {
			employees=session.selectList("Employee.getBySearch",search);
			session.commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	public boolean update(Employee employee) {
		// TODO Auto-generated method stub
		boolean status=false;
		SqlSession session=EmployeeUtil.getSqlSessionFactory().openSession();
		try {
			session.update("Employee.update",employee);
			session.commit();
			status=true;
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			// TODO: handle exception
		return status;
	}

	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		boolean status=false;
		SqlSession session=EmployeeUtil.getSqlSessionFactory().openSession();
		try {
			session.delete("Employee.deleteEmployee",id);
			session.commit();
			status=true;
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			// TODO: handle exception
		return status;
	}

	public boolean deleteAll() {
		// TODO Auto-generated method stub
		boolean status=false;
		SqlSession session=EmployeeUtil.getSqlSessionFactory().openSession();
		try {
			session.delete("Employee.deleteAll");
			session.commit();
			status=true;
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			// TODO: handle exception
		return status;
	}
}
