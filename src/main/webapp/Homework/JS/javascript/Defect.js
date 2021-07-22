$(function () {
    $(document).ready(function () {
        /**
         * 分配任务*/
        $("a.distribution").fancybox({
            'width': 500,
            'height': 300,
            'type': 'iframe',
            'hideOnOverlayClick': false,
            'showCloseButton': false,
            'onClosed': function () {
                window.location.href = 'Defect_list.html';
            }
        })
    })
})