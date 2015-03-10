<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${contextPath}/resources/css/foundation.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/normalize.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/feeder.css">
    <script src="${contextPath}/resources/js/jquery-2.1.3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.2/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.2/angular-route.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.2/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.2/angular-sanitize.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.2/angular-cookies.min.js"></script>
    <script src="${contextPath}/resources/js/feeder.js"></script>
</head>
<body ng-app="feeder">

<div class="row" style="margin-top: 50px;" ng-controller="OngsController" ng-init="loadOngs()">
    <div class="large-12 columns">
        <h1>ONGs</h1>
        <div class="row" ng-show="ongs.length" ng-repeat="ong in ongs">
            <div class="large-8 columns">
                {{ong.name}} - <a ng-href="{{ong.website}}" target="blank">{{ong.website}}</a>
            </div>
            <div class="large-4 columns" style="text-align: right;">
                <button class="button tiny alert radius" ng-click="deleteOng(ong.id)">Eliminar ONG</button>
            </div>
        </div>
        <div class="row" ng-hide="ongs.length" ng-repeat="ong in ongs">
            <div class="large-12 columns">
                <div class="radius" style="text-align: center; padding: 15px; background-color: #eef;">
                    No hay ONGs
                </div>
            </div>
        </div>
        <div class="row" style="margin-top:30px;">
            <div class="large-12 columns">
                <a href="${contextPath}/ongs/new">Nueva ONG</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
