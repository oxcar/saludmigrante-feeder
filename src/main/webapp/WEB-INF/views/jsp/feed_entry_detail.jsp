<%--@elvariable id="feedEntry" type="com.copili.feeder.domain.FeedEntry"--%>
<%--@elvariable id="states" type="java.util.List<om.copili.feeder.domain.State>"--%>
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

<div class="row" style="margin-top: 50px;">
    <div class="large-12 columns">
        <div class="row">
            <div class="large-12 columns">
                <h1>Entrada de Feed (id): ${feedEntry.id}</h1>
            </div>
        </div>
    </div>
    <div class="large-12 columns">
        <div class="row">
            <div class="large-12 columns">
                <div class="row">
                    <div class="large-12 columns">
                        <h4>${feedEntry.title}</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <p>${feedEntry.description}</p>
                    </div>
                </div>

                <div class="row">
                    <div class="large-12 columns">
                        <h5 class="subheader">Estado</h5>
                        <select>
                            <c:forEach var="state" items="${states}">
                                <option value="${state.code}">${state.state}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="large-12 columns">
                        <h5 class="subheader">Palabras Clave</h5>
                        <input type="text" value="${feedEntry.matchedWordsAsString}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <h5 class="subheader">Frases Clave</h5>
                        <ul>
                            <c:forEach var="sentence" items="${feedEntry.matchedPhrases}">
                                <li>${sentence}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <c:forEach var="thumbnail" items="${feedEntry.thumbnails}">
                            <img src="${thumbnail}" style="max-width: 200px; max-height: 150px;"/>
                        </c:forEach>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <p>Fuente: ${feedEntry.source}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="large-12 columns">
                        <p>Fecha publicacion: ${feedEntry.publishedDate}</p>
                    </div>
                </div>

                <div class="row">
                    <div class="large-12 columns" style="text-align: left;">
                        <a class="button tiny radius" href="${feedEntry.uri}" target="_blank">Ver Entrada</a>
                        <button class="button tiny success radius">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
