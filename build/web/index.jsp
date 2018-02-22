<%-- 
    Document   : index
    Created on : 19-feb-2018, 8:47:03
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
            <title>JSP Page</title>
              <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
            <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
             <link rel="stylesheet" type="text/css" href="theme.css"> 
        </head>
        <body>
            <center>
             <div id="capa1">
                <h2><h:outputText value="Practica 02 Libre configuracion"/></h2>
             </div>
             <br>
            <h:form>
            <h1><h:outputText value="Encuesta sobre cines"/></h1>
             <h:panelGrid columns="4" border="1">
                    <f:facet name="header">
                        <h:outputText style="font-size:25px" value="Filtrado de datos de cines de Sevilla"/>
                    </f:facet>
                    <h:outputText value="Nombre:" />
                    <h:inputText value="#{datosDB.sNombre_Busc}" />
                    <h:outputText value="Genero" />
                    <h:selectOneMenu value="#{datosDB.sgenero_Busc}">
                        <f:selectItems value="#{datosDB.listaGenero}" />
                    </h:selectOneMenu>
                     <h:outputText value="Snacks:" />
                     <h:selectManyListbox value="#{datosDB.sSnacks_Busc}">
                         <f:selectItems value="#{datosDB.listaSnacks}" />
                    </h:selectManyListbox>
                     <h:outputText value="Correo:" />
                     <h:inputText value="#{datosDB.sDireccion_Busc}" />
                     <h:commandButton value="Buscar" actionListener="#{datosDB.buscar_local}"/>
                     <h:outputText value="" />
                     <h:outputText value="" />
                      <h:commandButton value="Limpiar Búsqueda" actionListener="#{datosDB.limpiar_buscar_local}"/>
                </h:panelGrid>
				
            <br>
           <h:dataTable border="1" value="#{datosDB.rset}" var="fila" binding="#{datosDB.tabla}">
                <%--<h:column>
                    <f:facet name="header">
                        <h:outputText value="Dni"/>
                    </f:facet>
                    <h:outputText value="#{fila.dni_persona}"/>
                </h:column>--%>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Nombre"/>
                    </f:facet>
                    <h:outputText value="#{fila.nombre}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Frecuente"/>
                    </f:facet>
                    <h:outputText value="#{fila.frecuente}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Correo"/>
                    </f:facet>
                    <h:outputText value="#{fila.correo}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Genero"/>
                    </f:facet>
                    <h:outputText value="#{fila.descripcion}"/>
                </h:column>
                 <h:column>
                    <f:facet name="header">
                        <h:outputText value="Snacks"/>
                    </f:facet>
                     <h:dataTable value="#{datosDB.rsSnacks}" var="valor">
                        <h:column>
                             <h:outputText value="#{valor.descripcion}"/>
                        </h:column>
                    </h:dataTable>
                </h:column>
                <h:column>
                        <f:facet name="header">
                            <h:outputText value="SUPR"/>
                        </f:facet>
                        <h:commandButton value="X" action="#{datosDB.seleccionLista_Del}"/>
                </h:column>
                <h:column>
                        <f:facet name="header">
                            <h:outputText value="MOD"/>
                        </f:facet>
                        <h:commandButton value="X"  action="#{datosDB.seleccionLista_Mod}"                        
                             />  
                </h:column>
            </h:dataTable>
            <h:commandButton value="Añadir encuesta" action="#{datosDB.insertar_encuesta}"/>
            <h:commandButton value="Gestión generos" action="#{datosDB.gestion_Generos}"/>
            <h:commandButton value="Gestión Snacks" action="#{datosDB.gestion_Snacks}"/>
            </h:form> 
          </center>
             <br>
             <hr>
             <p style="font-family: Arial;">2º Daw - Ivan Hernando Ortega</p>
        </body>
    </html>
</f:view>
