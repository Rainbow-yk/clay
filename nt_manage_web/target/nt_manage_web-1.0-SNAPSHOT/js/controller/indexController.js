app.controller('indexController', function ($scope, indexService) {

    $scope.keyword = "";
    // 查询
    $scope.search = function (pageNum, pageSize) {
        indexService.findPage(pageNum, pageSize, $scope.keyword).success(function (response) {
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.data;
        })
    };

    var radios = document.getElementsByClassName("checkOne");
    var checkAll = document.getElementById("checkAll");
    // 全选
    $scope.checkAll = function () {
        for (var i = 0; i < radios.length; i++) {
            radios[i].checked = checkAll.checked
        }
    };

    // 单选
    $scope.radio = function () {
        var temp = true;
        for (var i = 0; i < radios.length; i++) {
            if (!radios[i].checked) {
                temp = false;
                break;
            }
        }
        if (temp) {
            checkAll.checked = true;
        } else {
            checkAll.checked = false;
        }
    };

    // 删除用户
    $scope.deleteUser = function () {
        var ids = [];
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                ids.push(radios[i].value)
            }
        }
        if (ids.length == 0) {

        } else {
            indexService.deleteUser(ids).success(function (response) {
                if (response.result) {
                    $scope.reloadList();
                }
            })
        }
    };

    $scope.reloadList = function() {
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    };

    $scope.paginationConf = {
        currentPage: 1,     //当前页
        totalItems: 10,     //总条数
        itemsPerPage: 10,   //每一页显示的数量
        perPageOptions: [10,20],   //选择每页显示的数量
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    }

});