<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" type="java.lang.String" required="false" description="Modal's title" %>
<%@ attribute name="message" type="java.lang.String" required="false" description="Modal's messge info" %>



<!-- confirmation alert -->
<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title == null ? "Tips:" : title} </h4>
            </div>
            <div class="modal-body">
                <p id="modal-message"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                <button id="btnModalOk" type="button" class="btn btn-primary">Submit</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    $(function () {
        $("#btnModalOk").bind("click", function () {
            <c:if test="${opts != 'confirm'}">
            $("#modal-default").modal("hide");
            </c:if>

            <c:if test="${opts == 'confirm'}">
            console.log("${url}");
            </c:if>
        })
    });
</script>
