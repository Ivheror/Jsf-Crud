<%-- 
    Document   : a_encuesta
    Created on : 19-feb-2018, 8:51:51
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
            <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
             <link rel="stylesheet" type="text/css" href="theme.css"> 
            <title><h:outputText value="#{locales.setiquetaTitulo}"/></title>
        </head>
        <body>
            <center>
            <h1><h:outputText value="#{personas.setiquetaTitulo}" /></h1>   
            <h:form>
                <table border="1">
                    <tr>
                        <td>Dni:</td>
                        <td><h:inputText value="#{personas.dni}"/></td>
                    </tr>
                    <tr>
                        <td>Nombre:</td>
                        <td><h:inputText value="#{personas.nombre}"/></td>
                    </tr>
                    <tr>
                        <td>Frecuente:</td>
                        <td><h:selectOneRadio value="#{personas.frecuente}">
                                <f:selectItem itemValue = "S" itemLabel = "Si"/>
                                <f:selectItem itemValue = "N" itemLabel = "No"/>
                            </h:selectOneRadio></td>
                    </tr>
                    <tr>
                        <td>Correo:</td>
                        <td><h:inputText value="#{personas.correo}"/></td>
                    </tr>
                    <tr>
                        <td>Genero:</td>
                        <td><h:selectOneMenu value="#{personas.genero}" >
                                <f:selectItems value="#{personas.listaGenero}"/>  <!-- hay que generar la clase de java -->
                            </h:selectOneMenu></td>
                    </tr>
                     <tr>
                        <td>Snacks:</td>
                        <td><h:selectManyListbox value="#{personas.snacks}" >
                                <f:selectItems value="#{personas.listaSnacks}" />
                            </h:selectManyListbox>
                        </td>
                    </tr>
                </table>
                        <h:commandButton value="#{personas.setiquetaBoton}" action="#{personas.guardar_encuesta()}"/>
               <h:outputText value="#{personas.mensajeError}"/>
                </center>
            </h:form>
        </body>
    </html>
</f:view>
