package nora;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB{
    
    private final String url=("jdbc:mysql://localhost:3306/nora");
    private final String dbUsername = "root";
    private final String dbPassword= "";
    
    private Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection (url,dbUsername,dbPassword);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new SQLException("Cannot Find MySQL Drive");
        }
    }
    
    private int getNumberOfAnswersForQuestion(int questionId){
        return getAllAnswersForQuestion(questionId).size();
    }
    
    public List<Answer> getAllAnswersForQuestion(int questionId){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ans WHERE ask_id = " + questionId);
            List<Answer> answers= new ArrayList<>();
            while(rs.next()){
                Answer a = new Answer(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("ask_id"), rs.getString("answer"), rs.getTimestamp("created_at"));
                answers.add(a);
            }
            conn.close();
            return answers;
        }catch(SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }   
    }
    
    public Question getQuestionForAnswer(Answer answer){
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ask where id = " + answer.getQuestionId());
            
            rs.next();
            Question q = new Question(rs.getInt("id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("question"),rs.getTimestamp("created_at"));
            conn.close();
            return q;            
            
        }catch(SQLException e){
            return null;
        }
    }
   
    
    public String getUsername(int userId){
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users where id = " + userId);
            rs.next();
            String username = rs.getString("name");
            conn.close();
            return username;
        }catch(SQLException e){
            e.printStackTrace();
            return "Not Found";
        }
    }
    
    public List<Answer> getAllMyAnswers(){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ans WHERE user_id = " + LoggedInUser.userId);
            List<Answer> answers= new ArrayList<>();
            while(rs.next()){
                Answer a = new Answer(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("ask_id"), rs.getString("answer"), rs.getTimestamp("created_at"));
                answers.add(a);
            }
            conn.close();
            return answers;
        }catch(SQLException e){
            e.printStackTrace();
            return new ArrayList<>();
        }        
    }
    
    public List<Question> getAllMyQuestions(){
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ask where user_id = " + LoggedInUser.userId);
            List<Question> questions = new ArrayList<>();
            while(rs.next()){
                Question q = new Question(rs.getInt("id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("question"),rs.getTimestamp("created_at"));
                
                q.setAnswers(getAllAnswersForQuestion(q.getId()));
                questions.add(q);
            }
            conn.close();
            return questions;
        }catch(SQLException e){
            return new ArrayList<>();
        }
    }
    
    public List<Tutorial> getAllMyTutorials(){
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tot where user_id = " + LoggedInUser.userId);
            List<Tutorial> tutorials = new ArrayList<>();
            while(rs.next()){
                Tutorial t = new Tutorial();
                t.setId(rs.getInt("id"));
                t.setUser_id(rs.getInt("user_id"));
                t.setTitle(rs.getString("title"));
                t.setCode(rs.getString("code"));
                t.setLanguage(rs.getString("language"));
                t.setCreatedAt(rs.getTimestamp("created_at"));
                tutorials.add(t);
            }
            conn.close();
            return tutorials;
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
    
    
     public List<Tutorial> getAllTutorials(){
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tot");
            List<Tutorial> tutorials = new ArrayList<>();
            while(rs.next()){
                Tutorial t = new Tutorial();
                t.setId(rs.getInt("id"));
                t.setUser_id(rs.getInt("user_id"));
                t.setTitle(rs.getString("title"));
                t.setCode(rs.getString("code"));
                tutorials.add(t);
            }
            conn.close();
            return tutorials;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
        
    }
    
    public List<Question> getAllQuestions(){
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ask");
            List<Question> questions = new ArrayList<>();
            while(rs.next()){
                Question t = new Question(
                        rs.getInt("id"),rs.getInt("user_id"),
                        rs.getString("title"),rs.getString("question"),
                        rs.getTimestamp("created_at")
                );
                questions.add(t);
            }
            conn.close();
            return questions;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
        
    }
    
    public int login(String username,String password){
        try {
            Connection connecttion = connect();
            Statement statement = connecttion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id,name,pass FROM users");
            while(rs.next()){
                if(rs.getString("name").equals(username) && rs.getString("pass").equals(password)){
                    return rs.getInt("id");
                }
            }
            return 0;
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    public boolean register(String username,String password){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            rs.moveToInsertRow();
            rs.updateString("name", username);
            rs.updateString("pass", password);
            rs.insertRow();
            conn.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean createTutorial(String title,String code,String language){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM tot");
            rs.moveToInsertRow();
            rs.updateString("title", title);
            rs.updateString("code", code);
            rs.updateInt("user_id", LoggedInUser.userId);
            rs.updateString("language", language);
            rs.insertRow();
            conn.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean postAnswer(String answer,int question_id){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM ans");
            rs.moveToInsertRow();
            rs.updateString("answer", answer);
            rs.updateInt("ask_id", question_id);
            rs.updateInt("user_id", LoggedInUser.userId);
            rs.insertRow();
            conn.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean askQuestion(String title, String body){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM ask");
            rs.moveToInsertRow();
            rs.updateString("title", title);
            rs.updateString("question", body);
            rs.updateInt("user_id", LoggedInUser.userId);
            rs.insertRow();
            conn.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
}