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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jayesh
 */
public class LoginController implements Initializable {
    @FXML
    private Pane p1;
    @FXML
    private Pane p2;
    @FXML
    private Pane p3;
    @FXML
    private Pane p4;
    @FXML
    private Pane p5;
    @FXML
    private Pane p6;
    
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l6;
    @FXML
    private Label ladd;
    @FXML
    private Label lremove;
    
    @FXML
    private ImageView IV1;
    @FXML
    private ImageView IV2;
    @FXML
    private ImageView IV3;
    @FXML
    private ImageView IV4;
    @FXML
    private ImageView IV5;
    @FXML
    private ImageView IV6;
    
    PreparedStatement ps;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        write();
    }    
BufferedReader br;
    FileReader fr;
    String username,password;
    private void con()throws Exception
    {
            fr=new FileReader("vinpass.txt");    
             br=new BufferedReader(fr);
            String str=br.readLine();
            String str1[]=str.split(" ");
            username=str1[0];
            //password=str1[1];
            password="";
            br.close();
            fr.close();
    }
    public void write()
    {
        
        try {
            con();
            Class.forName("com.mysql.jdbc.Driver");
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost/pro", username, password);
            Statement stat=c.createStatement();
            ResultSet rs=stat.executeQuery("select * from staff");
            int no_of_record=0;
            String names[]=new String[6];
            String imgsrc[]=new String[6];
            int i=0;
            while(rs.next())
            {
                no_of_record++;
                names[i]=rs.getString(1);
                imgsrc[i]=rs.getString(3);
                System.out.println(imgsrc[i]);
                i++;
            }
            
            switch(no_of_record)
            {           
                case 6:
                      p6.setVisible(true);
                      l6.setText(names[5]);
                      IV6.setImage(new Image(new File(imgsrc[5]).toURI().toString()));
                case 5:
                      p5.setVisible(true);
                      l5.setText(names[4]);
                    IV5.setImage(new Image(new File(imgsrc[4]).toURI().toString()));
                case 4:
                      p4.setVisible(true);
                    l4.setText(names[3]);
                    IV4.setImage(new Image(new File(imgsrc[3]).toURI().toString()));
                case 3:
                      p3.setVisible(true);
                    l3.setText(names[2]);
                    IV3.setImage(new Image(new File(imgsrc[2]).toURI().toString()));
                case 2:
                      p2.setVisible(true);
                    l2.setText(names[1]);
                    IV2.setImage(new Image(new File(imgsrc[1]).toURI().toString()));
                case 1:
                      p1.setVisible(true);
                      l1.setText(names[0]);
                      IV1.setImage(new Image(new File(imgsrc[0]).toURI().toString()));
                      break;
                default: 
                    p6.setVisible(true);l6.setText(names[5]);
                    p5.setVisible(true);l5.setText(names[4]);
                    p4.setVisible(true);l4.setText(names[3]);
                    p3.setVisible(true);l3.setText(names[2]);
                    p2.setVisible(true);l2.setText(names[1]);
                    p1.setVisible(true);l1.setText(names[0]);
                    IV1.setImage(new Image(new File(imgsrc[0]).toURI().toString()));
                    IV2.setImage(new Image(new File(imgsrc[1]).toURI().toString()));
                    IV3.setImage(new Image(new File(imgsrc[2]).toURI().toString()));
                    IV4.setImage(new Image(new File(imgsrc[3]).toURI().toString()));
                    IV5.setImage(new Image(new File(imgsrc[4]).toURI().toString()));
                    IV6.setImage(new Image(new File(imgsrc[5]).toURI().toString()));
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    private void retrivedata(int index)
    {
        try {
            con();
            Class.forName("com.mysql.jdbc.Driver");
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost/pro", username, password);
            Statement stat=c.createStatement();
            ResultSet rs=stat.executeQuery("select * from staff where srno="+index);
            if(rs.next())
            {
            LoginSeprateController.image=rs.getString(3).replace(".jpg", "b.jpg");
            LoginSeprateController.name=rs.getString(1);
            LoginSeprateController.pass=rs.getString(2);
            LoginSeprateController.srno=rs.getInt(4);        
            Scene cur=IV1.getScene();
            cur.getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("/finalpro/loginSeprate.fxml"));
            Scene sc=new Scene(root);
            Stage stage=new Stage();
            stage.setScene(sc);
            stage.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void imag1()
    {
        retrivedata(1);
    }
    public void imag2()
    {
    retrivedata(2);
    }
    public void imag3()
    {
    retrivedata(3);}
    public void imag4()
    {
    retrivedata(4);}
    public void imag5()
    {
    retrivedata(5);}
    public void imag6()
    {
    retrivedata(6);
    }
        
}
