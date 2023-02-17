
package com.mycompany.todoapp.controller;

import com.mycompany.todoapp.model.Projects;
import com.mycompany.todoapp.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {
    
    public void save(Projects projects){
        String sql = "INSERT INTO projects (name, "
                + "description, "
                + "createdAt, "
                + "updatedAt) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement= null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
       
            statement.setString(1, projects.getName());
            statement.setString(2, projects.getDescription());
            statement.setDate(3, new Date(projects.getCreatedAt()
                   .getTime()));
            statement.setDate(4, new Date(projects.getUpdatedAt()
                   .getTime()));
            
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(connection,statement);
        }
        
    }
    
    public void update(Projects projects){
        String sql = "UPDATE projects SET "
                + " name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ? ";
        
        Connection connection = null;
        PreparedStatement statement= null;
        
        try {
           
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, projects.getName());
            statement.setString(2, projects.getDescription());
            statement.setDate(3, new Date(projects.getCreatedAt()
                   .getTime()));
            statement.setDate(4, new Date(projects.getUpdatedAt()
                   .getTime()));
            statement.setInt(5,projects.getId());
            
            statement.execute();
              
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o projeto", ex);
        }finally {
           ConnectionFactory.closeConnection(connection,statement);
        } 
    }
    
    public List<Projects> getAll(){
        String sql = "SELECT * FROM projects";
        
        List<Projects> projects = new ArrayList<Projects>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql); 
           resultSet = statement.executeQuery();
           
            while(resultSet.next()){
                Projects project = new Projects();
                
                project.setId(resultSet.getInt("id"));
                
                project.setName(resultSet
                        .getString("name"));
                project.setDescription(resultSet
                        .getString("description"));
                project.setCreatedAt(resultSet
                        .getDate("createdAt"));
                project.setUpdatedAt(resultSet
                        .getDate("updatedAt"));
                
                projects.add(project);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar o projeto", ex);
        }
        finally{
            ConnectionFactory.closeConnection(connection,statement,resultSet);
        }
        return projects;
        
    }
    
    public void removeById(int projectId){
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
       
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
             
              statement = setInt(1, projectId );
              statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar o project",ex);
        }finally{
            ConnectionFactory.closeConnection(connection,statement);
        }
    }

    private PreparedStatement setInt(int i, int projectId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}
