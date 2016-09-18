<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<layout:base title="List">
    <jsp:attribute name="body">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Transfer funds</h3>
                <%--<a href="<c:url value="/"/>" class="pull-right">Back to the list</a>--%>
            </div>

            <div class="panel-body">

                <c:url var="createTransferURL" value="/transfer/create"/>
                <form:form modelAttribute="transfer" action="${createTransferURL}" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label path="from" class="col-sm-2 control-label">Transfer from</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" path="from.userName" placeholder="${transfer.from.userName}" disabled="true"/>
                        </div>
                    </div>
                    <form:hidden path="from" value="${transfer.from.id}"/>
                    <div class="form-group">
                        <label for="to" class="col-sm-2 control-label">Transfer to</label>
                        <div class="col-sm-10">
                            <select id="to" name="to" class="form-control">
                                <option value="-1">--- Select account ---</option>
                                <c:forEach var="accTo" items="${accounts}">
                                    <option value="${accTo.id}">${accTo.goalName}</option>
                                </c:forEach>
                            </select>
                            <span class="errormessage"> <form:errors path="to"/></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label path="balance" class="col-sm-2 control-label">My balance</label>
                        <div class="input-group col-sm-4" style="padding-left: 15px;">
                            <input type="text" class="form-control" path="from.balance" placeholder="${transfer.from.balance}" disabled="true"/>
                            <span class="input-group-addon">$</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="amount" class="col-sm-2 control-label">Amount</form:label>
                        <div class="input-group col-sm-4" style="padding-left: 15px;">
                            <form:input type="text" class="form-control" path="amount" placeholder="0"/>
                            <span class="input-group-addon">$</span>

                        </div>
                        <form:label path="amount" class="col-sm-2 control-label"></form:label>
                        <div class="col-sm-4">
                            <span class="errormessage"> <form:errors path="amount"/></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Transfer</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </jsp:attribute>
</layout:base>