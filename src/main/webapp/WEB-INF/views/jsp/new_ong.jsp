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

<div class="row" style="margin-top: 50px;" ng-controller="OngsController">
    <div class="large-12 columns">

        <h3>New ONG</h3>

        <div class="row">
            <div class="large-12 columns">
                <label for="name">Name:
                    <input id="name" type="text" ng-model="ong.name" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns">
                <label for="description">Description:
                    <input id="description" type="text" ng-model="ong.description" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="country">Country:
                    <input id="country" type="text" ng-model="ong.country" />
                </label>
            </div>
            <div class="large-6 columns">
                <label for="city">City:
                    <input id="city" type="text" ng-model="ong.city" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="website">Website:
                    <input id="website" type="text" ng-model="ong.website" />
                </label>
            </div>
            <div class="large-6 columns">
                <label for="email">e-Mail:
                    <input id="email" ng-model="ong.email" type="text" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="ranking">Ranking:
                    <input id="ranking" ng-model="ong.ranking" type="number" min="1" max="5" />
                </label>
            </div>
        </div>

        <div class="row" style="margin-top: 30px;">
            <div class="large-6 columns">
                <button class="button tiny success radius" ng-click="saveOng()">Save ONG</button>
            </div>
        </div>

        <div style="margin-top: 50px;"></div>

    </div>
</div>

</body>
</html>
