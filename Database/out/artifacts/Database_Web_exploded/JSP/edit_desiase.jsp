<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ page import="java.util.List" %>
<%@ page import="models.*" %>
<%@ page import="java.util.LinkedList" %>
    <%Desiase desiase = (Desiase) request.getAttribute("desiase");%>
    <%List<Symptoms> symptoms_desiase = (List) request.getAttribute("symptoms_desiase");%>
    <%List<Symptoms> other_symptoms = (List) request.getAttribute("other_symptoms");%>
    <%List<Procedures> procedures_desiase = (List) request.getAttribute("procedures_desiase");%>
    <%List<Procedures> other_procedures = (List) request.getAttribute("other_procedures");%>
    <%List<Drug> drug_desiase = (List) request.getAttribute("drug_desiase");%>
    <%List<Drug> other_drug = (List) request.getAttribute("other_drug");%>
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
    <li class="liright"><a href="/desiaseadd">Добавить болезнь</a></li>
    <li class="liright"><a href="/desiaseedit/<%=desiase.getId()%>">Изменить болезнь</a></li>
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
                <center>Редактирование болезни</center>
            </h1>
            <input class="input-block-level" id="name-field" name="name" type="text"
                   placeholder="Введите новое название болезни" value="<%=desiase.getName()%>" required/></td></tr>
            <input class="input-block-level" id="age-field" name="average_age" type="text"
                   placeholder="Введите новый средний возраст больного" value="<%=desiase.getAverage_age()%>" required/></td></tr>
            <input class="input-block-level" id="man-field" name="chance_desiase_man" type="text"
                   placeholder="Введите новую вероятность заболевания мужчины" value="<%=desiase.getChance_desiase_man()%>" required/></td></tr>
            <input class="input-block-level" id="women-field" name="chance_desiase_women" type="text"
                   placeholder="Введите новую вероятность заболевания женщины" value="<%=desiase.getChance_desiase_women()%>" required/></td></tr>
            <br>
            <div class="row">
                <div class="col-sm-4"><b>Симптомы</b></div>
                <div class="col-sm-4"><b>Процедуры</b></div>
                <div class="col-sm-4"><b>Лекарства</b></div>
                <div class="col-sm-4">
                    <%
                        for (int i = 0; i < symptoms_desiase.size(); i++) {
                    %>
                    <label><input type="checkbox" name="symptoms2" value="<%=symptoms_desiase.get(i).getId()%>" checked><a
                            href="/symptom/<%=symptoms_desiase.get(i).getId()%>"
                            target="_blank"><b><%=symptoms_desiase.get(i).getName()%>
                    </b></a></label><br>
                    <%}%>
                    <%
                        for (int i = 0; i < other_symptoms.size(); i++) {
                    %>
                    <label><input type="checkbox" name="symptoms2" value="<%=other_symptoms.get(i).getId()%>"><a
                            href="/symptom/<%=other_symptoms.get(i).getId()%>"
                            target="_blank"><b><%=other_symptoms.get(i).getName()%>
                    </b></a></label><br>
                    <%}%>
                </div>
                <div class="col-sm-4">
                    <%
                        for (int i = 0; i < procedures_desiase.size(); i++) {
                    %>
                    <label><input type="checkbox" name="procedures2" value="<%=procedures_desiase.get(i).getId()%>" checked><a
                            href="/procedure/<%=procedures_desiase.get(i).getId()%>"
                            target="_blank"><b><%=procedures_desiase.get(i).getName()%>
                    </b></a></label><br>
                    <%}%>
                    <%
                        for (int i = 0; i < other_procedures.size(); i++) {
                    %>
                    <label><input type="checkbox" name="procedures2" value="<%=other_procedures.get(i).getId()%>"><a
                            href="/procedure/<%=other_procedures.get(i).getId()%>"
                            target="_blank"><b><%=other_procedures.get(i).getName()%>
                    </b></a></label><br>
                    <%}%>
                </div>
                <div class="col-sm-4">
                    <%
                        for (int i = 0; i < desiase.getRecomendedDrugs().size(); i++) {
                    %>
                    <label><a
                            href="/drug/<%=desiase.getRecomendedDrugs().get(i).getDrug().getId()%>" title="Количество на складе: <%=desiase.getRecomendedDrugs().get(i).getDrug().getQuantity()%>"target="_blank"><b><%=desiase.getRecomendedDrugs().get(i).getDrug().getName()%>
                    </b></a></label>
                    <input class="input-block-level" name="<%=desiase.getRecomendedDrugs().get(i).getDrug().getName()%>" type="text"
                           value="<%=desiase.getRecomendedDrugs().get(i).getQuantity()%>">
                    <%}%>
                    <%
                        for (int i = 0; i < other_drug.size(); i++) {
                    %>
                    <label><a title="Количество на складе: <%=other_drug.get(i).getQuantity()%>"
                            href="/drug/<%=other_drug.get(i).getId()%>" target="_blank"><b><%=other_drug.get(i).getName()%>
                    </b></a></label>
                    <input class="input-block-level" name="<%=other_drug.get(i).getName()%>" type="text"
                           value="0">
                    <%}%>
                </div>
            </div>
            <center><input class="button" type="submit" value="Редактировать болезнь"></center>
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