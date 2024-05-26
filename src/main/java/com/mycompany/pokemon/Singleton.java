/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * Clase para el intercambio de información entre diferentes ventanas
 * En nombre de la clase puede ser el que se quiera
 */
public class Singleton 
{
   private final static Singleton INSTANCIA = new Singleton();
   
   private Singleton(){}
   //El constructor es privado para evitar que se puedan crear instancias de esta clase
   
   public static Singleton getInstancia()
   {
       return INSTANCIA;
       
   }
   
   //Ahora definimos atributos, getters y setters que queramos usar para intercambiar información
   private String DataBase;
   private String usuario;
   private String logs;
   private String logsJuego;
   private String dif;
   private int entrenadorID;
   private String contrasenya;
   private Pokemon equipo[] = new Pokemon [6];
   private Tux tux;
   private int cantidadVivos;
   private int cantidadTotal;
   private int tuxMuertos;
   
   //se inicializan los datos necesarios
   void Singelton()
   {
       logs = "Iniciando logs";   
       dif = "recluta";
       entrenadorID = 1; 
      
       
   }
   
   void initEquipoNull()
   {
        for(int i=0;i<6;i++)
       {
           equipo[i] = null;
       }
   }
   
   void initLogsJuego()
   {
       logsJuego= "Iniciando Logs de la partida";
   }
   
   void initTuxMuertos()
   {
       tuxMuertos= 0;
   }
   
   void initvivos()
   {
       cantidadVivos = cantidadTotal;
   }
   
       
     
   public void setTux(Tux t)
   {
       tux = t;
   }
   
   public Tux getTux()
   {
       
       return tux;
   }
   
   public void setEntrenadorid(int id)
   {
       entrenadorID = id;
   }
   
   public int getentrenadorid()
   {
       return entrenadorID;
   }
   
