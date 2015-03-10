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

<div class="row" style="margin-top: 50px;" ng-controller="FeedsController" ng-init="loadFeeds()">
    <div class="large-12 columns">
        <div class="row">
            <div class="large-12 columns">
                <h1>Feeds</h1>
            </div>
        </div>
        <div class="row">
            <div class="large-12 columns" style="margin-bottom: 10px;">
                <h5>Agregar un Feed</h5>
            </div>
        </div>
        <div class="row">
            <div class="large-6 columns">
                <label for="feed-name">
                    Nombre
                    <input type="text" id="feed-name" placeholder="nombre del medio" ng-model="feed.name" />
                </label>
            </div>
            <div class="large-6 columns">
                <label for="feed-url">
                    URL
                    <input type="text" id="feed-url" placeholder="url del feed"  ng-model="feed.url" />
                </label>
            </div>
        </div>
        <div class="row">
            <div class="large-12 columns" style="margin: 20px 0">
                <button class="button tiny success radius" ng-click="addFeed()">Guardar</button>
                <a class="button tiny radius" href="${contextPath}/feeds/all-entries">Ver todas las Entradas</a>
            </div>
        </div>
    </div>
    <div class="large-12 columns">
        <div class="row" ng-repeat="feed in feeds">
            <div class="large-8 columns">
                {{feed.name}} - <a ng-href="{{feed.url}}" target="blank">{{feed.url}}</a>
            </div>
            <div class="large-4 columns" style="text-align: right;">
                <button class="button tiny alert radius" ng-click="deleteFeed(feed.id)">Eliminar Feed</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
