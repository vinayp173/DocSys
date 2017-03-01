/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalpro;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nitu
 */
public class LoginSuc implements Initializable {
    @FXML
    private Label det;
    @FXML
    private Button okbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        det.setText(LoginSeprateController.msg);
    }    

    @FXML
    private void onclickok(ActionEvent event) {
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
                    try {
                        
                        Parent root=FXMLLoader.load(getClass().getResource("/docsysgui/GUI.fxml"));
                        Scene sc=new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(sc);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setMaximized(true);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginSeprateController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
}
