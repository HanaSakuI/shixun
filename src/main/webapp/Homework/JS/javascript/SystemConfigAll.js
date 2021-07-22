$(function () {
    $(document).ready(function(){
        /**ÐÞ¸Ä  **/
        $("a.edit").fancybox({
            'width' : 400,
            'height' : 260,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'SystemConfig_List.html';
            }
        });
    });
})