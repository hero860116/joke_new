$(function () {
    $('.digbtn').click(function (e) {
        e.preventDefault();

        if ($(this).hasClass('disabled')) {
            return;
        }

        var digtype = 'oppos';
        if ($(this).hasClass('btn-success')) {
            digtype = 'dig';
            $.get('action.htm?action=joke_action&event_submit_do_top_joke=1', {id: $(this).attr('data-id')});
        } else {
            $.get('action.htm?action=joke_action&event_submit_do_down_joke=1', {id: $(this).attr('data-id')});
        }

        if(digtype == 'dig'){
            $(this).html('<i class="icon-thumbs-up icon-white"></i>'+(parseInt($(this).text())+1));
        }else{
            $(this).html('<i class="icon-thumbs-down icon-white"></i>'+(parseInt($(this).text())+1));
        }
        $(this).parent().children('.digbtn').addClass('disabled');
        $(this).parent().children('.digbtn').removeClass('digbtn');
    });
});