app.controller("workController", function ($scope, workService) {

    $scope.init = function () {
        $scope.getAllWork();
        $scope.getInformation();
    };

    $scope.getAllWork = function () {
        workService.getAllWork().success(function (response) {
            $scope.workData = response;
        })
    };

    $scope.getInformation = function () {
        workService.getInformation().success(function (response) {
            $scope.information = response;
        })
    };

    // 提示删除信息的取消按钮方法
    $scope.cancel = function () {
        document.getElementById("outer1").style.display="none";
        document.getElementById("outer2").style.display="none";
    };

    // 弹出提示框
    $scope.showOuter = function (id) {
        document.getElementById("outer1").style.display="block";
        // 确定删除之后直接访问后台
        document.getElementById("confirm").onclick = function () {
            workService.deleteWork(id).success(function (response) {
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

    $scope.showOuter2 = function () {
        document.getElementById("outer2").style.display="block";
        // 确定删除之后直接访问后台
        document.getElementById("confirm2").onclick = function () {
            workService.updateInformation($scope.information).success(function (response) {
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


    $scope.search = function () {
        var keyword = $scope.keyword;
        if (keyword != null) {
            workService.searchWorks(keyword).success(function (response) {
                $scope.workData = response;
            })
        } else {
            alert("请填写关键字！")
        }
    };

    // $scope.updateInformation = function (information) {
    //     workService.updateInformation(information).success(function (response) {
    //         if (response.result) {
    //             $scope.cancel();
    //             // alert(response.message);
    //             $scope.init();
    //         }
    //     })
    // };

    $scope.updatePic = function () {
        var uploadFile = document.getElementById("upload");
        uploadFile.click();
        uploadFile.onchange = function () {
            workService.upload(this.files[0]).success(function (response) {
                $scope.information.picture = response.picUrl;
            });
        }
    };

});