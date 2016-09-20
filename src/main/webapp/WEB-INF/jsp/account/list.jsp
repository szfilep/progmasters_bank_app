<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<layout:base title="View/modify account">
    <jsp:attribute name="body">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                    ${successMessage}
            </div>
        </c:if>

        <h2><spring:message code="list.accounts"/></h2>
        <c:if test="${not empty accounts}">
            <ul class="list-group">
                <c:forEach var="acc" items="${accounts}">
                    <li class="list-group-item">${acc}</li>
                </c:forEach>
            </ul>
        </c:if>


        <h2><spring:message code="list.transfers"/></h2>
        <c:if test="${not empty transfers}">
            <ul class="list-group">
                <c:forEach var="tr" items="${transfers}">
                    <li class="list-group-item">${tr}</li>
                </c:forEach>
            </ul>
        </c:if>


    </jsp:attribute>
</layout:base>