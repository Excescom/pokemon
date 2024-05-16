package com.mycompany.pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EntrenadoresController {
    @FXML
    private Button botonMenu;
    
    @FXML
    private ImageView img;
    
     @FXML
    private ImageView pokemon1;
     
    @FXML
    private Label lbl;
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
    
     @FXML
    private ChoiceBox<String> entrenadores ;
    
    
    String entrenador[]= new String [1];
    Consultas consultas = new Consultas();
    
    Singleton sin = Singleton.getInstancia();
    @FXML
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();

        String texto = sin.getLogs();
        lblLogs.setText(texto);
        
                int i = 0;
                for(i =1 ; i <= 12 ; i++)
                {
                    entrenador [i-1] = consultas.Entrenadornombre(i);
                    if( i < 12)
                    {
                        entrenador = Arrays.copyOf(entrenador,entrenador.length+1);
                    }
                    
                }
 
        entrenadores.getItems().addAll(entrenador); 
        
       

        entrenadores.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
                {
                    try 
                    {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar();
                        String nombre = entrenadores.getValue();
                        List<Map<String, String>> info = consultas.PokemonNmobreEntrenador(entrenadores.getValue());
                        // Iterador para recorrer la lista
                        int i = 1;
                        lbl.setText("");
                        for (Map<String, String> PokemonNmobreEntrenador : info) 
                        {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : PokemonNmobreEntrenador.entrySet()) 
                            {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)

                                lbl.setText(lbl.getText() + "\n" + " " + key +" "+ i + " : " + value);
                                i++;
                            }
                        }
                        int id = consultas.EntrenadorId(entrenadores.getValue());
                        sin.setEntrenadorid(id);
                        
                        sin.setEquipoEntrenadores();
                        sin.setLogs("llegao");
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        );
    }
    
    @FXML
    private void menu()
        
    {
        try {
                sin.setLogs("moviendose al menú");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("menu.fxml"));

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
                stage.setTitle("MENU");
                sin.setLogs("moviendose al menu");
                stage.setScene(scene);
                stage.show();

                //cerramos la ventana actual buscando su referencia a través de algún 
                //control 
                Stage stageAcerrar = (Stage) botonMenu.getScene().getWindow();

                stageAcerrar.close();

            } catch (IOException e) {}
        
    }
  
     @FXML
    private void mostrar() throws SQLException
    {

        if (entrenadores.getValue() != null)
        {   
            sin.setLogs("entrenador " +entrenadores.getValue()+ " seleccionado");
            sin.setEntrenadorid(consultas.EntrenadorId(entrenadores.getValue()));
            lblLogs.setText(sin.getLogs());
            String trainer = entrenadores.getValue();
            img.setImage(new Image("file:.//fotos//entrenadores//"+trainer+".png"));
        }   

    }
}