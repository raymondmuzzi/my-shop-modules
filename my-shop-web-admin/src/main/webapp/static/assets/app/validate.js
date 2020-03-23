/** javascript object */
var Validate = function () {

    /**
     * Init jQuery validation
     */
    var handlerInitValidate = function () {
        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "Phone number is illegal");

        // validate the input form and add error info if the input info is illegal
        $("#inputForm").valid({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error,element) {
                element.parent().parent().attr("class","form-group has-error");
                error.insertAfter(element);
            }
        });
    };

    return {
        init: function () {
            handlerInitValidate();
        }
    }

}();

$(document).ready(function () {
    Validate.init();
});