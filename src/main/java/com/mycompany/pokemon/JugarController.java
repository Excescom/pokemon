package com.mycompany.pokemon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JugarController {
    @FXML
    private Button botonMenu;
    
    @FXML
    private Button ataque;
    
    @FXML
    private Button ataqueS;
    
    @FXML
    private Label vida;
    
    @FXML
    private Label tuxmuertos;
    
    @FXML
    private Label datosPokemon;
    
    @FXML
    private Label datosTux;
    
    @FXML
    private Label vidaTux;
    
    @FXML
    private ImageView tux;
    
    
    @FXML
    private ImageView pokemon1;
    @FXML
    private ImageView pokemon2;
    @FXML
    private ImageView pokemon3;
    @FXML
    private ImageView pokemon4;
    @FXML
    private ImageView pokemon5;
    @FXML
    private ImageView pokemon6;
    
   
    
    @FXML
    private ScrollPane logs;
    
    @FXML
    private TextArea lblLogs;
    Singleton sin = Singleton.getInstancia();
    
    Pokemon[] equipo = sin.getEquipo();

    @FXML
    private void initialize() throws SQLException
    //método que es llamado internamente justo después del constructor
    //En el constructor NO SE tiene acceso a las variables enlazadas con @FXML
    //En initialize() ya están las variables creadas y son accesibles
    {     
        logs = new ScrollPane();
        tux.setImage(new Image("file:.//fotos//pokemones//tux.png"));
        
        Tux tux = new Tux(sin.getdif()); 
        sin.setTux(tux);
        String texto = sin.getLogs();
        lblLogs.setText(texto);
        datos();
        sin.initTuxMuertos();  
        String tuxm = Integer.toString(sin.getTuxMuertos());
        tuxmuertos.setText(tuxm);
        Consultas consultas = new Consultas();
        
        sin.initLogsJuego();
        sin.setLogsJuego("Empieza el juego");
        lblLogs.setText(sin.getLogsJuego());
         
        Img();
        
        String vidaPokemon = Double.toString(equipo[0].getVida());
        String vidaTotal = Double.toString(equipo[0].getVidaTotal());
        vida.setText(vidaPokemon +" / "+ vidaTotal);
        
        String vidaPTux = Double.toString(sin.getTux().getVida());
        String vidaTuxTotal = Double.toString(sin.getTux().getVidaTotal());
        vidaTux.setText(vidaPTux +" / "+ vidaTuxTotal);
        
        AtaqueInicialTux();

            
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
    private void ataque() throws SQLException
    {
        int elegido = 0;
        
             if(equipo[elegido].getVida() <= 0)
            {
                Pokemonmuerto();
            }
            else
            {
            double defensa = sin.getTux().getDefensa();
            int tipo = sin.getTux().getTipo();
                int daño =(int) equipo[elegido].getAtaque(defensa,tipo);
                sin.getTux().quitarvida(daño);
                String vidaPTux = Double.toString(sin.getTux().getVida());
                String vidaTuxTotal = Double.toString(sin.getTux().getVidaTotal());
                vidaTux.setText(vidaPTux +" / "+ vidaTuxTotal);
                sin.setLogsJuego( equipo[elegido].getNombre()+" Ha realizado: "+daño+" puntos de daño");
                lblLogs.setText(sin.getLogsJuego());
                ataque.setDisable(false);
                ataqueS.setDisable(false); 
                Tuxmuerto();
            }
             
             AtaqueTux();
    }
    
    @FXML
    private void ataqueS() throws SQLException
    {
        
            int elegido = 0;
            
            if(equipo[elegido].getVida() <=0)
            {
               Pokemonmuerto();
            }
            else
            {
                int daño =(int)equipo[elegido].getAtaqueS(sin.getTux().getDefensaS(),sin.getTux().getTipo());
                sin.getTux().quitarvida(daño);
                String vidaPTux = Double.toString(sin.getTux().getVida());
                String vidaTuxTotal = Double.toString(sin.getTux().getVidaTotal());
                sin.setLogsJuego( equipo[elegido].getNombre()+" Ataque Especial " +" Ha realizado: "+daño+" puntos de daño");
                lblLogs.setText(sin.getLogsJuego());
                vidaTux.setText(vidaPTux +" / "+ vidaTuxTotal);
                ataque.setDisable(false);
                ataqueS.setDisable(false);
                Tuxmuerto();
            }
            AtaqueTux();

    }
    
    private void datos() throws SQLException
    {
        datosPokemon.setText("");
        datosTux.setText("");
        Consultas consultas = new Consultas();
        int elegido=0;
        
         datosPokemon.setText("nivel: " +  equipo[elegido].getNivel() + "\n" +  
                    "vida Total: " +  equipo[elegido].getVidaTotal() + "\n" + 
                    "ataque: " +  equipo[elegido].ataque + "\n"+
                    "ataque Especial: " +  equipo[elegido].ataqueS + "\n" +
                    "defensa: " +  equipo[elegido].getDefensa() + "\n" + 
                    "defensa especial: " +  equipo[elegido].getDefensaS() + "\n" +
                    "velocidad: " +  equipo[elegido].getVelocidad() + "\n");
        
            
            
            datosTux.setText("nivel: " + sin.getTux().getNivel() + "\n" +  
                    "vida Total: " + sin.getTux().getVidaTotal() + "\n" + 
                    "ataque: " + sin.getTux().ataque + "\n"+
                    "ataque Especial: " + sin.getTux().ataqueS + "\n" +
                    "defensa: " + sin.getTux().getDefensa() + "\n" + 
                    "defensa especial: " + sin.getTux().getDefensaS() + "\n" +
                    "velocidad: " + sin.getTux().getVelocidad() + "\n");

    }
    
            
  private void Img() throws SQLException
  {
      int i;
      Consultas consultas = new Consultas();
      int cantidad = consultas.CantidadPokemon(sin.getentrenadorid());
        ImageView equipoImage[] = {pokemon1,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6};
        for(i = 0;i<cantidad;i++)
        {
            
            
            int id = equipo[i].getID();
            String numero = Integer.toString(id);
            
            if(id < 10)
            {
                numero = "00"+numero;
                equipoImage[i].setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
            else if(id < 100)
            {
                numero = "0"+numero; 
                equipoImage[i].setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }
            else if (id > 100)
            {
                equipoImage[i].setImage(new Image("file:.//fotos//pokemones//"+numero+".png"));
            }          
        }
        
        for(;i<6;i++)
        {
            equipoImage[i].setImage(new Image("file:.//fotos//pokemones//none.png"));
        }
  }
  
  private void AtaqueInicialTux()
  {
      int elegido = 0;
            int daño;
            //empieza el juego
            if(sin.getTux().getVelocidad() > equipo[elegido].getVelocidad())
            {
                int ataca = (int) (Math.random() * 2);
                
                if(ataca == 0)
                {
                   daño = (int) sin.getTux().getAtaque(equipo[elegido].getDefensa(),equipo[elegido].getTipo());
                   equipo[elegido].quitarvida(daño);
                   sin.setLogsJuego(sin.getTux().getNombre() +" Ha realizado: "+daño+" puntos de daño");
                   lblLogs.setText(sin.getLogsJuego());
                }
                else
                {
                    daño = (int) sin.getTux().getAtaqueS(equipo[elegido].getDefensaS(),equipo[elegido].getTipo());
                    equipo[elegido].quitarvida(daño);
                    sin.setLogsJuego("Ataque Especial " + sin.getTux().getNombre() +" Ha realizado: "+daño+" puntos de daño");
                    lblLogs.setText(sin.getLogsJuego());
                }     
               
            }
                String vidaPokemon = Integer.toString((int) equipo[elegido].getVida());
                String    vidaTotal = Integer.toString((int) equipo[elegido].getVidaTotal());
                vida.setText(vidaPokemon +" / "+ vidaTotal);
                ataque.setDisable(false);
                ataqueS.setDisable(false);  
  }
  
  private void AtaqueTux()
  {
      int elegido = 0;
            int daño;
            //empieza el juego
           
                int ataca = (int) (Math.random() * 2);
                if(sin.getTux().getVida() <= 0)
                {
                    AtaqueInicialTux();
                }
                else
                {
                    if(ataca == 0)
                {
                   daño = (int) sin.getTux().getAtaque(equipo[elegido].getDefensa(),equipo[elegido].getTipo());
                   equipo[elegido].quitarvida(daño);
                   sin.setLogsJuego(sin.getTux().getNombre() +" Ha realizado: "+daño+" puntos de daño");
                   Pokemonmuerto();
                   lblLogs.setText(sin.getLogsJuego());
                }
                else
                {
                    daño = (int) sin.getTux().getAtaqueS(equipo[elegido].getDefensaS(),equipo[elegido].getTipo());
                    equipo[elegido].quitarvida(daño);
                    sin.setLogsJuego("Ataque Especial " + sin.getTux().getNombre() +" Ha realizado: "+daño+" puntos de daño");
                    Pokemonmuerto();
                    lblLogs.setText(sin.getLogsJuego());
                }  
                
                String vidaPokemon = Integer.toString((int) equipo[elegido].getVida());
                String    vidaTotal = Integer.toString((int) equipo[elegido].getVidaTotal());
                vida.setText(vidaPokemon +" / "+ vidaTotal);
                }
                

                
  }
  
  private void Pokemonmuerto()
  {
        if(equipo[0].getVida() <= 0)
        {
            ataque.setDisable(true);
            ataqueS.setDisable(true);  
            sin.setLogsJuego( equipo[0].getNombre()+" Ha muerto");
        }
        else
        {
            ataque.setDisable(false);
            ataqueS.setDisable(false);  
        }
  }
  
  private void Tuxmuerto()
  {
        if(sin.getTux().getVida() <= 0)
        {
            ataque.setDisable(true);
            ataqueS.setDisable(true);  
            sin.setLogsJuego( "TUX Ha muerto");
            sin.setLogsJuego("Se genera un nuevo tux :o");
            Tux tux = new Tux(sin.getdif()); 
            sin.setTux(tux);
            String vidaPTux = Double.toString(sin.getTux().getVida());
            String vidaTuxTotal = Double.toString(sin.getTux().getVidaTotal());
            sin.setTuxMuertos();
            String tuxm = Integer.toString(sin.getTuxMuertos());
            tuxmuertos.setText(tuxm);
            vidaTux.setText(vidaPTux +" / "+ vidaTuxTotal);
            
        }
        else
        {
            ataque.setDisable(false);
            ataqueS.setDisable(false);  
        }
  }
  @FXML
  private void cambiarPokemon1() throws SQLException
  {
      if(equipo[1].getVida() <=0)
      {
          sin.setLogsJuego( "POKEMON MUERTO, NO SE PUEDE SELECCIONAR");
      }
      else
      {
        Pokemon guardado = equipo[0];
      equipo[0] = equipo[1];
      equipo[1] = guardado;
      Img();
      datos(); 
      AtaqueInicialTux();  
      }
      
  }
  @FXML
   private void cambiarPokemon2() throws SQLException
  {
      if(equipo[2].getVida() <=0)
      {
          sin.setLogsJuego( "POKEMON MUERTO, NO SE PUEDE SELECCIONAR");
      }
      else
      {
      Pokemon guardado = equipo[0];
      equipo[0] = equipo[2];
      equipo[2] = guardado;
      Img();
      datos(); 
      AtaqueInicialTux();  
      }
  }
   @FXML
    private void cambiarPokemon3() throws SQLException
  {
      if(equipo[3].getVida() <=0)
      {
          sin.setLogsJuego( "POKEMON MUERTO, NO SE PUEDE SELECCIONAR");
      }
      else
      {
      Pokemon guardado = equipo[0];
      equipo[0] = equipo[3];
      equipo[3] = guardado;
      Img();
      datos(); 
      AtaqueInicialTux();        
      }
  }
    @FXML
     private void cambiarPokemon4() throws SQLException
  {
      if(equipo[4].getVida() <=0)
      {
          sin.setLogsJuego( "POKEMON MUERTO, NO SE PUEDE SELECCIONAR");
      }
      else
      {
      Pokemon guardado = equipo[0];
      equipo[0] = equipo[4];
      equipo[4] = guardado;
      Img();
      datos(); 
      AtaqueInicialTux(); 
      }
  }
    @FXML
      private void cambiarPokemon5() throws SQLException
  {
      if(equipo[5].getVida() <=0)
      {
          sin.setLogsJuego( "POKEMON MUERTO, NO SE PUEDE SELECCIONAR");
      }
      else
      {
      Pokemon guardado = equipo[0];
      equipo[0] = equipo[5];
      equipo[5] = guardado;
      Img();
      datos(); 
      AtaqueInicialTux(); 
      }
  }
}
