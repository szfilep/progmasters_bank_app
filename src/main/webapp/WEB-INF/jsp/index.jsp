<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<spring:message code="register.title" var="title"/>
<layout:base title="${title}" showNavbar="false">
    <jsp:attribute name="head">
        <link href="<c:url value="/static/css/signin.css"/>" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:attribute name="body">
        <c:if test="${not empty successMessage}">
			<div class="alert alert-success">
					${successMessage}
			</div>
		</c:if>

		<c:url var="createAccountURL" value="/account/create"/>
        <form:form modelAttribute="account" action="${createAccountURL}" method="post" class="form-horizontal">
			<div class="form-group">
				<spring:message code="account.username" var="username"/>
				<form:label path="userName" class="col-sm-2 control-label">${username}</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" path="userName" placeholder="${username}"/>
					<span class="errormessage"> <form:errors path="userName"/></span>
				</div>
			</div>
			<div class="form-group">
				<spring:message code="account.goalname" var="goalname"/>
				<form:label path="goalName" class="col-sm-2 control-label">${goalname}</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" path="goalName" placeholder="${goalname}"/>
					<span class="errormessage"> <form:errors path="goalName"/></span>
				</div>
			</div>
			<sec:csrfInput/>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Számla létrehozása</button>
				</div>
			</div>
		</form:form>


    </jsp:attribute>
</layout:base>