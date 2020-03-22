<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">User detailed info</h3>

                            <div class="row" style="padding-left: 12px;padding-top: 10px">
                                <a type="button" class="btn btn-sm btn-default"
                                   href="/user/form">
                                    <i class="fa fa-plus"></i> Add
                                </a>&nbsp;&nbsp;&nbsp;

                                <a type="button" class="btn btn-sm btn-default"
                                   href="/user/deleteMulti">
                                    <i class="fa fa-close"></i> Remove
                                </a>&nbsp;&nbsp;&nbsp;

                                <a type="button" class="btn btn-sm btn-default"
                                   href="/user/export">
                                    <i class="fa fa-download"></i> Export
                                </a>&nbsp;&nbsp;&nbsp;

                                <a type="button" class="btn btn-sm btn-default"
                                   href="/user/import">
                                    <i class="fa fa-upload"></i> Import
                                </a>
                            </div>

                            <div class="box-tools">
                                <div class="input-group input-group-sm" style="width: 150px;">
                                    <input type="text" name="table_search" class="form-control pull-right"
                                           placeholder="Search">

                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-default"><i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <thead>

                                <tr>
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
                                        <td>${tbUser.id}</td>
                                        <td>${tbUser.username}</td>
                                        <td>${tbUser.email}</td>
                                        <td>${tbUser.phone}</td>
                                        <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
</body>
</html>
