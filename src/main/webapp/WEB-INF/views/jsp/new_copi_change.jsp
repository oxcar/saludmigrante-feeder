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

<div class="row" style="margin-top: 50px;" ng-controller="ChangeCopisController" ng-init="loadCombos()">
    <div class="large-12 columns">

        <h3>New Change Copi</h3>

        <div class="row">
            <div class="large-12 columns">
                <label for="ongId">ONG:
                    <select id="ongId" ng-model="copi.ongId" ng-options="ong.id as ong.name for ong in ongs"></select>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns">
                <label for="url">URL:
                    <input type="text" id="url" ng-model="copi.url" />
                </label>
            </div>
        </div>
        <div class="row">
            <div class="large-12 columns">
                <label for="text">Copi:
                    <input type="text" id="text" ng-model="copi.text" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns">
                <label for="announcement">Announcement:
                    <input type="text" id="announcement" ng-model="copi.announcement" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="begin">Begin:
                    <input id="begin" type="text" ng-model="copi.begin" placeholder="dd/mm/yyyy" />
                </label>
            </div>
            <div class="large-6 columns">
                <label for="end">End:
                    <input id="end" type="text" ng-model="copi.end" placeholder="dd/mm/yyyy"/>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="state">State:
                    <select id="state" ng-model="copi.state" ng-options="state.code as state.state for state in states"></select>
                </label>
            </div>
            <div class="large-6 columns">
                <label for="keywords">Keywords:
                    <input id="keywords" type="text" ng-model="copi.keywords" placeholder="Separados por comas" />
                </label>
            </div>
        </div>

        <div class="row" style="margin-top: 30px;">
            <div class="large-6 columns">
                <button class="button tiny success radius" ng-click="saveChangeCopi()">Save Copi</button>
            </div>
        </div>

        <div style="margin-top: 50px;"></div>

    </div>
</div>

</body>
</html>
