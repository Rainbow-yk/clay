app.controller("uploadController", function ($scope, uploadService, $timeout) {

    $scope.init = function () {
        // 初始化富文本框编辑器
        var ue = UE.getEditor('editor');
        $scope.getCategoryList()
    };

    // 获取分类列表
    $scope.getCategoryList = function () {
        uploadService.getCategoryList().success(function (response) {
            $scope.config = {
                data: response
                // data: [{id:1,text:'动物'},{id:2,text:'植物'},{id:3,text:'人物'},{id:4,text:'其他'}]
            };
        })
    };

    $scope.entity = {};
    // 发布作品
    $scope.issue = function () {
        var arr = [];
        // 获取富文本框中的内容然后封装到arr对象中
        arr.push(UE.getEditor('editor').getContent());
        $scope.entity.content = arr.toString();
        // 判断页面填的数据有没有空的
        if ($scope.entity.title == null || $scope.entity.brief == null || $scope.entity.content == null || $scope.entity.coverUrl == null) {
            $scope.flashMessage = "请将信息补充完整";
            $scope.message = {
                "display" : "block"
            };
            $scope.hiddenMessage()
        } else {// 数据都不为空就可以去访问后台了
            uploadService.issue($scope.entity, $scope.ids).success(function (response) {
                if (response.result) {
                    $scope.flashMessage = response.message;
                    $scope.message = {
                        "display" : "block"
                    };
                    $scope.hiddenMessage();
                }
            })
        }
    };

    // 关于作品封面照片的图片上传
    $scope.upload = function () {
        var uploadFile = document.getElementById("upload");
        uploadFile.click();
        uploadFile.onchange = function () {
            var filename = this.files[0].name;
            var lastIndexOf = filename.lastIndexOf(".");
            var extName = filename.substring(lastIndexOf + 1).toUpperCase();
            // 判断用户上传的是否是图片，是就访问后台
            if (extName == "JPG" || extName == "PNG" || extName == "GIF") {
                uploadService.upload(this.files[0]).success(function (response) {
                    $scope.entity.coverUrl = response.cover;
                    $scope.hidden = {
                        "display" : "none"
                    }
                });
            } else {
                $scope.flashMessage = "请上传图片文件";
                $scope.message = {
                    "display" : "block"
                };
                $scope.hiddenMessage()
            }
        }
    };

    // 提示三秒消失
    $scope.hiddenMessage = function () {
        var timeout = $timeout(function(){
            $scope.message = {
                "display" : "none"
            };
            window.location.href = "/profile.html"
        }, 3000);   // 三秒后执行此方法
    }

});