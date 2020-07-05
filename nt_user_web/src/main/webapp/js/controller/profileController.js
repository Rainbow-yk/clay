app.controller("profileController", function ($scope, profileService) {

    // 页面初始化方法
    $scope.init = function () {
        $scope.findOne();
    };

    // 个人详情页中的修改头像方法
    $scope.upload = function (mobile) {
        var uploadFile = document.getElementById("upload");
        uploadFile.click();
        uploadFile.onchange = function () {
            profileService.upload(this.files[0], mobile).success(function (response) {
                if (response.result) {
                    $scope.findOne();
                }
            });
        }
    };

    // 页面详情中的查询用户信息的方法
    $scope.findOne = function () {
        profileService.findOne().success(function (response) {
            $scope.user = response;
            $scope.getMyWorks(response.id);
        });
    };

    // 获取我的左右作品的方法
    $scope.getMyWorks = function (id) {
        profileService.getMyWorks(id).success(function (response) {
            $scope.myWorks = response;
        })
    };

    // 鼠标滑过作品的动态效果
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

    // 提示删除信息的取消按钮方法
    $scope.cancel = function () {
        document.getElementById("outer").style.display="none";
    };

    // 弹出提示框
    $scope.showOuter = function (id) {
        document.getElementById("outer").style.display="block";
        // 确定删除之后直接访问后台
        document.getElementById("confirm").onclick = function () {
            profileService.deleteWork(id).success(function (response) {
                if (response.result) {
                    $scope.cancel();
                    alert(response.message);
                    $scope.init();
                } else {
                    alert(response.message)
                }
            });
        }
    };
    $scope.down = {
        "display" : "none"
    };
    $scope.showDetailsInfo = function () {
        $scope.up = {
            "display" : "none"
        };
        $scope.down = {
            "display" : "block"
        };
        $scope.main = {
            "display" : "none"
        }
    };

    $scope.hiddenDetailsInfo = function () {
        $scope.up = {
            "display" : "block"
        };
        $scope.down = {
            "display" : "none"
        };
        $scope.main = {
            "display" : "block"
        }
    };

    $scope.updateUserInfo = function () {
        profileService.updateUserInfo($scope.user).success(function (response) {
            if (response.result) {
                alert(response.message)
            } else {
                alert(response.message)
            }
        });
    }
});