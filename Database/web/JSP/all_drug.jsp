<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ page import="java.util.List" %>
<%@ page import="models.Desiase" %>
<%@ page import="models.Symptoms" %>
<%@ page import="models.Drug" %>
    <%Symptoms symptom = (Symptoms) request.getAttribute("symptom");%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Поликлиника</title>
    <link rel="stylesheet" type="text/css" href="../Views/css/bootstrap.css">
    <link href="../Views/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../Views/css/jasny-bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css"/>
    <link href="../Views/css/my-styles.css" rel="stylesheet" media="screen">
</head>
<body class="bb">
<ul>
    <li class="lileft"><a href="/desiases">Болезни</a></li>
    <li class="lileft"><a href="/drugs">Лекарства</a></li>
    <li class="lileft"><a href="/procedures">Процедуры</a></li>
    <li class="lileft"><a href="/symptoms">Симптомы</a></li>
    <li class="liright"><a href="/drugadd">Добавить лекарство</a></li>
</ul>
<center><div class="container">
    <h1 class="back"><center >Список всех лекарств</center></h1>
    <% if ((String) request.getAttribute("text")!=null) {%>
    <div class="alert alert-info">
        <center><strong>Отлично!</strong> <%=(String) request.getAttribute("text")%></center></div>
    <%}%>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-striped table-bordered" id="table2"><thead>
            <tr>
                <th>Id:</th>
                <th>Название:</th>
                <th>Форма:</th>
                <th>Противопоказания:</th>
                <th>Передозировка:</th>
                <th>Количество на складе:</th>
                <th>Побочные эффекты:</th>
                <th>Заменители:</th>
            </tr></thead>
                <tbody><%
                    List<Drug> drugs = (List) request.getAttribute("drugs");
                    for (int i = 0; i< drugs.size(); i++) {
                %>
                <tr>
                    <td>
                        <%=drugs.get(i).getId()%>
                    </td>
                    <td><center><b>
                        <a href="/drug/<%=drugs.get(i).getId()%>"><%=drugs.get(i).getName()%></a>
                    </b></center></td>
                    <td>
                        <%=drugs.get(i).getForm()%>
                    </td>
                    <td>
                        <%=drugs.get(i).getContraindications()%>
                    </td>
                    <td>
                        <%=drugs.get(i).getOverdose()%>
                    </td>
                    <td>
                        <%=drugs.get(i).getQuantity()%>
                    </td>
                    <td><b>
                        <center>
                            <% for (int k = 0; k < drugs.get(i).getSide_effects().size(); k++) {%>
                            <%=drugs.get(i).getSide_effects().get(k).getName()%>
                            <br><%}%>
                        </center>
                    </b></td>
                    <td><b>
                        <center>
                            <% for (int j = 0; j < drugs.get(i).getSubstitutes().size(); j++) {%>
                            <a href="/drug/<%=drugs.get(i).getSubstitutes().get(j).getDrug().getId()%>" title="<%=drugs.get(i).getSubstitutes().get(j).getDistinction()%>"><%=drugs.get(i).getSubstitutes().get(j).getDrug().getName()%>
                            </a><br><%}%>
                        </center>
                    </b></td>
                </tr>
                <%}%></tbody>
            </table></div></div>
</div></center>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script src="//code.jquery.com/jquery-1.12.3.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
</body>
</html>