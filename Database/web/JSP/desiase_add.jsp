<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ page import="java.util.List" %>
<%@ page import="models.Desiase" %>
<%@ page import="models.Symptoms" %>
<%@ page import="models.Procedures" %>
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
    <link href="../Views/css/bootstrap-slider.css" rel="stylesheet" media="screen">
    <style>
        #ex1Slider .slider-selection {
            background: #BABABA;
        }
    </style>
</head>
<body class="bb">
<ul>
    <li class="lileft"><a href="/desiases">Болезни</a></li>
    <li class="lileft"><a href="/drugs">Лекарства</a></li>
    <li class="lileft"><a href="/procedures">Процедуры</a></li>
    <li class="lileft"><a href="/symptoms">Симптомы</a></li>
    <li class="liright"><a href="/desiaseadd">Добавить болезнь</a></li>
</ul>
<div class="container">
    <div class="large-offset-3 large-6 columns">
        <br>
        <% if ((String) request.getAttribute("error") != null) {%>
        <div class="alert alert-warning">
            <center><strong>Ошибка!</strong> <%=(String) request.getAttribute("error")%>
            </center>
        </div>
        <%}%>
        <form class="form-signin" id="login-form" method="post">
            <h1 class="noneback">
                <center>Добавление болезни</center>
            </h1>
            <input class="input-block-level" id="name-field" name="name" type="text"
                   placeholder="Введите название болезни" required/></td></tr>
            <input class="input-block-level" id="age-field" name="average_age" type="text"
                   placeholder="Введите средний возраст больного" required/></td></tr>
            <input class="input-block-level" id="man-field" name="chance_desiase_man" type="text"
                   placeholder="Введите вероятность заболевания мужчины" required/></td></tr>
            <input class="input-block-level" id="women-field" name="chance_desiase_women" type="text"
                   placeholder="Введите вероятность заболевания женщины" required/></td></tr>
            <br>
            <div class="row">
                <div class="col-sm-4"><b>Симптомы</b></div>
                <div class="col-sm-4"><b>Процедуры</b></div>
                <div class="col-sm-4"><b>Лекарства</b></div>
                <div class="col-sm-4">
                    <%
                        List<Symptoms> symptoms = (List) request.getAttribute("symptoms");
                        for (int i = 0; i < symptoms.size(); i++) {
                    %>
                    <label><input type="checkbox" name="symptoms2" value="<%=symptoms.get(i).getId()%>"><a
                            href="/symptom/<%=symptoms.get(i).getId()%>"
                            target="_blank"><b><%=symptoms.get(i).getName()%>
                    </b></a></label><br>
                    <%}%></div>
                <div class="col-sm-4">
                    <%
                        List<Procedures> procedures = (List) request.getAttribute("procedures");
                        for (int i = 0; i < procedures.size(); i++) {
                    %>
                    <label><input type="checkbox" name="procedures2" value="<%=procedures.get(i).getId()%>"><a
                            href="/procedure/<%=procedures.get(i).getId()%>"
                            target="_blank"><b><%=procedures.get(i).getName()%>
                    </b></a></label><br>
                    <%}%></div>
                <div class="col-sm-4">
                    <%
                        List<Drug> drugs = (List) request.getAttribute("drugs");
                        for (int i = 0; i < drugs.size(); i++) {
                    %>
                    <label><a
                            href="/drug/<%=drugs.get(i).getId()%>" target="_blank" title="Количество на складе: <%=drugs.get(i).getQuantity()%>"><b><%=drugs.get(i).getName()%>
                    </b></a></label>
                    <input class="input-block-level" name="<%=drugs.get(i).getName()%>" type="text"
                           value="0">
                    <%}%></div>
            </div>

            <center><input class="button" type="submit" value="Добавить болезнь"></center>
        </form>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script src="//code.jquery.com/jquery-1.12.3.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script src="../Views/js/bootstrap-slider.js"></script>
</body>
</html>