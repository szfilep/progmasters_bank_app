<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="sping" uri="http://www.springframework.org/tags" %>

<layout:base title="View/modify account">
    <jsp:attribute name="body">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                ${successMessage}
            </div>
        </c:if>

        <form class="form-horizontal">
            <div class="form-group">
                <label path="userName" class="col-sm-2 control-label"><sping:message code="username"/></label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" path="userName" placeholder="${account.userName}" disabled/>
                </div>
            </div>
            <div class="form-group">
                <label path="goalName" class="col-sm-2 control-label">Goal name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" path="goalName" placeholder="${account.goalName}" disabled/>
                </div>
            </div>
            <div class="form-group">
                <label path="balance" class="col-sm-2 control-label">Balance</label>
                <div class="input-group col-xs-4" style="padding-left: 15px;">
                    <input type="text" class="form-control" path="balance" placeholder="${account.balance}" disabled/>
                    <span class="input-group-addon">$</span>
                </div>
            </div>
            <div class="form-group">
                <label path="funds" class="col-sm-2 control-label">Funds</label>
                <div class="input-group col-xs-4" style="padding-left: 15px;">
                    <input type="text" class="form-control" path="funds" placeholder="${account.funds}" disabled/>
                    <span class="input-group-addon">$</span>
                </div>
            </div>
            <sec:csrfInput/>
        </form>

        <c:if test="${not empty transfers}">
            <ul>
                <c:forEach var="tr" items="${transfers}">
                    <li>${tr}</li>
                </c:forEach>
            </ul>
        </c:if>

    </jsp:attribute>
</layout:base>