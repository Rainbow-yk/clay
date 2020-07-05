app.controller("indexController", function ($scope, $timeout, indexService) {

    // 项目初始化的方法
    $scope.init = function () {
        $scope.getSlideshowInfo();
        $scope.getUserInfo();
        $scope.getAllWork();
        $scope.getInformation();
    };

    // 获取所有的轮播图信息
    $scope.getSlideshowInfo = function () {
        indexService.getSlideshowInfo().success(function (response) {
            $scope.slideshow = response;
        })
    };

    // 获取资讯信息
    $scope.getInformation = function () {
        indexService.getInformation().success(function (response) {
            $scope.information = response;
        })
    };

    // 获取用户的信息
    $scope.getUserInfo = function () {
        indexService.getUserInfo().success(function (response) {
            $scope.user = response;
            $scope.getMyCollect(response.id)
        })
    };

    // 获取登录用户收藏的作品，让爱心变成红色
    $scope.getMyCollect = function (id) {
        indexService.getMyCollect(id).success(function (response) {
            for (var i = 0; i < response.length; i++) {
                var obj = document.getElementsByClassName("collect"+response[i].id);
                for (var j = 0; j < obj.length; j++) {
                    obj[j].style.color = "red"; // 将图标变红
                }
            }
        })
    };

    // 挨到头像后显示选项
    $scope.showOptions = function () {
        document.querySelector('.options').style.display = 'block'
    };

    // 离开头像之后隐藏
    $scope.hiddenOptions = function () {
        document.querySelector('.options').style.display = 'none'
    };

    // 鼠标滑过选项时的变色效果
    $scope.pitchOn = function () {
        var lis = document.querySelectorAll('.options li');
        for (var i = 0; i < lis.length; i++) {
            lis[i].onmouseover = function () {
                for (var i = 0; i < lis.length; i++) {
                    lis[i].className = '';
                }
                this.className = "pitch-on";
            };
            lis[i].onmouseleave = function () {
                for (var i = 0; i < lis.length; i++) {
                    lis[i].className = '';
                }
            };
        }
    };

    // 查询所有的作品
    $scope.getAllWork = function () {
        indexService.getAllWork().success(function (response) {
            $scope.works = response;
        })
    };

    // 用户点赞之后的变色，+1，后台存数据
    $scope.like = function (work, user) {
        if (user.mobile != null) {
            var likeObj = document.getElementsByClassName("like"+work.id);
            if (likeObj[0].style.color == "") {
                work.likeCount += 1;
                for (var i = 0; i < likeObj.length; i++) {
                    likeObj[i].style.color = "red";
                }
                indexService.collectWork(work).success(function (response) {
                });
            } else {
                work.likeCount -= 1;
                for (var j = 0; j < likeObj.length; j++) {
                    likeObj[j].style.color = "";
                }
                indexService.collectWork(work).success(function (response) {
                });
            }
        } else {
            alert("请先登录")
        }
    };
    
    $scope.collect = function (work, user) {
        if (user.mobile != null) {
            var collectObj = document.getElementsByClassName("collect"+work.id);
            if (collectObj[0].style.color == "") {
                work.collectCount += 1;  // +1
                for (var i = 0; i < collectObj.length; i++) {
                    collectObj[i].style.color = "red"; // 将图标变红
                }
                // 去后台存数据
                indexService.collectWork(work).success(function (response) {
                    if (response.result) {
                        var obj = document.getElementsByClassName("success"+work.id);
                        for (var j = 0; j < obj.length; j++) {
                            obj[j].style.display = "block";
                        }
                        $scope.hiddenMessage(work.id);// 将收藏成功的提示信息定时隐藏
                    }
                });
            }
        } else { // 游客没登录不允许电赞和收藏
            alert("请先登录")
        }

    };

    // 点击分类按钮之后根据不同的分类获取不同的数据
    $scope.findWorkByCategoryId = function (id) {
        indexService.findWorkByCategoryId(id).success(function (response) {
            $scope.categoryWorks = response;
        });
    };

    // 提示二秒消失
    $scope.hiddenMessage = function (id) {
        var timeout = $timeout(function(){
            var obj = document.getElementsByClassName("success"+id);
            for (var j = 0; j < obj.length; j++) {
                obj[j].style.display = "none";
            }
        }, 2000);   // 二秒后执行此方法
    };

    // 首页搜索
    $scope.search = function () {
        var keyword = $scope.keyword;
        if (keyword != null) {
            indexService.search(keyword).success(function (response) {
                $scope.mySearch = response;
                $scope.kill = {
                    "display" : "none"
                };
                $scope.searchResult = {
                    "display" : "block"
                }
            })
        }
    };

    $scope.vertical = function () {
        document.getElementById("show2").style.display = "none";
        document.getElementById("show1").style.display = "block";
    };

    $scope.horizontal = function () {
        document.getElementById("show1").style.display = "none";
        document.getElementById("show2").style.display = "block";
        document.getElementById("horizontal").style.display = "block";
    };

});