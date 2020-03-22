<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyShop | Dashboard</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <%-- nav jsp --%>
    <jsp:include page="../includes/nav.jsp"/>

    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                User Management
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>


        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-6 col-md-offset-3">
                    <c:if test="${baseResult != null}">
                        <!-- data validate board -->
                        <div class="alert alert-${baseResult.status == 200 ? 'success' : 'danger'} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-ban"></i> Alert!</h4>
                                ${baseResult.message}
                        </div>
                    </c:if>

                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbUser.id == null ? "Add":"Edit"} User</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <!-- form:form spring mvc form tag -->
                        <form:form cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                            <div class="box-body">

                                <div class="form-group">
                                    <label for="username" class="col-sm-2 control-label">Username</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control" path="username"
                                               placeholder="Please input username"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">Password</label>
                                    <div class="col-sm-10">
                                        <form:password cssClass="form-control" path="password"
                                               placeholder="Please declare password with character and digit"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="phone" class="col-sm-2 control-label">Phone</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" cssClass="form-control" path="phone"
                                               placeholder="Your phone number"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-10">
                                        <form:input type="email" cssClass="form-control" path="email"
                                               placeholder="Your email"/>
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default col-md-1 col-md-offset-3"
                                        onclick="history.go(-1);">Back
                                </button>
                                <button type="submit" class="btn btn-info col-md-1 col-md-offset-3">Submit</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
    </div>

    <%-- copyright jsp --%>
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<%-- footer jsp --%>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
