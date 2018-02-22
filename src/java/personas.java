/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Qatar
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
public class personas 
{
    private String dni;
    private String nombre;
    private String correo;
    private String frecuente;
    private String[] snacks;
    private String genero;
    private String mensajeError;
    private String setiquetaTitulo="Añadir nueva encuesta";
    private String setiquetaBoton="Añadir encuesta";
    
    
    public personas() 
    {
        if (!datosDB.sDni.equals("")) {
            recoge_datos(datosDB.sDni);
        }
        
    }
    public final void recoge_datos(String sDni) {
        setiquetaTitulo = "Modificación de encuesta";
        setiquetaBoton = "Modificar encuesta";
        String sc = "SELECT * FROM personas WHERE dni_persona=" + sDni;
        ResultSet rs = MySQL_Util.Sel_Consulta(datosDB.Conn, sc);
        dni= MySQL_Util.Leer_Campo(rs, "dni_persona");
        nombre = MySQL_Util.Leer_Campo(rs, "nombre");
        correo = MySQL_Util.Leer_Campo(rs, "correo");
        frecuente = MySQL_Util.Leer_Campo(rs, "frecuente");
        genero = MySQL_Util.Leer_Campo(rs, "cod_peli");
        /*ahora obtendremos las formas de pago, para ello escribiremos
         en MySQL_Util un método Llenar_Seleccionados que nos devolverá un
         array con las formas de pago del local seleccionado
         */
        sc = "SELECT id FROM piscolabis_personas WHERE dni_persona=";
        sc += sDni;
        snacks = MySQL_Util.Llenar_Seleccionados(datosDB.Conn, sc, "id");
    }
    
    
    
    public ArrayList getListaGenero()
    {
    return(MySQL_Util.Llenar_Lista(datosDB.Conn, "select id,descripcion from genero"));
    }
    
    public ArrayList getListaSnacks()
    {
    return(MySQL_Util.Llenar_Lista(datosDB.Conn, "select id,descripcion from piscolabis"));
    }
    public String guardar_encuesta()
    {
        boolean bRes = false;
        if (datosDB.sDni.equals("")) /*Si estoy añadiendo un local*/ {
            bRes = insertar_encuesta();
        } else /* estoy modificando un local */ {
            bRes = actualizar_encuesta();
        }
        if (bRes) {
            return "index";
            //return "";
        } else {
            return "error_insertar_encuesta";
            //return "";
        }
    }
    
    public boolean insertar_encuesta() 
    {
        String cad="INSERT INTO PERSONAS VALUES(" + dni + ",'" + nombre + "','" + correo + "','" + frecuente + "'," + genero + ")";
        int n = MySQL_Util.Ej_Consulta_ID_Auto(datosDB.Conn, cad);
        String[] sInserts = new String[snacks.length];
        for (int i = 0; i < snacks.length; i++) {
            sInserts[i] = "INSERT INTO piscolabis_personas VALUES(" + snacks[i] + "," + dni + ")";
        }

        return MySQL_Util.Ej_Consulta_Trans(datosDB.Conn, sInserts); 
    }
    
    public boolean actualizar_encuesta() {
        //mensajeError="";
        boolean devuelve;
        String[] cadDML = new String[snacks.length + 1];
        String cadUpdate = "UPDATE personas SET nombre='" + nombre + "',correo='" + correo + "',";
        cadUpdate += "frecuente='" + frecuente +"',cod_peli=" + genero + " WHERE dni_persona=" + datosDB.sDni;
        if (MySQL_Util.Ej_Consulta(datosDB.Conn, cadUpdate)) { //Para borrar las formas de pago que tenía el local
            cadDML[0] = "DELETE FROM piscolabis_personas WHERE dni_persona=" + datosDB.sDni;
            for (int i = 0; i < snacks.length; i++) {
                cadDML[i + 1] = "INSERT INTO piscolabis_personas VALUES(" + snacks[i] + "," + datosDB.sDni + ")";
                //mensajeError+=cadDML[i+1]+" ";
            }
            devuelve = MySQL_Util.Ej_Consulta_Trans(datosDB.Conn, cadDML);
        } else {
            devuelve = false;
        }
        //mensajeError=cadUpdate;
        return devuelve;
    }
    
    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }


    /**
     * @return the snacks
     */
    public String[] getSnacks() {
        return snacks;
    }
    
    public void setSnacks(String[] snacks) {
        this.snacks = snacks;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the mensajeError
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * @param mensajeError the mensajeError to set
     */
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    /**
     * @return the setiquetaTitulo
     */
    public String getSetiquetaTitulo() {
        return setiquetaTitulo;
    }

    /**
     * @return the setiquetaBoton
     */
    public String getSetiquetaBoton() {
        return setiquetaBoton;
    }

    /**
     * @return the frecuente
     */
    public String getFrecuente() {
        return frecuente;
    }

    /**
     * @param frecuente the frecuente to set
     */
    public void setFrecuente(String frecuente) {
        this.frecuente = frecuente;
    }

    

   
}
