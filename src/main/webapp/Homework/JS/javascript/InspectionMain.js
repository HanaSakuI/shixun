
$(function () {
    $(document).ready(function(){
        /** 新增   **/
        $("#addBtn").fancybox({
            'href'  : 'Inspection_add.html',
            'width' :800,
            'height' : 530,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Inspection_List.html';
            }
        });

        //分配
        $("a.distribution").fancybox({
            'width' : 540,
            'height' : 480,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Inspection_List.html';
            }
        })

        /**修改  **/
        $("a.edit").fancybox({
            'width' : 800,
            'height' : 530,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Inspection_List.html';
            }
        });

        $("a.runView").fancybox({
            'width' : 1000,
            'height' : 800,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Inspection_RunList.html';
            }
        })

        $("a.view").fancybox({
            'width' : 1000,
            'height' : 800,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Inspection_List.html';
            }
        })

        $("a.runEdit").fancybox({
            'width' : 800,
            'height' : 530,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Inspection_RunList.html';
            }
        });

        $("a.runBack").fancybox({
            'width' : 800,
            'height' : 530,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
            'showCloseButton' : false,
            'onClosed' : function() {
                window.location.href = 'Inspection_RunList.html';
            }
        });
    });


})