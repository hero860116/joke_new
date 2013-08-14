$(function(){
    $("#topJoke").click(function(){
        var thisObj = $(this);
        var jokeId = $(this).parent().children().eq(0).val();
        var topSize =  $(this).parent().children().eq(1).val();
        $.ajax({
            type: "POST",
            url: "action.htm?action=joke_action&event_submit_do_top_joke=1",
            cache:false,
            async:true,
            data:{"id" : jokeId},
            dataType:"json",
            success: function(data){
                var newTopSize = parseInt(topSize)+1;
                thisObj.children().eq(1).text(newTopSize);
                thisObj.parent().children().eq(1).val(newTopSize);

                $(this).parent().children('.digbtn').addClass('disabled');
                $(this).parent().children('.digbtn').removeClass('digbtn');
            }
        });

    });

    $("#downJoke").click(function(){
        var thisObj = $(this);
        var jokeId = $(this).parent().children().eq(0).val();
        var downSize =  $(this).parent().children().eq(2).val();
        $.ajax({
            type: "POST",
            url: "action.htm?action=joke_action&event_submit_do_down_joke=1",
            cache:false,
            async:true,
            data:{"id" : jokeId},
            dataType:"json",
            success: function(data){
                var newDownSize = parseInt(downSize)+1;
                thisObj.children().eq(1).text(newDownSize);
                thisObj.parent().children().eq(2).val(newDownSize);
            }
        });

    });

    $(function(){
        $('.digbtn').click(function(e){
            e.preventDefault();

            if($(this).hasClass('disabled')){
                return;
            }

            if($(this).hasClass('btn-success')){
                $.get('action.htm?action=joke_action&event_submit_do_top_joke=1',{id:$(this).attr('data-id')});
            }else{
                $.get('action.htm?action=joke_action&event_submit_do_down_joke=1',{id:$(this).attr('data-id')});
            }

            $(this).html('<i class="icon-thumbs-up icon-white"></i>'+(parseInt($(this).text())+1));
            $(this).parent().children('.digbtn').addClass('disabled');
            $(this).parent().children('.digbtn').removeClass('digbtn');
        });
    });
});