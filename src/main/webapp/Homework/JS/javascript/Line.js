$(function () {
    $(document).ready(function(){
        /** 新增   **/
        $("#addBtn").fancybox({
            'href'  : 'Line_add.html',
            'width' :800,
            'height' : 530,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Line_List.html';
            }
        });
        /**修改  **/
        $("a.edit").fancybox({
            'width' : 800,
            'height' : 530,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Line_List.html';
            }
        });
    });
})