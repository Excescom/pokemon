package com.mycompany.pokemon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BaseDatosController {
    
    
    
    
    @FXML
    private TextField ip;
    
    @FXML
    private TextField db;
    
    @FXML
    private TextField port;
    
    @FXML
    private TextField passwd;
    
    @FXML
    private TextField user;
    
    @FXML
    private TextField drv;
    
    @FXML
    private Label lbl;
    
    @FXML
    private Button check;
    
    @FXML
    private Button start;
    
    Singleton sin = Singleton.getInstancia();
    Consultas consultas = new Consultas();
    @FXML
    private void initialize() 
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
            
    {
        sin.Singelton();
        
    }
     
    
     public static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
     
    @FXML
    private void prueba() throws SQLException
    {
        boolean conectado = consultas.comprobar(port.getText(), ip.getText(), user.getText(), passwd.getText(), db.getText(), drv.getText());
        
        if(conectado == true)
        {
            String texto = "conectado";
            lbl.setText(texto);
            showAlert(AlertType.INFORMATION,"CONECTADO","conexión correcta");
            sin.setLogs("conectado a la base de datos");
            int i;
            
            
        }
        else
        {
            String texto = "fallo en conexión";
            showAlert(AlertType.INFORMATION,"ERROR","conexión erronea");
            lbl.setText(texto);
            sin.setLogs("fallo en conexión base de datos");
        }
        
        
  
    }
    
    
    @FXML
    private void empieza()
    {
        sin.setLogs("probando conexión");
        boolean conectado = consultas.comprobar(port.getText(), ip.getText(), user.getText(), passwd.getText(), db.getText(), drv.getText());
        
        if(conectado == true)
        {
            String texto = "conectado";
            lbl.setText(texto);
            sin.setContrasenya(passwd.getText());
            sin.setUsuario(user.getText());
            sin.setLogs("conectado a la base de datos");
            sin.setLogs("moviendose al menú principal");
            
             try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("menu.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                
                stage.setTitle("Menú principal");
                stage.setScene(scene);
                stage.show();
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                //cerramos la ventana actual buscando su referencia a través de algún 
                //control (en este caso el botón 'start'
                Stage stageAcerrar = (Stage) start.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {sin.setLogs("fallo al moverse al menú principal");}
        }
        else
        {
            sin.setLogs("fallo al moverse al menú principal");
            String texto = "fallo en conexión";
            lbl.setText(texto);
        }
        
        
        
    }
}
