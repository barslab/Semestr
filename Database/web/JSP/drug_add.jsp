<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ page import="java.util.List" %>
<%@ page import="javafx.geometry.Side" %>
<%@ page import="models.*" %>
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
                <center>Добавление лекарства</center>
            </h1>
            <input class="input-block-level" id="name-field" name="name" type="text"
                   placeholder="Введите название лекарства" required/></td></tr>
            <input class="input-block-level" id="quantity-field" name="quantity" type="text"
                   placeholder="Введите количество поставляемого лекарства на склад" required/></td></tr>
            <div class="form-group">
                <select class="input-block-level form-control" id="form" name="form">
                    <option value="мазь">Мазь</option>
                    <option value="жидкость">Жидкость</option>
                    <option value="таблетки">Таблетки</option>
                </select>
            </div>
            <input class="input-block-level" id="overdose-field" name="overdose" type="text"
                   placeholder="Введите передозировку" required/>
            <textarea class="form-control" rows="5" name="contraindications" placeholder="Введите противопоказания" required/></textarea>
            <br>
            <div class="row">
                <div class="col-sm-6"><b>Побочные эффекты</b></div>
                <div class="col-sm-6"><b>Заменители</b></div>
                <div class="col-sm-6">
                    <%
                        List<SideEffects> side_effects = (List) request.getAttribute("side_effects");
                        for (int i = 0; i < side_effects.size(); i++) {
                    %>
                    <label><input type="checkbox" name="side_effects2" value="<%=side_effects.get(i).getId()%>">
                        <b><%=side_effects.get(i).getName()%>
                    </b></label><br>
                    <%}%></div>
                <div class="col-sm-6">
                    <%
                        List<Drug> drugs = (List) request.getAttribute("drugs");
                        for (int i = 0; i < side_effects.size(); i++) {
                    %>
                    <label><input type="checkbox" name="drugs2" value="<%=drugs.get(i).getId()%>"><a href="/drug/<%=drugs.get(i).getId()%>" target="_blank">
                            <b><%=drugs.get(i).getName()%></b>
                    </a></label><br>
                    <%}%></div>
            </div><br>
            <center><input class="button" type="submit" value="Добавить лекарство"></center>
        </form>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script src="//code.jquery.com/jquery-1.12.3.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
</body>
</html>