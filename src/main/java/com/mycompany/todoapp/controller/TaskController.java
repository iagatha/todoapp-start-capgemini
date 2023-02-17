
package com.mycompany.todoapp.controller;

import com.mycompany.todoapp.model.Tasks;
import com.mycompany.todoapp.util.ConnectionFactory;
import com.mysql.cj.protocol.x.MessageConstants;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TaskController {
    
    public void save(Tasks tasks){
        String sql = "INSERT INTO tasks (id,"
                + "idProjects, " 
                + "name, "
                + "description, "
                + "completed, "
                + " notes, "
                + "deadline, "
                + "createdAt, "
                + "updatedAt) VALUES (?, ?, ?, ? ,? ,? ,? , ?, ?)";
        Connection connection = null;
        PreparedStatement statement= null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
           
            statement.setInt(1, tasks.getId());
            statement.setInt(2,tasks.getIdProjects());
            statement.setString(3,tasks.getName());
            statement.setString(4, tasks.getDescription());
            statement.setBoolean(5,tasks.isCompleted());
            statement.setString(6,tasks.getNotes());
            statement.setDate(7,new Date(tasks.getDeadline().getTime()));
            statement.setDate(8, new Date( tasks.getCreatedAt().getTime()));
            statement.setDate(9, new Date( tasks.getUpdatedAt().getTime()));
          
           statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa" +
                    ex.getMessage(), ex);
        }finally{
            ConnectionFactory.closeConnection(connection,statement);
         
        }
    }
    
    public void update(Tasks tasks){
       
        String sql = "UPDATE tasks SET "
                + "idProjects = ?,"
                + " name = ?, "
                + "description = ?, "
                + "completed = ?, "
                + " notes = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ? ";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        
        try {
            
           //estabelecendo a conexãocom o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = connection.prepareStatement(sql);
            
            //setando os valores do statement
           
            statement.setInt(1, tasks.getIdProjects());
            statement.setString(2, tasks.getName());
            statement.setString(3, tasks.getDescription());
            statement.setBoolean(4, tasks.isCompleted());
            statement.setString(5, tasks.getNotes());
            statement.setDate(6, new Date(tasks.getDeadline()
                    .getTime()));
            statement.setDate(7, new Date(tasks.getCreatedAt()
                   .getTime()));
            statement.setDate(8, new Date(tasks.getUpdatedAt()
                   .getTime()));
             statement.setInt (9,tasks.getId());
           
           //executando a query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa" +
                    ex.getMessage(), ex);
        }
    }
    
    public void removeById(int taskId){
        String sql = "DELETE FROM tasks WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //criando conexão com o banco
            connection = ConnectionFactory.getConnection();
            //preparando a query
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            
            //executando a query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" +
                    ex.getMessage(), ex);
        }finally{
            ConnectionFactory.closeConnection(connection,statement);
        }
    }
    
    public List<Tasks> getAll(int idProjects){
        String sql = "SELECT * FROM tasks WHERE idProjects = ?";
        
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
    // A lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Tasks> tasks = new ArrayList<Tasks>();
        
        try {
            //criando a conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            //setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProjects);
            
            //valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            // enquanto houverem valores a serem percorridos
            while(resultSet.next()){
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setIdProjects(resultSet
                        .getInt("idProjects"));
                task.setName(resultSet
                        .getString("name"));
                task.setDescription(resultSet
                        .getString("description"));
                task.setNotes(resultSet
                        .getString("notes"));
                task.setCompleted(resultSet
                        .getBoolean("completed"));
                task.setDeadline(resultSet
                        .getDate("deadline"));
                task.setCreatedAt(resultSet
                        .getDate("createdAt"));
                task.setUpdatedAt(resultSet
                        .getDate("updatedAt"));
                
                tasks.add(task);
            }
            
             
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao inserir a tarefa" +
                    ex.getMessage(), ex);
        }finally{
             ConnectionFactory.closeConnection(connection,statement,resultSet);
        }
        // Lista de tarefa que foi criada
        return tasks;
    }
   
}
