app.controller("collectController", function ($scope, collectService) {

    // 项目的初始化
    $scope.init = function () {
        $scope.findOne();
    };

    // 收藏页面中的更换头像
    $scope.upload = function (mobile) {
        var uploadFile = document.getElementById("upload");
        uploadFile.click();
        uploadFile.onchange = function () {
            collectService.upload(this.files[0], mobile).success(function (response) {
                if (response.result) {
                    $scope.findOne();
                }
            });
        }
    };

    // 收藏页面查询用户的信息
    $scope.findOne = function () {
        collectService.findOne().success(function (response) {
            $scope.user = response;
            $scope.getMyCollect(response.id);
        });
    };

     // 获取收藏的作品
    $scope.getMyCollect = function (id) {
        collectService.getMyCollect(id).success(function (response) {
            $scope.myCollect = response;
        })
    };

    // 鼠标滑过作品之后的变色效果
    $scope.show = function () {
        var works = document.getElementsByClassName("work");
        for (var i = 0; i < works.length; i++) {
            works[i].onmouseover = function () {
                for (var i = 0; i < works.length; i++) {
                    works[i].style.background = '#fff';
                }
                this.style.background = "#f4f4f47d";
            };
            works[i].onmouseleave = function () {
                for (var i = 0; i < works.length; i++) {
                    works[i].style.background = '#fff';
                }
            }
        }
    };

    // 删除作品时提示框中的取消
    $scope.cancel = function () {
        document.getElementById("outer").style.display="none";
    };

    // 点击删除时弹出提示框
    $scope.showOuter = function (id) {
        document.getElementById("outer").style.display="block";
        // 点击确认删除之后访问后台进行删除
        document.getElementById("confirm").onclick = function () {
            collectService.deleteCollect(id).success(function (response) {
                // 删除成功
                if (response.result) {
                    $scope.cancel();
                    alert(response.message);
                    $scope.init();
                } else {// 删除失败
                    alert(response.message)
                }
            });
        }
    };

});