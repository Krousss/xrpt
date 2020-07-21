$("#reg_submitbut").click(function () {
    var pwd1 = $.trim($("#password1").val());
    var pwd2 = $.trim($("#password2").val());
    var phone = $.trim($("#selphone").val());
    var userName = $.trim($("#userName").val());
    if (phone == "") {
        $("#r_uid").text("账号不能为空")
        return false;
    }
    if (userName == "") {
        $("#r_userName").text("昵称不能为空")
        return false;
    }
    if (pwd1 == "") {
        $("#r_pass1").text("密码不能为空")
        return false;
    }
    if (pwd1 != pwd2) {
        $("#r_pass2").text("密码不一致")
        return false;
    }
    $("#reg_form").submit();
});