package dao;

import java.util.List;

import models.Employee;

public interface EmployeeDao {

	public boolean insert(Employee employee);

	public Employee getById(int id);

	public List<Employee> getAll();

	public boolean update(Employee employee);

	public boolean deleteEmployee(int id);

	public boolean deleteAll();
}
