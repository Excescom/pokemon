package com.mycompany.pokemon;

import java.io.IOException;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NormasController {
    @FXML
    private Button botonMenu;
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
    
    @FXML
    private Label lbl;

    Singleton sin = Singleton.getInstancia();
    @FXML
    //escribo las normas
    private void initialize()
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        lbl.setText("1: Selección de Entrenador: Al iniciar el juego,  puedes elegir uno de los 12 entrenadores disponibles. \n Cada entrenador tiene un equipo de Pokémon único para desafiar a TUX.\n" +
"\n" +
"2:Elección de Dificultad: Antes de comenzar la batalla, debes seleccionar la dificultad del juego entre cuatro niveles: \n Pesadilla, Veterano, Marine o Recluta. La dificultad afectará el nivel de tus Pokémon y el de TUX.\n" +
"\n" +
"3:Combate por Turnos: La batalla se desarrolla por turnos. Tanto tú como TUX seleccionan un Pokémon activo para el combate. \n El Pokémon con la velocidad más alta ataca primero. Si hay un empate, puedes decidir cuál de tus Pokémon comienza la batalla.\n" +
"\n" +
"4:Calculadora de Daño: Durante el combate, se calcula el daño que tus Pokémon infligen a TUX y viceversa. \n El daño se calcula según el nivel, habilidades y bonificaciones de los Pokémon en juego.\n" +
"\n" +
"5:Ataques Especiales: Tienes la opción de usar un ataque especial en cualquier momento. \n Sin embargo, dejará a tu Pokémon fuera de combate por un turno. \n Si es tu único Pokémon en combate, quedará expuesto a los ataques de TUX.\n" +
"\n" +
"6:Registro de la Batalla: Todo lo que sucede durante la batalla se registra en el área de 'LOG' en la interfaz. \n Puedes seguir el progreso de la batalla y ver los eventos clave que ocurrieron.\n" +
"\n" +
"7:Derrota de TUX: Si logras derrotar a TUX, aparecerá otro de igual nivel para continuar la batalla. \n Deberás seguir luchando con tus Pokémon, aunque estén agotados, hasta superar todos los desafíos.");
        logs = new ScrollPane();

        String texto = sin.getLogs();
        lblLogs.setText(texto);
    }
    
    @FXML
    //te lleva al manú
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
  
}
