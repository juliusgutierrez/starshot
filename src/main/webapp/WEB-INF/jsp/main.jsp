<%--
  Created by IntelliJ IDEA.
  User: juliusgutierrez
  Date: 18/05/2019
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Dashboard</title>

    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet"
          href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

    <link rel="stylesheet"
          href="${contextPath}/resources/css/common.css">


</head>
<body>
<input type="hidden" value="${contextPath}" id="contextPath">
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand navbar-left" href="#">Employee Time Tracker Table</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="${contextPath}/logout">Sign out</a></li>

        </ul>
    </div><!-- /.container-fluid -->
</nav>
<div class="dt-content">

    <div class="div-search">

        <div class="form-inline">
            <div class="form-group">
                <label for="name">Search Name: </label>
                <input type="text" class="form-control" id="name" placeholder="Name">
            </div>
            <label for="status">Select Status: </label>
            <div class="form-group">
                <div>
                    <select id="status" name="active" class="selectpicker">
                        <option value="">All</option>
                        <option value=true>Active</option>
                        <option value=false>Not Active</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <button class="btn btn-success float-right" id="search-btn">
                    <span class='glyphicon glyphicon-search' aria-hidden='true'></span>
                    Search
                </button>
            </div>
            <div class="form-group pull-right">
                <button class="btn btn-info float-right" id="create-btn">
                    <span class='glyphicon glyphicon-plus' aria-hidden='true'></span>
                    Add New Entry
                </button>
            </div>
        </div>
    </div>
    <div class="div-dt-content">
        <table id="employee-table" class="table table-striped table-bordered employee-dt">
            <thead>
            <tr>
                <th>Action</th>
                <th>User Id</th>
                <th>Full Name</th>
                <th>Time In</th>
                <th>Time Out</th>
                <th>Active</th>
            </tr>
            </thead>
        </table>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" tabindex="-1">
    <div class="modal-dialog" role="document">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Employee Time Track Detail</h4>
            </div>

            <!-- Modal Body -->
            <div class="modal-body">

                <div class="text-danger error-div" role="alert">
                    <small></small>
                </div>

                <form class="form employee-form">
                    <div class="loader hide"></div>
                    <input type="hidden" id="userId" name="id">
                    <div class="form-group">
                        <label for="fullname">Full Name </label>
                        <input type="text" class="form-control" id="fullname" name="fullName"
                               placeholder="Name">
                    </div>
                    <div class="form-group">
                        <label for="timeIn">Time In </label>
                        <div class="input-group date" id="timeInDate">
                            <input type="text" class="form-control" id="timeIn" name="timeIn"
                                   placeholder="Time in">
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="timeOut">Time Out </label>
                        <div class="input-group date" id="timeOutDate">
                            <input type="text" class="form-control" id="timeOut" name="timeOut"
                                   placeholder="Time out">
                            <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                        </div>
                    </div>
                    <div class="form-group select-div">
                        <label for="isActive">Status</label>
                        <div>
                            <select id="isActive" name="active" class="selectpicker col-xs-12">
                                <option value=true>Active</option>
                                <option value=false>Not Active</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="delete-btn">Delete</button>
                <button type="submit" class="btn btn-success" id="save-btn">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myNotifModal" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" tabindex="-1">
    <div class="modal-dialog modal-sm" role="document">
        <!-- Modal content-->
        <div class="modal-content">
            <!-- Modal Body -->
            <div class="modal-body">
                <h4 class="success-msg">Success</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>

</body>
</html>

