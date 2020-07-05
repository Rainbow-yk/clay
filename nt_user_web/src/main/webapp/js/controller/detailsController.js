app.controller("detailsController", function ($scope, detailsService) {

    // 通过路径地址上拼接的id获取作品的详情
    $scope.getInfo = function () {
        var url = location.href;
        var index = url.indexOf("=");
        var id = url.substring(index + 1);
        var re = /^[0-9]+$/ ;
        if (re.test(id)) { // id属于正整数就请求后台
            detailsService.getInfo(id).success(function (response) {
                $scope.info = response;
                var main = document.getElementById("main");
                main.innerHTML = response.workInfo.content
            })
        } else { // 数据不正常就跳转到404
            window.location.href = "pageNotFind.html";
        }
    }

});