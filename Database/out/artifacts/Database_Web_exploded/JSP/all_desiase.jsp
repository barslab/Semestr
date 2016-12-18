<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ page import="java.util.List" %>
<%@ page import="models.Desiase" %>
<!DOCTYPE html>
<%@ page import="models.Symptoms" %>
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
    <li class="liright"><a href="/desiaseadd">Добавить болезнь</a></li>
    <li class="liright"><a href="/desiaseformulation">Формулировка болезни</a></li>
</ul>
<center><div class="container">
    <h1 class="back"><center >Список болезней</center></h1>
    <% if ((String) request.getAttribute("text")!=null) {%>
    <div class="alert alert-info">
        <center><strong>Отлично!</strong> <%=(String) request.getAttribute("text")%></center></div>
    <%}%>
    <%  List<String> errors = (List) request.getAttribute("error_drug");
     if (errors!=null) {
    for (int i=0;i<errors.size();i++) {%>
    <div class="alert alert-danger">
        <center><strong>Предупреждение!</strong> <%=errors.get(i)%></center></div>
    <%}}%>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-striped table-bordered" id="table2"><thead>
            <tr>
                <th>Id:      </th>
                <th>Название:      </th>
                <th>Вероятность заболевания мужчины:      </th>
                <th>Вероятность заболевания женщины:      </th>
                <th>Средний возраст больного:      </th>
                <th>Список симптомов:      </th>
                <th>Список необходимых процедур:      </th>
                <th>Список необходимых лекарств:      </th>
            </tr></thead>
                <tbody><%
                    List<Desiase> desiases = (List) request.getAttribute("desiases");
                    for (int i=0;i<desiases.size();i++) {
                %>
                <tr>
                    <td>
                        <%=desiases.get(i).getId()%>
                    </td>
                    <td><center><b>
                        <a href="/desiase/<%=desiases.get(i).getId()%>"><%=desiases.get(i).getName()%></a>
                    </b></center></td>
                    <td>
                        <%=desiases.get(i).getChance_desiase_man()%>
                    </td>
                    <td>
                        <%=desiases.get(i).getChance_desiase_women()%>
                    </td>
                    <td>
                        <%=desiases.get(i).getAverage_age()%>
                    </td>
                    <td><center>
                        <% for (int j=0; j<desiases.get(i).getSymptoms().size(); j++) {%>
                        <b><a href="/symptom/<%=desiases.get(i).getSymptoms().get(j).getId()%>"><%=desiases.get(i).getSymptoms().get(j).getName()%></a></b><br><%}%>
                    </center></td>
                    <td><center>
                    <% for (int k=0; k<desiases.get(i).getProcedures().size(); k++) {%>
                        <b><a href="/procedure/<%=desiases.get(i).getProcedures().get(k).getId()%>"><%=desiases.get(i).getProcedures().get(k).getName()%></a></b><br><%}%>
                    </center></td>
                    <td><center>
                    <% for (int z=0; z<desiases.get(i).getRecomendedDrugs().size(); z++) {%>
                        <b><a href="/drug/<%=desiases.get(i).getRecomendedDrugs().get(z).getDrug().getId()%>" title="Необходимое количество: <%=desiases.get(i).getRecomendedDrugs().get(z).getQuantity()%>"><%=desiases.get(i).getRecomendedDrugs().get(z).getDrug().getName()%></a></b><br><%}%>
                    </center></td>
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