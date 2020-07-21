ctxPath = 'http://127.0.0.1:8080/xrpt/';
    function showModal(id) {
        $.ajax({
            type:"post",
            url:ctxPath+"cookbook_detail",
            data:{
                id:$(id).attr('id'),
            },
            dataType:"text",
            async:false,
            success:function (data) {
                $(".modal-body").html(data);
                $("#s_imgurl").text($(id).find("img").attr('src'));
                $('#s_url').text($(id).attr('id'))
                $("#s_name").text($(id).find("h4").text());
                $('#collect').text("收藏");
                $('#collect').attr('disabled',false);
            },
            error:function (e) {
                console.log(e);
            }
        }) ;
    }
    function collect() {

        $.ajax({
            type:"post",
            url:ctxPath+"collect",
            data:{
                url:$('#s_url').text(),
                img:$("#s_imgurl").text(),
                name:$("#s_name").text(),
                uid:"12345"
            },
            dataType:"text",
            async:true,
            success:function (data) {
                if(data=="issues"){
                    alert("您已收藏过该菜谱！")
                    $('#collect').text("已收藏")
                    $('#collect').attr('disabled',true);
                }else if(data=="true"){
                    $('#collect').text("已收藏")
                    alert("收藏成功")
                    $('#collect').attr('disabled',true);
                }else{
                    alert("收藏失败！")
                    window.location.reload()
                }
            },
            error:function (e) {
                console.log(e);
            }
        }) ;
    }
    function showfavorite() {
        $('.test').text("")
        $.ajax({
            type:"post",
            url:ctxPath+"myfavorite",
            data:{
                id:"12345",
            },
            dataType:"json",
            async:true,
            success:function (data) {

                var favoriteMap=data.favoriteMap;
                for (var i=0;i<data.favoriteMap.length;i++){
                    $('.test').prepend(
                        "<div class='cookbook' id='"+favoriteMap[i].furl+"' data-toggle='modal' data-target='#myModal2' onclick='showModal(this)'>" +
                        "<img src='"+favoriteMap[i].img+"'/>"+
                        "<h4>"+favoriteMap[i].fname+"</h4>"+
                        "</div>"
                    )
                }
            },
            error:function (e) {
                console.log(e);
            }
        }) ;
    }
    function deleteFavorite() {
        $.ajax({
            type:"post",
            url:ctxPath+"deleteFavorite",
            data:{
                url:$('#s_url').text(),
                id:"12345"
            },
            dataType:"text",
            async:false,
            success:function (data) {

                if(data=="true"){
                    showfavorite()
                    $('#favorite a[href="#favorite"]').tab('show')
                    $('#myModal2').modal('hide')
                }
            },
            error:function (e) {
                console.log(e);
            }
        }) ;


    }
