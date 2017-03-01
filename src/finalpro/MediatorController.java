/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpro;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jayesh
 */
public class MediatorController implements Initializable {
    //ch pass user
    @FXML
    private TextField chuser;
    @FXML
    private TextField chpass;
    @FXML
    private Button chbtn;
    //add user
    @FXML
    private TextField adduser;
    @FXML
    private TextField addpass;
    @FXML
    private Button addbtn;
    //delete user
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button delbtn;
    @FXML
    private CheckBox confirm;
    @FXML
    private Label userl;
    @FXML
    private Label passl;
    //database
    @FXML
    private Button importbtn;
    @FXML
    private Button exportbtn;
    @FXML
    private Button mainbtn;
    DateFormat df;
    Date date;
    String curuser;
    @FXML
    TabPane tp;
    //
    @FXML
            ImageView iv1;
    @FXML
            ImageView iv2;
    @FXML
            ImageView iv3;
    @FXML
            ImageView iv4;
    @FXML
            ImageView iv5;
    @FXML
            ImageView iv6;
    
    @FXML
            ImageView iv11;
    @FXML
            ImageView iv21;
    @FXML
            ImageView iv31;
    @FXML
            ImageView iv41;
    @FXML
            ImageView iv51;
    @FXML
            ImageView iv61;
    PreparedStatement ps;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initi();
    }    
    private void initi()
    {
        iv1.setImage(new Image(new File("src//users_image/i1.jpg").toURI().toString()));
        iv2.setImage(new Image(new File("src//users_image/i2.jpg").toURI().toString()));
        iv3.setImage(new Image(new File("src//users_image/i3.jpg").toURI().toString()));
        iv4.setImage(new Image(new File("src//users_image/i4.jpg").toURI().toString()));
        iv5.setImage(new Image(new File("src//users_image/i5.jpg").toURI().toString()));
        iv6.setImage(new Image(new File("src//users_image/i6.jpg").toURI().toString()));
        
    }
    @FXML
            Label status;
    
    @FXML
            Label status1;
    String imageSelected="",addimageSelected="";
    public void imag1()
    {
    imageSelected="src//users_image/i1.jpg";
    status.setText("Hydrangeas");
    }
    
    public void imag2()
    {
    imageSelected="src//users_image/i2.jpg";    
    status.setText("Jellyfish");
    }
    public void imag3()
    {
    imageSelected="src//users_image/i3.jpg";    
    status.setText("Koala");
    }
    
    public void imag4()
    {
    imageSelected="src//users_image/i4.jpg";    
    status.setText("Desert");
    }
    
    public void imag5()
    {
    imageSelected="src//users_image/i5.jpg";    
    status.setText("Penguins");
    }
    
    public void imag6()
    {
    imageSelected="src//users_image/i6.jpg";    
    status.setText("Tulips");
    }
    public void imag11()
    {
    addimageSelected="src//users_image/i1.jpg";
    status1.setText("Hydrangeas");
    }
    
    public void imag21()
    {
    addimageSelected="src//users_image/i2.jpg";    
    status1.setText("Jellyfish");
    }
    public void imag31()
    {
    addimageSelected="src//users_image/i3.jpg";    
    status1.setText("Koala");
    }
    
    public void imag41()
    {
    addimageSelected="src//users_image/i4.jpg";    
    status1.setText("Desert");
    }
    
    public void imag51()
    {
    addimageSelected="src//users_image/i5.jpg";    
    status1.setText("Penguins");
    }
    
    public void imag61()
    {
    addimageSelected="src//users_image/i6.jpg";    
    status1.setText("Tulips");
    }
    
    BufferedReader br;
    FileReader fr;
    String username1,password1;
    private void con()throws Exception
    {
            fr=new FileReader("vinpass.txt");    
             br=new BufferedReader(fr);
            String str=br.readLine();
            String str1[]=str.split(" ");
            username1=str1[0];
            password1=str1[1];
            br.close();
            fr.close();
    }
    Connection c;
    
    private void connect()
    {
        try {
            con();
            c=DriverManager.getConnection("jdbc:mysql://localhost/pro",username1,password1);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
    }
    private void log()
       {
        try {
            String query="insert into log values(?,?)";
            ps = c.prepareStatement(query);
            ps.setString(1,"admin");
            ps.setString(2,"Tried logging in at "+df.format(date));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//change password
    @FXML
    public void chpassClick()
    {
        try {
            connect();
            String u=chuser.getText();
            Statement stat=c.createStatement();
            ResultSet rs=stat.executeQuery("select * from staff where namer='"+u+"'");
            if(rs.next())
            {
                int st=stat.executeUpdate("update staff set passr='"+chpass.getText()+"' where namer='"+u+"'");
                if(st>0)
                {System.out.println("updated");
                    if(!(imageSelected.isEmpty()))
                    {
                        stat.executeUpdate("update staff set image='"+imageSelected+"' where namer='"+u+"'");
                    }
                    chpass.setText("");
                    chuser.setText("");
                    status1.setText("");
                    imageSelected="";
                    try {
                        Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/staff_saved.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    try {
                        Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/staff_not_saved.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else {
                try {
                        Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/staff_not_saved.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally
        {
         dis();   
        }
    }
    
//add user
    @FXML
    public void addUserClick()
    {
        try {
            connect();
            String u=adduser.getText();
            Statement stat=c.createStatement();
            int nocount=0;
            ResultSet rs=stat.executeQuery("select * from staff");
            while(rs.next())
            {
                nocount++;
            }
            rs=stat.executeQuery("select * from staff where namer='"+u+"'");
            if(!rs.next())
            {
                if(addimageSelected.isEmpty())
                {
                    status.setText("image not selected");
                }
                else
                {
                
                int st=stat.executeUpdate("insert into staff values ('"+adduser.getText()+"','"+addpass.getText()+"','"+addimageSelected+"',"+(nocount+1)+",'Welcome First time login')");
                if(st>0){
                        adduser.setText("");
                    addpass.setText("");
                    addimageSelected="";
                    status.setText("");
                    try {
                        Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/staff_added.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    try {
                        Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/staff_not_added.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }
            }else System.out.println("user alredy exits");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally
        {
         dis();   
        }
    }
//delete user
    @FXML
    public void delUserClick()
    {
        try {
            
            connect();
            Statement stat=c.createStatement();
            ResultSet rs=stat.executeQuery("select * from staff where namer='"+username.getText()+"'");
            if(rs.next())
            {
                userl.setVisible(false);
                ResultSet rs1=stat.executeQuery("select * from staff where passr='"+password.getText()+"'");
                if(rs1.next()){ 
                passl.setVisible(false);
                if(confirm.isSelected())
                {
                    //confirm.set
                    //confirm.setForeground(Color.BLACK);
                    int st=stat.executeUpdate("delete from staff where namer='"+username.getText()+"'");
                    if(st>0){
                        username.setText("");
                        password.setText("");
                        confirm.setSelected(false);
                     try {
                        Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/staff_deleted.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                    else {
                        try {
                        Parent root=FXMLLoader.load(getClass().getResource("/DialogsInt/staff_not_deleted.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                }
                else
                {
                    //confirm.setForeground(Color.red);
                }
                }else
                {
                    passl.setVisible(true);
                }
            }else {
                userl.setVisible(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally
        {
         dis();   
        }
    }
    //log tab
    //show Btn
        @FXML
    Label showlabel;
     @FXML
    public void onShowBtnClick(ActionEvent event)
    {
         
       
        try {
            showlabel.setVisible(false);
            con();
             Class.forName("com.mysql.jdbc.Driver");
        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/pro",username1,password1);
        String query="select * from log";
        Statement stat=con1.createStatement();
        ResultSet rs1=stat.executeQuery(query);
        if(rs1.next())
        {
            Parent par = FXMLLoader.load(getClass().getResource("/logs_pkg/show_logs.fxml"));
            Scene scene = new Scene(par);
            Stage stage = new Stage();
            stage.setScene(scene);
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        else
        {
            Parent par = FXMLLoader.load(getClass().getResource("/logs_pkg/empty_logs_dialog.fxml"));
            Scene scene = new Scene(par);
            Stage stage = new Stage();
            stage.setScene(scene);
            
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.setMaximized(true);
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
            
        }
        } catch (IOException ex) {
            Logger.getLogger(MediatorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MediatorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MediatorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MediatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //clear Btn
    @FXML
    public void onClearLogs(ActionEvent event) {
        try
        {
            con();
        String query="delete from log";
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/pro",username1,password1);
        Statement st=con1.createStatement();
        st.executeUpdate(query);
        }catch(Exception ex){}
        showlabel.setVisible(true);
    }
//Database
    @FXML
    public void onMainBtnClick(ActionEvent event)
    {
        try {
            Parent par = FXMLLoader.load(getClass().getResource("/docsysgui/GUI.fxml"));
            Scene scene = new Scene(par);
            Stage stage = new Stage();
            stage.setScene(scene);
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setMaximized(true);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(MediatorController.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            dis();
        }
        
    }
    private void dis()
    {
        try {
            if(c.isClosed()){}
            else
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void onCLickImportbtn() 
    {
        try {
            con();
        } catch (Exception ex) {
            Logger.getLogger(MediatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage s=new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("SQL Files","*.sql"));
        File selectedFile = fileChooser.showOpenDialog(s);
        String filen="";
        if (selectedFile != null) {
            System.out.println(selectedFile.getAbsolutePath());
             filen=selectedFile.getAbsolutePath();
        }
        if(System.getProperty("os.name").contains("Win"))
            importD(username1,password1,filen);
        else
            importD1(username1,password1,filen);
        
        
    }
    public void importD(String user,String pass,String backfile)
    {
       
        try
        {
        System.out.println("importing");
        File f1=new File("imp.bat");
        FileWriter fw1=new FileWriter(f1);
        File f2=new File(backfile);
        BufferedWriter br1=new BufferedWriter(fw1);
        br1.write("mysql -u "+user+" -p "+pass+" pro <"+f2.getAbsolutePath());
        br1.newLine();
        br1.write("exit");
        br1.close();
        fw1.close();
        Process runtimeProcess =Runtime.getRuntime().exec("cmd /C start "+f1.getCanonicalPath());
           // Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();
       
        if(processComplete == 0){System.out.println("5");
        System.out.println("imported");
        }else{System.out.println("import cancle");}
        }catch(Exception e){System.out.println("7"+e.getMessage());}
        
        
    }
    
    public void importD1(String user,String pass,String filen)
    {
       //String executeCmd= "mysql -u "+dbUser+" -p"+dbPass+" "+dbName +" c:\\Backup\\backup3.sql";
        try
        {
        System.out.println("importing");
        File f1=new File("imp.sh");
        FileWriter fw1=new FileWriter(f1);
        File f2=new File(filen);
        BufferedWriter br1=new BufferedWriter(fw1);
        br1.write("mysql -u "+user+" root -p "+pass+" pro <"+f2.getAbsolutePath());
        br1.newLine();
        br1.write("exit(0)");
        br1.close();
        fw1.close();
        Process runtimeProcess =Runtime.getRuntime().exec(""+f1.getAbsolutePath());
           // Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();
       
        if(processComplete == 0){System.out.println("5");
        System.out.println("imported");
        }else{System.out.println("import cancle");}
        }catch(Exception e){System.out.println("7"+e.getMessage());}
    }
    @FXML
    public void onClickExport()
    {
        try {
            con();
        } catch (Exception ex) {
            Logger.getLogger(MediatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
       //connect();
       String executeCmd = "";
       Date d1=new Date();
       SimpleDateFormat df = new SimpleDateFormat("dd_MM_yyyy_hh_mm");
       String filename="backup"+df.format(d1)+".sql";
       File f1=new File(filename);
       try
        {
            executeCmd = "mysqldump -u "+username1+" -p"+password1+" pro -r "+f1.getCanonicalPath();
             System.out.print("exporting"+executeCmd);
            Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if(processComplete == 0){
         System.out.print("exported");
            
            } else {
         System.out.print("notexported");
                }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    
}
