/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nitu
 */
public class LoginSeprateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField password;
    @FXML
    Label labelname;
    @FXML
    ImageView profile;
    @FXML
    Label err;
    @FXML 
    Label det;
    static String image="";
    static String name="",pass=""; 
    static int srno=0;
    static String msg="";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelname.setText(name);
        profile.setImage(new Image(new File(image).toURI().toString()));

    }    
    
    BufferedReader br;
    FileReader fr;
    String username,password1;
    private void con()throws Exception
    {
            fr=new FileReader("vinpass.txt");    
             br=new BufferedReader(fr);
            String str=br.readLine();
            String str1[]=str.split(" ");
            username=str1[0];
            //password1=str1[1];
            br.close();
            fr.close();
    }
    public void onLoginBtn_Clicked()
    {
        if(pass.equals(password.getText()))
        {
            try {
                err.setVisible(false);
                System.out.println("yahhh");
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
                con();
                Class.forName("com.mysql.jdbc.Driver");
                Connection c=DriverManager.getConnection("jdbc:mysql://localhost/pro", username, password1);
                Statement stat=c.createStatement();
                ResultSet rs=stat.executeQuery("select details from staff where srno='"+srno+"'");
               
                if(rs.next())
                {
                    msg=rs.getString(1)+"\n on "+System.getProperty("os.name");
                }
                stat.executeUpdate("update staff set details='Last Login at "+df.format(date)+"' where srno='"+srno+"'");
                if(name.equals("admin"))
                {
                    try {
                        Scene cur=password.getScene();
                        cur.getWindow().hide();
                        Parent root=FXMLLoader.load(getClass().getResource("/finalpro/mediator.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    
                    try {
                        
                        Scene cur=password.getScene();
                        cur.getWindow().hide();
                        Parent root=FXMLLoader.load(getClass().getResource("/finalpro/LoginSuccess.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }/**/
                }  
            } catch (Exception ex) {
                Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
        else
        {
        err.setVisible(true);
        }
    }
    public void onRevert()
    {
        try {
            Scene cur=password.getScene();
            cur.getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("/finalpro/login.fxml"));
            Scene sc=new Scene(root);
            Stage stage=new Stage();
            stage.setScene(sc);
            stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
