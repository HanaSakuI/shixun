$(document).ready(function(){
    /** 新增   **/
    $("#addBtn").fancybox({
        'href'  : 'user_edit.html',
        'width' : 733,
        'height' : 530,
        'type' : 'iframe',
        'hideOnOverlayClick' : false,
        'showCloseButton' : false,
        'onClosed' : function() {
            window.location.href = 'user.html';
        }
    });
    /**编辑   **/
    $("a.edit").fancybox({
        'width' : 733,
        'height' : 530,
        'type' : 'iframe',
        'hideOnOverlayClick' : false,
        'showCloseButton' : false,
        'onClosed' : function() {
            window.location.href = 'user.html';
        }
    });
    // /**冻结  **/
    // $("a.frozen").fancybox({
    //     'width' : 733,
    //     'height' : 530,
    //     'type' : 'iframe',
    //     'hideOnOverlayClick' : false,
    //     'showCloseButton' : false,
    //     'onClosed' : function() {
    //         window.location.href = 'user.html';
    //     }
    // });

    /**查看log日志**/
    $("a.log").fancybox({
        'width' : 733,
        'height' : 530,
        'type' : 'iframe',
        'hideOnOverlayClick' : false,
        'showCloseButton' : false,
        'onClosed' : function() {
            window.location.href = 'user.html';
        }
    });

});