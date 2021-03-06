<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyShop | User management</title>
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
                User List
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-home"></i> Home</a></li>
                <li class="active">User Management</li>
                <li class="active">User List</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <!-- data validate board -->
                        <div class="alert alert-${baseResult.status == 200 ? 'success' : 'danger'} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-check"></i> Congratulation!</h4>
                                ${baseResult.message}
                        </div>
                    </c:if>

                    <!-- Advanced search form -->
                    <div class="box box-info box-info-search" style="display: none;">
                        <div class="box-header with-border">
                            <h3 class="box-title">Advanced Search</h3>
                        </div>
                        <!-- /.box-header -->

                        <!-- form start -->
                        <form:form cssClass="form-horizontal" action="/user/search" method="post"
                                   modelAttribute="tbUser">
                            <div class="box-body">
                                <!-- username -->
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-4 control-label">Username</label>
                                        <div class="col-sm-8">
                                            <form:input path="username" cssClass="form-control pull-right"
                                                        placeholder="Username"/>
                                        </div>
                                    </div>
                                </div>

                                <!-- email -->
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-2 control-label">Email</label>
                                        <div class="col-sm-10">
                                            <form:input path="email" cssClass="form-control pull-right"
                                                        placeholder="Email"/>
                                        </div>
                                    </div>
                                </div>

                                <!-- phone -->
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-2 control-label">Phone</label>
                                        <div class="col-sm-10">
                                            <form:input path="phone" cssClass="form-control pull-right"
                                                        placeholder="Phone"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-info">Search</button>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">

                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>


                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">User info list</h3>

                            <div class="row" style="margin-top: 20px;">
                                <div class="col-xs-12">
                                    <a type="button" class="btn btn-sm btn-default" href="#">
                                        <i class="fa fa-plus"></i> Add
                                    </a>&nbsp;&nbsp;&nbsp;

                                    <button type="button" class="btn btn-sm btn-default" onclick="App.deleteMulti('/user/delete')">
                                        <i class="fa fa-close"></i> Remove
                                    </button>&nbsp;&nbsp;&nbsp;

                                    <a type="button" class="btn btn-sm btn-default" href="#">
                                        <i class="fa fa-download"></i> Export
                                    </a>&nbsp;&nbsp;&nbsp;

                                    <a type="button" class="btn btn-sm btn-default"
                                       href="#">
                                        <i class="fa fa-upload"></i> Import
                                    </a>&nbsp;&nbsp;&nbsp;

                                    <button class="btn btn-sm btn-primary"
                                            onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast'):$('.box-info-search').hide('fast')">
                                        <i class="fa fa-search"></i> Search
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <thead>

                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Update</th>
                                    <th>Created</th>
                                    <th>Operation</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbUsers}" var="tbUser">
                                    <tr>
                                        <td><input id="${tbUser.id}" type="checkbox" class="minimal"></td>
                                        <td>${tbUser.id}</td>
                                        <td>${tbUser.username}</td>
                                        <td>${tbUser.email}</td>
                                        <td>${tbUser.phone}</td>
                                        <td><fmt:formatDate value="${tbUser.updated}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td><fmt:formatDate value="${tbUser.created}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <a type="button" class="btn btn-sm btn-default"
                                               href="/user/query/${tbUser.id}">
                                                <i class="fa fa-search"></i> Detail
                                            </a>&nbsp;&nbsp;&nbsp;
                                            <a type="button" class="btn btn-sm btn-primary"
                                               href="/user/form">
                                                <i class="fa fa-edit"></i> Edit
                                            </a>&nbsp;&nbsp;&nbsp;
                                            <a type="button" class="btn btn-sm btn-danger"
                                               href="/user/remove/${tbUser.id}">
                                                <i class="fa fa-trash"></i> Remove
                                            </a>&nbsp;&nbsp;&nbsp;

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
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

<%-- self defined modal --%>
<sys:modal/>

</body>
</html>
