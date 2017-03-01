/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_form;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Nitu
 */
public class LoadingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //connect();
        progressAction();
        
    }
    @FXML
            Stage stage;
    Connection c;
    Statement stat;
    ResultSet rs;
    BufferedReader br;
    FileReader fr;
    String username,password;
    @FXML
    Label loadstat;
    @FXML
    ProgressBar pb=new ProgressBar(0);
    final String content[]={
        "Loading Cached Object...done",
        "Loading Core...",
        "Reading Module Storage...",
        "Reading Module Storage...done",
        "Turning On Modules...",
        "Starting Modules...",
        "Checking DataBase...",
        "Retriving Mysql drivers...",
        "Connecting to Database...",
        "Checking Mysql Login...",
        "Setup Complete"};
    Task work;
    @FXML
    public void progressAction()
    {
        
        pb.setStyle("-fx-accent: black;");
    pb.setProgress(0);
    work=retTask();    
        pb.progressProperty().unbind();
        pb.progressProperty().bind(work.progressProperty());
        
        work.setOnSucceeded(new EventHandler<Event>(){

            @Override
            public void handle(Event event) {
               connect();
               System.out.println("successful");
            }
        });
        new Thread(work).start();
    }
    
    int i;
    public Task retTask() {
        
        return new Task() {
            @Override
            protected Object call() throws Exception {
          
                for ( i = 0; i <10; i++) {
                    
                    Thread.sleep(1150);
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                           loadstat.setText(""+content[i]);
                        }
                    });//loadstat.setText(content[i]);
                    updateMessage(i+" milliseconds");
                    updateProgress(i + 1, 10);
                    
 //                   System.out.println(""+i+""+pb.getProgress());
                }
                return true;
            }
        };
    }
    
    private void con()throws Exception
    {
            fr=new FileReader("vinpass.txt");    
             br=new BufferedReader(fr);
            String str=br.readLine();
            String str1[]=str.split(" ");
            username=str1[0];
            password="";
            br.close();
            fr.close();
    }
    @FXML
    Button check;
     public void connect()
    { 
        try{
        con();
        Class.forName("com.mysql.jdbc.Driver");
        c=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro",username,password);
        stat=c.createStatement();
        rs=stat.executeQuery("Select * from uni");
        Scene cur=check.getScene();
            cur.getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("/finalpro/login.fxml"));
            Scene sc=new Scene(root);
            stage=new Stage();
            stage.setScene(sc);
            stage.show();
            rs.close();
            stat.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        try {
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro",username,password);
            stat=c.createStatement();
            rs=stat.executeQuery("Select * from uni");
            }catch (SQLException ex) {
            try {
                Scene cur=check.getScene();
                cur.getWindow().hide();
                Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/No_Init.fxml"));
                Scene sc=new Scene(root);
                stage=new Stage();
                stage.setScene(sc);
                stage.show();
                
            } catch (IOException ex1) {
                Logger.getLogger(LoadingController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            }
        
        }
    }
}
