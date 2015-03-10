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

<div class="row" style="margin-top: 50px;" ng-controller="CopisController" ng-init="loadStates()">
    <div class="large-12 columns">

        <h3>New Learn Copi</h3>

        <div class="row">
            <div class="large-12 columns">
                <label for="url">Url:
                    <input id="url" type="text" ng-model="copi.url" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns">
                <label for="title">Title:
                    <input id="title" type="text" ng-model="copi.title" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-12 columns">
                <label for="authors">Authors:
                    <input id="authors" type="text" ng-model="copi.authors" placeholder="Autores separados por comas" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="journal">Journal:
                    <input id="journal" type="text" ng-model="copi.journal" />
                </label>
            </div>
            <div class="large-6 columns">
                <label for="volume">Volume:
                    <input id="volume" type="text" ng-model="copi.volume" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="issue">Issue:
                    <input id="issue" type="text" ng-model="copi.issue" />
                </label>
            </div>
            <div class="large-6 columns">
                <label for="pages">Pages:
                    <input id="pages" ng-model="copi.pages" type="text" min="1" max="10000" />
                </label>
            </div>
        </div>

        <div class="row">
            <div class="large-6 columns">
                <label for="year">Year:
                    <input id="year" ng-model="copi.year" type="number" min="1800" max="2050" />
                </label>
            </div>
            <div class="large-6 columns">
                <label for="doi">DOI:
                    <input id="doi" type="text" ng-model="copi.doi" />
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
                    <input id="keywords" type="text" ng-model="copi.keywords" />
                </label>
            </div>
        </div>

        <div class="row" style="margin-top: 30px;">
            <div class="large-6 columns">
                <button class="button tiny radius" ng-click="addCopi()">New text field</button>
            </div>
        </div>

        <div class="row" ng-repeat="copiText in copi.texts" style="margin-top: 30px;">
            <div class="large-12 columns">
                <textarea name="" ng-model="copiText.text" id="copi-{{$index}}" cols="30" rows="2" maxlength="130" class="radius" style="width: 100%; border: 1px solid dimgrey;"></textarea>
            </div>
        </div>

        <div class="row" style="margin-top: 30px;">
            <div class="large-6 columns">
                <button class="button tiny success radius" ng-click="saveCopis()">Save Copis</button>
            </div>
        </div>

        <div style="margin-top: 50px;"></div>

    </div>
</div>

</body>
</html>
