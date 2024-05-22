package com.mycompany.pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {
    
    @FXML
    private Button database;
    
    @FXML
    private Button jugar;
    
    @FXML
    private ImageView logo;
     
    @FXML
    private ScrollPane logs;
    
    
    @FXML
    private TextArea lblLogs;
    
    @FXML
    private ChoiceBox<String> dificultad ;
    
    private String[] difi = {"recluta","marine","veterano","pesadilla"};
    Singleton sin = Singleton.getInstancia();
    @FXML
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();
        logs.setLayoutX(14);
        logs.setLayoutY(582);
        dificultad.getItems().addAll(difi);  
        String texto = sin.getLogs();
        lblLogs.setText(texto);
        logo.setImage(new Image("file:.//fotos//pokemon.png"));
        
         dificultad.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                    getdif();
                }
            }
        );
 
    }
    
    
    
    public void dif()
    {
        dificultad.getItems().addAll(difi);   
    }
    @FXML
    public String getdif()
    {
        String resul = "recluta";
        if (dificultad.getValue() != null)
        {
            sin.setdif(dificultad.getValue());
            sin.setLogs("la difucultad elejida es  = " + dificultad.getValue());
            String texto = sin.getLogs();
            lblLogs.setText(texto);
            resul = dificultad.getValue();
            
        }
        else
        {
            sin.setLogs("la difucultad no ha sido cambiada");
        }
        
        return resul;
    }
    
    @FXML
    private void db()
        
    {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("aviso.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                
                stage.setTitle("aviso");
                sin.setLogs("moviendose a la base de datos");
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) database.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {}
        
    }
    
    @FXML
    private void juego() throws SQLException
        
    {
        sin.setLogs("moviendose al juego");
        try {
            if(sin.getentrenadorid() != 0)
            {
                sin.setEquipoEntrenadores();
            }
            else
            {
                sin.setEquipoCustomCompleto();
            }

  
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("juego.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                stage.setTitle("POKEMON");
               
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) jugar.getScene().getWindow();

                stageAcerrar.close();

            } catch (Exception e) {System.out.println(e);}
 
    }
    
    @FXML
    private void pokedex()
        
    {
        sin.setLogs("moviendose a pokedex");
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("pokedex.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                stage.setTitle("pokedex");
               
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) jugar.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {sin.setLogs("fallo al moverse a pokedex");}
 
    }
    
    
    @FXML
    private void entrenadores()
        
    {
        sin.setLogs("moviendose a entrenadores");
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("entrenadores.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                
                stage.setTitle("entrenadores");
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) jugar.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {sin.setLogs("fallo al moverse a entrenadores");}
 
    }
    @FXML
    private void normas()
        
    {
        sin.setLogs("moviendose a normas");
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("normas.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                
                stage.setTitle("normas");
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) jugar.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {sin.setLogs("fallo al moverse a entrenadores");}
 
    }
    
    
}
