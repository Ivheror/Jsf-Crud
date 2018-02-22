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
import javax.faces.model.SelectItem;


public class MySQL_Util {
   //Driver para MySql
    static String sdriver="com.mysql.jdbc.Driver";

// Objeto connection
    static Connection conn; 
 /*
 * Voy a hacer dos métodos de conexión, uno que recibe un array 
 * de un elemento para registrar el posible error y otro que no
 * recibe ningún elemento para registrar el error.
  *
 * 
 */   
public static Connection Conectar(String sHost, String sUsuario, String sClave, String sBD)
{
return Conectar(sHost,sUsuario,sClave,sBD,null);
}
public static Connection Conectar(String sHost, String sUsuario, String sClave, String sBD, String[] sRes)

{
 //Url de Jdbc (base de datos:ejemplos)
    String sUrl_Jdbc="jdbc:mysql://"+sHost+":3306/"+sBD;   
//Registrar el driver  MySql
 try
      {Class.forName(sdriver);}
  catch (Exception ex)
  {    
  if (sRes!=null)
    sRes[0]="error al seleccionar el driver "+ex.toString();
  }
// Conectar BD
try
{
conn=DriverManager.getConnection(sUrl_Jdbc,sUsuario,sClave);}
catch (Exception ex)
{
  if (sRes!=null)  
    sRes[0]="error al crear el objeto connection "+ex.toString();
}
  return conn;
}
/*
 * Voy a hacer dos métodos para ejecutar una SELECT, uno que recibe un array 
 * de un elemento para registrar el posible error y otro que no
 * recibe ningún elemento para registrar el error.
 * 
 */
public static ResultSet Sel_Consulta(Connection conn,String sConsulta)
{
return Sel_Consulta(conn, sConsulta,null);
}

public static ResultSet Sel_Consulta(Connection conn,String sConsulta, String[] sRes)
{
ResultSet rs=null;
Statement sentencia;
try
{
    sentencia=conn.createStatement();
    rs=sentencia.executeQuery(sConsulta);
}
catch (Exception e)
{
   if (sRes!=null)
      // sRes[0]=e.toString();
       sRes[0]=sConsulta;
}
return rs;
}
public static ArrayList Llenar_Lista(Connection conn, String consulta)
{
    ArrayList lista=new ArrayList();
    try
    {
    Statement sentencia=conn.createStatement();
    ResultSet rslista=sentencia.executeQuery(consulta);
    while (rslista.next())
    {
        lista.add(new SelectItem(rslista.getString("id"),rslista.getString("descripcion")) );
    }
    }
    catch (Exception e)
    {
        lista=null;
    }
    return lista;
}
/*metodo para ejecutar una consulta de accion (UPDATE,DELETE,INSERT*/
public static boolean Ej_Consulta(Connection conn,String sConsulta)
{
try
    {
    Statement ejSentencia=conn.createStatement();
    ejSentencia.executeUpdate(sConsulta);
    return true;
    }
catch (Exception e)
{
    return false;
}
}
/* El siguiente método ejecuta una Insert en una tabla con PK autonumérica
y además devuelve el valor de la PK de la fila introducida*/
public static int Ej_Consulta_ID_Auto(Connection conn,String sConsulta)
{
    try
    {
    Statement ejSentencia=conn.createStatement();
    ejSentencia.executeUpdate(sConsulta);
    /*obtener el id del registro añadido*/
    String sObtener="SELECT LAST_INSERT_ID()";
    ResultSet rsObtener=Sel_Consulta(conn,sObtener);
    rsObtener.next();
    int n=rsObtener.getInt(1);
    /* hasta aquí
    ejSentencia.executeUpdate("INSERT INTO LOCALES_FORMAS_PAGO VALUES("+n+","+1+")");
            */
    return n;
    }
    catch (Exception ex)
    {
     return -1;
    }
}
public static boolean Ej_Consulta_Trans(Connection conn,String[] sConsultas)
{
    try
    {
        /*Recuperamos el estado anterior del autocommit*/
        boolean estadoAnterior=conn.getAutoCommit();
        /* lo ponemos a false */
        conn.setAutoCommit(false);               
        Statement ejSentencia=conn.createStatement();
        for (int i=0;i< sConsultas.length;i++)
        {
           ejSentencia.executeUpdate(sConsultas[i]);
        }
        conn.commit();
        return true;      
        
    }
    catch (Exception e)
    {
        try
        {
            sConsultas[0]="Error tipo"+e.toString();
            conn.rollback();
        }
        catch (Exception e2)
        {
            sConsultas[0]="Error tipo"+e2.toString();
        }
        return false;
    }
}
/* el siguiente método recibe un resultset y un nombre de columna
y devuelve el contenido de la columna
*/
public static String Leer_Campo(ResultSet rs, String nombreCampo)
{
try
{rs.first();
return rs.getString(nombreCampo);
}

catch (Exception e)
{
return e.toString();
}

        }
/*El siguiente método recibe una conexión, una select y un nombre de columna
y devuelve un array
*/
public static String[] Llenar_Seleccionados(Connection conn, String sconsulta, String sNombre)
{
String[] tabla=null;
int i=0;
try
{
Statement sentencia=conn.createStatement();
ResultSet rslista=sentencia.executeQuery(sconsulta);
/* si hay filas devueltas por la SElect declaro un array con tantos elementos
como filas devuelva la SELECT*/
if (rslista.last())
{ tabla=new String[rslista.getRow()];}
/* Cargo el array para ello en primer lugar me posiciono en la primera fila
devuelta por la SELECT*/
rslista.first();
do
{
  tabla[i]=rslista.getString(sNombre);
  i++;
}while (rslista.next());
rslista.close();
return tabla;
}
catch (Exception e)
        {return null;}

}
public static String Leer_campo2(ResultSet rs,String snombreCampo)
{
try
{

return rs.getString(snombreCampo);
}
catch(Exception e)

{
return e.toString();
}
}
public static String implode(String[] sc)
 {
  String devuelve="";
  boolean primera=true;
  for (int i=0;i<sc.length;i++)
  { 
      if (primera)
      {
      primera=false;
      devuelve=devuelve+sc[i];
      }
      else
      {
      devuelve=devuelve+","+sc[i];
      }
        
 }
  return devuelve; 
 }
public static int Numero_Registros(ResultSet rs)
{
    int devuelve;
try
{
 if (rs.last())
     devuelve=rs.getRow();
 else
     devuelve=0;
 return devuelve;    
}
catch (Exception e)
{
return -1;
}
}
}


    


