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
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntrenadorCustomController {
    
    
    
    @FXML
    private Button botonMenu;
    
    @FXML
    private Label lbl;
    
    @FXML
    private ImageView img;
   
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
     @FXML
    private ChoiceBox<String> pokemon1 ;
    
    String pokemones[]= new String [1];
    Consultas consultas = new Consultas();
    
    Singleton sin = Singleton.getInstancia();
    @FXML
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        //logs = new ScrollPane();

        String texto = sin.getLogs();
        lblLogs.setText(texto);
         int i = 0;
                for(i =1 ; i <= 151 ; i++)
                {
                    pokemones [i-1] = consultas.PokemonNombre(i);
                    if( i < 151)
                    {
                        pokemones = Arrays.copyOf(pokemones,pokemones.length+1);
                    }
                    
                }
                pokemon1.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        // Llama al método que deseas ejecutar cuando se selecciona un elemento en el ChoiceBox
                        mostrar();
                        lbl.setText("");
                        String numero = consultas.pokemonId(pokemon1.getValue());
                        int numeroInt = Integer.parseInt(numero); 
                        List<Map<String, String>> info = consultas.PokemonCompleto(numeroInt);
                        
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : info) {
                            // Iterador para recorrer el mapa de información de cada pokemon
                            for (Map.Entry<String, String> entry : pokemonInfo.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                lbl.setText(lbl.getText() + "\n" +" "+ key+ ": " + value);
                            }
                        }
                       
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
        pokemon1.getItems().addAll(pokemones);  
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
                
                stage.setTitle("MENU");
                sin.setLogs("moviendose al menu");
                stage.setScene(scene);
                stage.resizableProperty().setValue(false); //sin botón de restaurar/maximiza
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

        if (pokemon1.getValue() != null)
        {
            String numeroEscrito = consultas.pokemonId(pokemon1.getValue());
            sin.setLogs("pokemon " +pokemon1.getValue()+ " seleccionado");
            lblLogs.setText(sin.getLogs());
            int numero = Integer.parseInt(numeroEscrito);
            
                     
            if(numero < 10)
            {
                numeroEscrito ="00"+numeroEscrito;
                img.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else if(numero < 100)
            {
                numeroEscrito ="0"+numeroEscrito;
                img.setImage(new Image("file:.//fotos//pokemones//"+numeroEscrito+".png"));
            }
            else
            {
                img.setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
        }
        else
        {
            sin.setLogs("1 pokemon inexistete");
            lblLogs.setText(sin.getLogs());
        }
        
       
        
    }
  
}
