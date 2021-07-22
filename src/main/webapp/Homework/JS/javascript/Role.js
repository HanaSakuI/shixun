$(function () {
    $(document).ready(function(){
        /** 新增   **/
        $("#addBtn").fancybox({
            'href': 'AddRole.html',
            'width': 300,
            'height': 300,
            'type': 'iframe',
            'hideOnOverlayClick': false,
            'showCloseButton': false,
            'onClosed': function () {
                window.location.href = 'RoleManage.html';
            }
        });

        /**修改  **/
        $("a.edit").fancybox({
            'width': 300,
            'height': 200,
            'type': 'iframe',
            'hideOnOverlayClick': false,
            'showCloseButton': false,
            'onClosed': function () {
                window.location.href = 'RoleManage.html';
            }
        });

        /**删除 **/
        $("a.deleteRole").fancybox({
            'width': 300,
            'height': 180,
            'type': 'iframe',
            'hideOnOverlayClick': false,
            'showCloseButton': false,
            'onClosed': function () {
                window.location.href = 'RoleManage.html';
            }
        });
    });
})