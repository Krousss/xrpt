 $("#reg_submitbut").click(function(){
            var UID = $.trim($("#UID").val());
            var pwd1 = $.trim($("#password1").val());
            var pwd2 = $.trim($("#password2").val());
            if(UID == ""){
            	$("#r_uid").text("id不能为空")
                return false;
            }
            if(pwd1 == ""){
                $("#r_pass1").text("密码不能为空")
                return false;
            }
            if(pwd1 != pwd2){
                $("#r_pass2").text("密码不一致")
                 $("#r_pass1").text("密码不一致")
                return false;
            }
            $("#reg_form").submit();
        });