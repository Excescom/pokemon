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
   private String dif;
   private int entrenadorID;
   private String contrasenya;
   private Pokemon equipo[] = new Pokemon [6];
   private Pokemon tux;
   
   void Singelton() throws SQLException
   {
       logs = "Iniciando logs";
       dif = "recluta";
       entrenadorID = 1;
       setEquipoEntrenadores();
   }
   
   public Pokemon getTux()
   {
       tux = new Pokemon("tux", 0, 0, 0, 0,0, 0, 0 ,0, dif); 
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
   
   public void setEquipoEntrenadores() throws SQLException
   {
       Consultas consultas = new Consultas();
       //nombre = n, vida = v, ataque = a, ataque especial = as, defensa = d, defensa especial = ds, velocidad = vel, tipo = t y dificultad = dificultad
       int cantidad = consultas.CantidadPokemon(entrenadorID);
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
                            for (Map<String, String> pokemonInfo : info) 
                            {
                                // Iterador para recorrer el mapa de información de cada pokemon
                                for (Map.Entry<String, String> entryP : pokemonInfo.entrySet()) 
                                {
                                    String keyp = entryP.getKey();
                                    String valuep = entryP.getValue();
                                    // Aquí puedes hacer lo que necesites con la clave (key) y el valor (value)
                                    //este es el orden para crear el pokemon
                                    //String n, int i, double v, double a, double as, double d, double ds, double vel , int t, String dificultad
                                    //nombre = n, vida = v, ataque = a, ataque especial = as, defensa = d, defensa especial = ds, velocidad = vel, tipo = t y dificultad = dificultad

                                    if(keyp == "Pokemon")
                                    {
                                        nombre = valuep;
                                    }
                                    else if(keyp == "ID_Pokemon")
                                    {
                                        int numero = Integer.parseInt(valuep);
                                        id = numero;
                                    }
                                    else if(keyp == "HP")
                                    {
                                         double numero = Double.parseDouble(valuep);
                                         vida = numero;
                                    }
                                    else if(keyp == "Attack")
                                    {
                                         double numero = Double.parseDouble(valuep);
                                         ataque = numero;
                                    }
                                    else if(keyp == "Special_Attack")
                                    {
                                         double numero = Double.parseDouble(valuep);
                                         ataqueS = numero;
                                    }
                                    else if(keyp == "Defense")
                                    {
                                         double numero = Double.parseDouble(valuep);
                                         defensa = numero;
                                    }
                                    else if(keyp == "Special_Defense")
                                    {
                                         double numero = Double.parseDouble(valuep);
                                         defensaS = numero;
                                    }
                                    else if(keyp == "Speed")
                                    {
                                         double numero = Double.parseDouble(valuep);
                                         velocidad = numero;
                                    }
                                    else if(keyp == "First_Type")
                                    {
                                       int  numero = consultas.TipoID(valuep);
                                       tipo = numero;
                                    }
                                   
                                }   
                            }
                            equipo[f] = new Pokemon(nombre,id,vida,ataque,ataqueS,defensa,defensaS,velocidad,tipo,dif);   
                            f++;

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
          for(int i = 0; i<6;i++)   
          {
              System.out.println(equipo[i]);
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
   
   
   
   public void setContrasenya(String d)
   {
       contrasenya = d;
   }
   
   public String getContrasenya()
   {
       return contrasenya;
   }
}
