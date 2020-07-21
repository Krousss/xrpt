$("#resubmit").click(function () {
    var pwd1 = $.trim($("#password1").val());
    var pwd2 = $.trim($("#password2").val());
    if (pwd1 == "") {
        alert("密码不能为空")
        return false;
    }
    if (pwd1 != pwd2) {
        alert("密码不一致")
        return false;
    }
    $("#reform").submit();
});