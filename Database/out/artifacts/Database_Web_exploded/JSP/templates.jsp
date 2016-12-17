<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <li class="lileft"><a class="active" href="">                                                                                  </a></li>
    <li class="lileft"><a class="active" href="/">На главную</a></li>
    <li class="lileft"><a href="/search">Поиск пациента</a></li>
    <li class="liright"><a href="/patientdelete">Выписать пациента</a></li>
    <li class="liright"><a href="/patientadd">Добавить пациента</a></li>
    <li class="liright"><a href="/registration">Регистрация</a></li>
    <li class="liright"><a href="/signin">Вход</a></li>
</ul>