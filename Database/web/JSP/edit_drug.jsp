<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="java.util.LinkedList" %>
    <%Drug drug = (Drug) request.getAttribute("drug");%>
    <%List<Drug> substitutes_drug = new LinkedList<Drug>();%>
    <%substitutes_drug = (List) request.getAttribute("substitutes_drug");%>
    <%List<Drug> other_drug = (List) request.getAttribute("other_drug");%>
    <%List<SideEffects> side_drug = (List) request.getAttribute("side_drug");%>
    <%List<SideEffects> other_side = (List) request.getAttribute("other_side");%>
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
<nav class="navmenu navmenu-default navmenu-fixed-left offcanvas" role="navigation">
    <a class="navmenu-brand" href="/desiase">Поиск по болезням</a>
    <a class="navmenu-brand" href="/lksetting">Настройки личного кабинта</a>
    <a class="navmenu-brand" href="/patients">Все ваши пациенты</a>
    <a class="navmenu-brand" href="/activepatients">Все невыписанные пациенты</a>
    <a class="navmenu-brand" href="/notactibepatients">Все выписанные пациенты</a>
    <a class="navmenu-brand" href="/allpatients">Все пациенты поликлиники</a>
    <a class="navmenu-brand" href="/logout">Выйти из аккаунта</a>
</nav>
<div class="navbar navbar-default navbar-fixed-top">
    <button type="button" class="navbar-toggle" data-toggle="offcanvas" data-target=".navmenu" data-canvas="body">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
</div>
<ul>
    <li class="lileft"><a class="active" href=""> </a></li>
    <li class="lileft"><a href="/desiases">Болезни</a></li>
    <li class="lileft"><a href="/drugs">Лекарства</a></li>
    <li class="lileft"><a href="/procedures">Процедуры</a></li>
    <li class="lileft"><a href="/symptoms">Симптомы</a></li>
    <li class="liright"><a href="/drugadd">Добавить лекарство</a></li>
    <li class="liright"><a href="/drugedit/<%=drug.getId()%>">Изменить лекарство</a></li>
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
                <center>Редактирование лекарства</center>
            </h1>
            <input class="input-block-level" id="name-field" name="name" type="text"
                   placeholder="Введите новое название лекарства" value="<%=drug.getName()%>" required/></td></tr>
            <input class="input-block-level" id="quantity-field" name="quantity" type="text"
                   placeholder="Введите новое количество лекарства на складе" value="<%=drug.getQuantity()%>" required/></td></tr>
            <div class="form-group">
                <select class="input-block-level form-control" id="form" name="form">
                    <option value="maz">Мазь</option>
                    <option value="jidkost">Жидкость</option>
                    <option value="tabletki">Таблетки</option>
                </select>
            </div>
            <input class="input-block-level" id="overdose-field" name="overdose" type="text"
                   placeholder="Введите новую передозировку" value="<%=drug.getOverdose()%>" required/>
            <textarea class="form-control" rows="5" name="contraindications" placeholder="Введите новые противопоказания"required/><%=drug.getContraindications()%></textarea>
            <br>
            <br>
            <div class="row">
                <div class="col-sm-6"><b>Побочные эффекты</b></div>
                <div class="col-sm-6"><b>Заменители</b></div>
                <div class="col-sm-6">
                    <%
                        for (int i = 0; i < side_drug.size(); i++) {
                    %>
                    <label><input type="checkbox" name="side_effects2" value="<%=side_drug.get(i).getId()%>" checked>
                        <b><%=side_drug.get(i).getName()%>
                        </b></label><br>
                    <%}%>
                    <%
                        for (int i = 0; i < other_side.size(); i++) {
                    %>
                    <label><input type="checkbox" name="side_effects2" value="<%=other_side.get(i).getId()%>">
                        <b><%=other_side.get(i).getName()%>
                        </b></label><br>
                    <%}%>
                </div>
                <div class="col-sm-6">
                    <%
                        for (int i = 0; i < substitutes_drug.size(); i++) {
                    %>
                    <label><input type="checkbox" name="drugs2" value="<%=substitutes_drug.get(i).getId()%>" checked><a href="/drug/<%=substitutes_drug.get(i).getId()%>" target="_blank">
                        <b><%=substitutes_drug.get(i).getName()%></b>
                    </a></label><br>
                    <%}%>
                    <%
                        for (int i = 0; i < other_drug.size(); i++) {
                    %>
                    <label><input type="checkbox" name="drugs2" value="<%=other_drug.get(i).getId()%>"><a href="/drug/<%=other_drug.get(i).getId()%>" target="_blank">
                        <b><%=other_drug.get(i).getName()%></b>
                    </a></label><br>
                    <%}%>
                </div>
            </div><br>
            <center><input class="button" type="submit" value="Редактировать лекарство"></center>
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