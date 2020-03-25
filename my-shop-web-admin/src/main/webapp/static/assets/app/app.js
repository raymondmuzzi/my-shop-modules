var App = function () {

    var _masterCheckbox;
    var _checkbox;

    // store the id's array
    var _idArray;

    /**
     * private method, init iCheck
     */
    var handlerInitCheckbox = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        })

        // get the control checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');

        // get all the checkbox list
        _checkbox = $('input[type="checkbox"].minimal');

    };

    /**
     * Checkbox select all
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            // if true, checkbox is unchecked
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }
            // if false, checkbox is checked
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     * Delete multi selected id
     *
     * @param url the ajax url to server
     */
    var handlerDeleteMulti = function (url) {

        _idArray = new Array();

        // add the deleted id to the array
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefined" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        if (_idArray.length === 0) {
            $("#modal-message").html("You've never selected any data, please select at least one");
        }

        else {
            $("#modal-message").html("Are you sure to DELETE the data?");

        }

        $("#modal-default").modal("show");

        $("#btnModalOk").bind("click", function () {
            del();
        });

        /**
         * Current private function's private function
         */
        function del() {
            $("#modal-default").modal("hide");

            // if there are no selected data, close the confirmation alert
            if (_idArray.length === 0) {
                //...
            }
            // submit the data to the server side
            else {
                setTimeout(function () {
                    $.ajax({
                        "url": url,
                        "type": "POST",
                        "data": {"ids": _idArray.toString()},
                        "dataType": "JSON",
                        "success": function (data) {
                            if (data.status === 200) {
                                window.location.reload();
                            }

                            // delete failed
                            else {
                                $("#btnModalOk").unbind();
                                $("#btnModalOk").bind("click", function () {
                                    $("#modal-default").modal("hide");
                                });

                                $("#modal-message").html(data.message);
                                $("#modal-default").modal("show");

                            }
                        }
                    });
                }, 500);
            }
        }
    };


    return {
        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        }
    }
}();

$(document).ready(function () {
    App.init()
});