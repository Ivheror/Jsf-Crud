<%-- 
    Document   : g_tablas_aux
    Created on : 19-feb-2018, 23:57:10
    Author     : Qatar
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
<title><h:outputText value="#{g_tablas_aux.stitulo}"/></title>
</head>
<body>
<center>
<h1><h:outputText value="#{g_tablas_aux.stitulo}"/></h1>
<h:form>
<h:dataTable border="3" value="#{g_tablas_aux.rsTabla}" binding="#{g_tablas_aux.tabla}"
             var="reg">
    <h:column>
        <f:facet name="header">
            <h:outputText value="Borrar"/>
        </f:facet>
        <h:commandButton value="X" onclick="return confirm('¿Deseas BORRAR esa fila?')"
                         actionListener="#{g_tablas_aux.borrar_Fila}" />
    </h:column>
    <h:column>
        <f:facet name="header">
            <h:outputText value="Id"/>
        </f:facet>
        <h:outputText value="#{reg.ID}"/>
    </h:column>
    <h:column>
        <f:facet name="header">
            <h:outputText value="Nombre"/>
        </f:facet>
        <h:outputText value="#{g_tablas_aux.iterar_Nombre}"/>
        <h:inputText id="mod" size="45" binding="#{g_tablas_aux.nombre_mod}" 
                     required="true" 
                     requiredMessage="No puedes Modificar a Vacio">

        </h:inputText>
        <h:message for="mod" style="color:red"/>
        </h:column>
    <h:column>
        <f:facet name="header">
            <h:outputText value="Modificación"/>
        </f:facet>
        <h:commandButton value="Modificar" 
                         actionListener="#{g_tablas_aux.modificar_Fila}"/>
    </h:column>
</h:dataTable>
</h:form>
<br>
<h:form>
<table border="3">
<tr>
<td> <h:commandButton value="Añadir" actionListener="#{g_tablas_aux.insertar_Fila}"/></td>
<td>
<h:inputText id="nuevo" size="44" value="#{g_tablas_aux.descripcion}" 
             required="true" requiredMessage="No puedes Añadir Vacio">
<f:validateLength minimum="2"/>
</h:inputText>
<h:message for="nuevo" style="color:red"/>
</td>
</tr>
</table>
<br><br>
<h:commandButton value="<-- Volver" action="index" immediate="true"/>
</h:form>
</center>
</body>
</html>
</f:view>

