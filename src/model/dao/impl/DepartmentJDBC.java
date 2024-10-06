package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJDBC implements DepartmentDao {
    private final Connection connection;

    public DepartmentJDBC( Connection connection ) {
        this.connection = connection;
    }


    @Override
    public void insert( Department department ) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO department " +
                    "(Name) VALUES (?)",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, department.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(resultSet);
            }else throw new DbException("Unexpected Error! No rows affected");
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public void update( Department department ) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2,department.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public void deleteById( Integer id ) {
        PreparedStatement  preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM department WHERE id = ?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public Department findById( Integer id ) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM department WHERE ID = ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Department(resultSet.getInt("Id"),resultSet.getString("Name"));
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List <Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM department ORDER BY NAME");
            resultSet = preparedStatement.executeQuery();
            List<Department> departmentList = new ArrayList <>();
            while (resultSet.next()) {
                departmentList.add(new Department(resultSet.getInt("Id"),resultSet.getString("Name")));
            }
            return departmentList;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }
}