   public Pokemon[] getEquipo()
   {
       
       return equipo;
       
   }
   //añade los pokemons del entrenador seleccionado
   public void setEquipoEntrenadores() throws SQLException
   {
       Consultas consultas = new Consultas();
       initEquipoNull();
       //nombre = n, vida = v, ataque = a, ataque especial = as, defensa = d, defensa especial = ds, velocidad = vel, tipo = t y dificultad = dificultad
        int cantidad = getCantidad();
        int f = 0;
        String nombre = "";
        int id = 0;
        double vida = 0;
        double ataque = 0;
        double ataqueS = 0;
        double defensa = 0;
        double defensaS = 0;
        double velocidad = 0;
        int tipo = 0;
        
       
            try 
            {
                List<Map<String, String>> info = consultas.PokemonIDEntrenador(entrenadorID);
                // Iterador para recorrer la lista

                for (Map<String, String> PokemonID : info) 
                {
                    // Iterador para recorrer el mapa de información de cada pokemon
                    for (Map.Entry<String, String> entry : PokemonID.entrySet()) 
                    {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                       
                        try 
                        {
                            int idPokemon = Integer.parseInt(value);
                            List<Map<String, String>> infop = consultas.PokemonCompleto(idPokemon);
                            // Iterador para recorrer la lista
                            for (Map<String, String> pokemonInfo : infop) 
                            {
                                nombre = pokemonInfo.get("Pokemon");
                                id = Integer.parseInt(pokemonInfo.get("ID_Pokemon"));
                                vida =  Double.parseDouble(pokemonInfo.get("HP"));
                                ataque = Double.parseDouble(pokemonInfo.get("Attack"));
                                ataqueS = Double.parseDouble(pokemonInfo.get("Special_Attack"));
                                defensa = Double.parseDouble(pokemonInfo.get("Defense"));
                                defensaS = Double.parseDouble(pokemonInfo.get("Special_Defense"));
                                velocidad = Double.parseDouble(pokemonInfo.get("Speed"));
                                tipo =  consultas.TipoID(pokemonInfo.get("First_Type"));
                                
                                 equipo[f] = new Pokemon(nombre,id,vida,ataque,ataqueS,defensa,defensaS,velocidad,tipo,dif);   
                                 f++;
                                // Iterador para recorrer el mapa de información de cada pokemon
                                   
                            }

                        } 
                        catch (Exception e) 
                        {
                            e.printStackTrace();
                        }

                    }
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
          
       }
   //añade de 1 en 1 en el array a los pokemons
    public void setEquipoCustom(int position, String name) throws SQLException
   {
       
       Consultas consultas = new Consultas();
       //nombre = n, vida = v, ataque = a, ataque especial = as, defensa = d, defensa especial = ds, velocidad = vel, tipo = t y dificultad = dificultad
       
        int f = 0;
        String nombre = "";
        int id = 0;
        double vida = 0;
        double ataque = 0;
        double ataqueS = 0;
        double defensa = 0;
        double defensaS = 0;
        double velocidad = 0;
        int tipo = 0;
 
         try 
                        {
                            String pokemon = consultas.pokemonId(name);
                            int idPokemon = Integer.parseInt(pokemon);
                            List<Map<String, String>> infop = consultas.PokemonCompleto(idPokemon);
                            // Iterador para recorrer la lista
                            for (Map<String, String> pokemonInfo : infop) 
                            {
                                nombre = pokemonInfo.get("Pokemon");
                                id = Integer.parseInt(pokemonInfo.get("ID_Pokemon"));
                                vida =  Double.parseDouble(pokemonInfo.get("HP"));
                                ataque = Double.parseDouble(pokemonInfo.get("Attack"));
                                ataqueS = Double.parseDouble(pokemonInfo.get("Special_Attack"));
                                defensa = Double.parseDouble(pokemonInfo.get("Defense"));
                                defensaS = Double.parseDouble(pokemonInfo.get("Special_Defense"));
                                velocidad = Double.parseDouble(pokemonInfo.get("Speed"));
                                tipo =  consultas.TipoID(pokemonInfo.get("First_Type"));
                                
                                equipo[position] = new Pokemon(nombre,id,vida,ataque,ataqueS,defensa,defensaS,velocidad,tipo,dif);   
                            }
                        } 
                        catch (Exception e) 
                        {
                            e.printStackTrace();
                        }
   }
    //añade a todos los pokemons que has seleccionado
    public void setEquipoCustomCompleto()
    {
        Consultas consultas = new Consultas();
        
        int f = 0;
        String nombre = "";
        int id = 0;
        double vida = 0;
        double ataque = 0;
        double ataqueS = 0;
        double defensa = 0;
        double defensaS = 0;
        double velocidad = 0;
        int tipo = 0;
        for(int i = 0 ; i <6 ; i++)
        {
            if(equipo[i] != null)
            {
                try 
                    {
                        String pokemon = consultas.pokemonId(equipo[i].getNombre());
                        int idPokemon = Integer.parseInt(pokemon);
                        List<Map<String, String>> infop = consultas.PokemonCompleto(idPokemon);
                        // Iterador para recorrer la lista
                        for (Map<String, String> pokemonInfo : infop) 
                        {
                            nombre = pokemonInfo.get("Pokemon");
                            id = Integer.parseInt(pokemonInfo.get("ID_Pokemon"));
                            vida =  Double.parseDouble(pokemonInfo.get("HP"));
                            ataque = Double.parseDouble(pokemonInfo.get("Attack"));
                            ataqueS = Double.parseDouble(pokemonInfo.get("Special_Attack"));
                            defensa = Double.parseDouble(pokemonInfo.get("Defense"));
                            defensaS = Double.parseDouble(pokemonInfo.get("Special_Defense"));
                            velocidad = Double.parseDouble(pokemonInfo.get("Speed"));
                            tipo =  consultas.TipoID(pokemonInfo.get("First_Type"));

                            equipo[i] = new Pokemon(nombre,id,vida,ataque,ataqueS,defensa,defensaS,velocidad,tipo,dif);   
                        }
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
            
        
    }
    

   public void setDataBase(String d)
   {
       DataBase = d;
   }
   
   public String getDatabase()
   {
       return DataBase;
   }
 
   public void setdif(String d)
   {
       dif = d;
   }
   
   public String getdif()
   {
       return dif;
   }
   
   public void setCantidad(int d)
   {
       cantidadTotal = d;
   }
   
   public int getCantidad()
   {
       return cantidadTotal;
   }
   
    public void setCantidadVivos()
   {
       cantidadVivos = cantidadVivos -1;
   }
   
   public int getCantidadVivos()
   {
       return cantidadVivos;
   }
   
    public void setTuxMuertos()
   {
       tuxMuertos = tuxMuertos +1;
   }
   
   public int getTuxMuertos()
   {
       return tuxMuertos;
   }
   
   
   
   public void setUsuario(String d)
   {
       usuario = d;
   }
   
   public String getUsuario()
   {
       return usuario;
   }
   
   public String getLogs()
   {
       return logs;
   }
   
   public void setLogs(String d)
   {
       
       logs = logs + " \n" + d;
   }
   
   public String getLogsJuego()
   {
       return logsJuego;
   }
   
   public void setLogsJuego(String d)
   {
       
       logsJuego = d + " \n" + logsJuego;
   }
   
   
   
   public void setContrasenya(String d)
   {
       contrasenya = d;
   }
   
   public String getContrasenya()
   {
       return contrasenya;
   }
}
