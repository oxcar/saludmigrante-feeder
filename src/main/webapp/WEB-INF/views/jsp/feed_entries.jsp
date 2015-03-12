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
    <style>
        .processed {
            background-color: #f3fff2;
            border: 2px solid green;
            margin-bottom: 20px;
            padding-top: 10px;
        }
        .not-processed {
            border: 1px solid #ddd;
            margin-bottom: 20px;
            padding-top: 10px;
        }
    </style>
</head>
<body ng-app="feeder">

<div class="row" style="margin-top: 50px;" ng-controller="FeedEntriesController" ng-init="loadAllFeedEntries()">
    <div class="large-12 columns">
        <div class="row">
            <div class="large-12 columns">
                <h1>Noticias</h1>
            </div>
        </div>
    </div>
    <div class="large-12 columns">
        <div class="row" ng-show="feedEntries" ng-repeat="feedEntry in feedEntries">
            <div class="large-12 columns" ng-class="{true:'processed', false:'not-processed'}[feedEntry.processed]">
                <div class="row">
                    <div class="large-12 columns">
                        <h4>{{feedEntry.title}}</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <p>{{feedEntry.description}}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <h5 class="subheader">Estado</h5>
                        <select ng-model="feedEntry.state" ng-options="state.code as state.state for state in states"></select>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="large-12 columns">
                        <h5 class="subheader">Palabras Frecuentes</h5>
                        <a href="#" class="button tiny disabled radius" style="margin-right: 3px;" ng-repeat="word in feedEntry.frequency_words">{{word}}</a>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <h5 class="subheader">Palabras Clave</h5>
                        <input type="text" ng-model="feedEntry.matched_words_as_string" />
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <h5 class="subheader">Frases Clave</h5>
                        <ul>
                            <li ng-repeat="sentence in feedEntry.matched_phrases">{{sentence}}</li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <img src="" ng-src="{{thumbnail}}" ng-repeat="thumbnail in feedEntry.thumbnails"
                             style="max-width: 200px; max-height: 150px;"/>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <p>Fuente: {{feedEntry.source}}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <p>Fecha publicacion: {{feedEntry.published_date | date : 'dd MMMM yyyy'}}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns" style="text-align: left;">
                        <a class="button tiny radius" ng-href="{{feedEntry.uri}}" target="_blank">Ver Entrada</a>
                        <button class="button tiny success radius" ng-click="processFeedEntry($index)">Procesar</button>
                        <button class="button tiny alert radius" ng-click="deleteFeedEntry(feedEntry.feedId, feedEntry.id)">EliminarEntrada</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-hide="feedEntries.length > 0">
        <div class="large-12 columns">
            <div class="radius" style="text-align: center; padding: 15px; background-color: #eef;">
                No hay Entradas
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>
