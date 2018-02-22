/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.event.ActionEvent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;
public class datosDB {

    public static Connection Conn;
    private String[] mensajeError=new String[1];
    private ResultSet rset;
    private ResultSet rsSnacks;
    private HtmlDataTable tabla;
    public  static String sDni;
    //Los siguientes atributos son los del panel de búsquedas
    private String sNombre_Busc;
    private int sgenero_Busc;
    private String sDireccion_Busc;
    private String[] sSnacks_Busc;
    //para mostrar el nº locales encontrados
    private String sinfo_Sel;
    public String sConsulta="Select * from genero_personas ";
    public String sWhere=" ";
    public String sOrden="nombre";
   
  
    
    public datosDB() {
        mensajeError[0]="Todo Bien";
        Conn=MySQL_Util.Conectar("localhost","root", "", "cine2", mensajeError);
        
        
    }
    public void buscar_local(ActionEvent e)
    {
    //Aqui rellenamos el WHERE de la SELECT
        sWhere=" WHERE TRUE";
    if (sNombre_Busc.trim().length()>0)
        {sWhere+= " AND nombre like '%"+sNombre_Busc+"%'";}
    if (sgenero_Busc>0)
        {sWhere+= " AND id ="+sgenero_Busc;}
    if (sDireccion_Busc.trim().length()>0)
        {sWhere+= " AND correo like '%"+sDireccion_Busc+"%'";}
    if (sSnacks_Busc.length>0)
    {sWhere+= " AND   ID IN(SELECT dni_persona FROM piscolabis_personas";
     sWhere+=" WHERE id IN (";
     sWhere+=MySQL_Util.implode(sSnacks_Busc)+ " ))";
     //mensajeError[0]=sConsulta+sWhere;
    }
    }
    public void limpiar_buscar_local(ActionEvent e)
    {
    sNombre_Busc="";
    sgenero_Busc=0;
    sDireccion_Busc="";
    sSnacks_Busc=null;
    sWhere="";
    
    }
    public String[] getMensajeError() {
        return mensajeError;
    }

    /**
     * @param mensajeError the mensajeError to set
     */
    public void setMensajeError(String[] mensajeError) {
        this.mensajeError = mensajeError;
    }
      
    public String concatenar(String[] a){
       String salida="";
       if(a==null)
           return salida;
       for(int i=0;i<a.length;i++){
          salida += a[i]+", ";
       }
       return salida.substring(0, salida.length()-2);
    }

    /**
     * @return the rsocio
     */
    public ResultSet getRset()
    {
        
        rset=MySQL_Util.Sel_Consulta(Conn, sConsulta+sWhere, mensajeError);
        return rset;
    }

    

    /**
     * @return the tabla
     */
    public HtmlDataTable getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }
    public String seleccionLista_Del()
    {
    String borrarencuesta;    
    String sid_borrar=Obtener_ID();
    String borrarfp="DELETE FROM piscolabis_personas WHERE dni_persona="+sid_borrar;
    if (MySQL_Util.Ej_Consulta(Conn, borrarfp))
         {borrarencuesta   ="DELETE FROM personas WHERE dni_persona="+sid_borrar;
          if (MySQL_Util.Ej_Consulta(Conn, borrarencuesta))
             {return "index";}
          else
             {return "error_insertar_local";}
         }
    else
         return "error_insertar_local";
    }
    public String Obtener_ID()
    {
           Map <String,Object> fila;
           String valorDevuelto;
           fila= (Map <String,Object>) tabla.getRowData();
           valorDevuelto=fila.get("dni_persona").toString();
           return valorDevuelto;
    }
    public String insertar_encuesta()
    {
    sDni="";
    return "insertar";
    }
    public String seleccionLista_Mod()
            { sDni=Obtener_ID();
             return "insertar";
            }

    /**
     * @return the sNombre_Busc
     */
    public String getsNombre_Busc() {
        return sNombre_Busc;
    }

    /**
     * @param sNombre_Busc the sNombre_Busc to set
     */
    public void setsNombre_Busc(String sNombre_Busc) {
        this.sNombre_Busc = sNombre_Busc;
    }

    /**
     * @return the szona_Busc
     */
    public int getsgenero_Busc() {
        return sgenero_Busc;
    }

    /**
     * @param szona_Busc the szona_Busc to set
     */
    public void setsgenero_Busc(int szona_Busc) {
        this.sgenero_Busc = szona_Busc;
    }

    /**
     * @return the sDireccion_Busc
     */
    public String getsDireccion_Busc() {
        return sDireccion_Busc;
    }

    /**
     * @param sDireccion_Busc the sDireccion_Busc to set
     */
    public void setsDireccion_Busc(String sDireccion_Busc) {
        this.sDireccion_Busc = sDireccion_Busc;
    }

    /**
     * @return the sfpago_Busc
     */
    public String[] getsSnacks_Busc() {
        return sSnacks_Busc;
    }

    /**
     * @param sfpago_Busc the sfpago_Busc to set
     */
    public void setsSnacks_Busc(String[] sSnacks_Busc) {
        this.sSnacks_Busc = sSnacks_Busc;
    }

    /**
     * @return the sinfo_Sel
     */
    public String getSinfo_Sel() {
        String devuelve="Nº de encuestas encontrados";
        int numero=MySQL_Util.Numero_Registros(rset);
        return devuelve+": "+numero;
    }
    //para el combo de busqueda de zonas
    public ArrayList getListaGenero()
    {
    ArrayList lista=MySQL_Util.Llenar_Lista(Conn, "SELECT id,descripcion FROM genero");
    lista.add(new SelectItem(0,"Seleccione")) ;
    return lista;
    }
    public ArrayList getListaSnacks()
    {
    ArrayList lista=MySQL_Util.Llenar_Lista(Conn, "SELECT id,descripcion FROM piscolabis");
    lista.add(new SelectItem(0,"Seleccione")) ;
    return lista;
    }
    public ResultSet getRsSnacks() {
        try
        {
        String consultaSnacks="SELECT pi.descripcion FROM piscolabis as pi, piscolabis_personas as pp where pi.id = pp.id";
           consultaSnacks+=" and dni_persona="+rset.getString("dni_persona");
        rsSnacks=MySQL_Util.Sel_Consulta(Conn, consultaSnacks, mensajeError);
        }
        catch (Exception e)
         {rsSnacks=null;}
        return rsSnacks; 
    }

    /**
     * @param rsSnacks the rsSnacks to set
     */
    public void setRsSnacks(ResultSet rsSnacks) {
        this.rsSnacks = rsSnacks;
    }
    
    public String gestion_Generos() {

        g_tablas_aux.stabla = "genero";

        g_tablas_aux.stitulo = "Gestión de Generos:";

    return "g_tablas_aux";

    }

    public String gestion_Snacks() {

            g_tablas_aux.stabla = "piscolabis";

            g_tablas_aux.stitulo = "Gestión de Piscolabis:";

    return "g_tablas_aux";

    }
}