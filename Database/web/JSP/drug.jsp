<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ page import="models.Symptoms" %>
<%@ page import="models.Drug" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%Drug drug = (Drug) request.getAttribute("drug");%>
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
    <li class="liright"><a href="/drugdelete/<%=drug.getId()%>">Удалить лекарство</a></li>
    <li class="liright"><a href="/drugedit/<%=drug.getId()%>">Редактировать лекарство</a></li>
</ul>
<center>
    <div class="container">
        <h1 class="back">
            <center>Информация о лекарстве</center>
        </h1>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-striped table-bordered" id="table2">
                    <thead>
                    <tr>
                        <th>Id:</th>
                        <th>Название:</th>
                        <th>Форма:</th>
                        <th>Противопоказания:</th>
                        <th>Передозировка:</th>
                        <th>Количество на складе:</th>
                        <th>Побочные эффекты:</th>
                        <th>Заменители:</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <%=drug.getId()%>
                        </td>
                        <td>
                            <center><b>
                                <%=drug.getName()%>
                            </b></center>
                        </td>
                        <td><b>
                            <%=drug.getForm()%>
                        </b></td>
                        <td><b>
                            <%=drug.getContraindications()%>
                        </b></td>
                        <td><b>
                            <%=drug.getOverdose()%>
                        </b></td>
                        <td><b>
                            <%=drug.getQuantity()%>
                        </b></td>
                        <td><b>
                            <center>
                                <% for (int i = 0; i < drug.getSide_effects().size(); i++) {%>
                                <%=drug.getSide_effects().get(i).getName()%>
                                <br><%}%>
                            </center>
                        </b></td>
                        <td><b>
                            <center>
                                <% for (int j = 0; j < drug.getSubstitutes().size(); j++) {%>
                                <a href="/drug/<%=drug.getSubstitutes().get(j).getDrug().getId()%>" title="<%=drug.getSubstitutes().get(j).getDistinction()%>"><%=drug.getSubstitutes().get(j).getDrug().getName()%>
                                </a><br><%}%>
                            </center>
                        </b></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</center>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script src="//code.jquery.com/jquery-1.12.3.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
</body>
</html>
