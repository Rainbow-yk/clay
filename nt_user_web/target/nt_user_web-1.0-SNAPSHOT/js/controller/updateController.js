app.controller("updateController", function ($scope, $timeout, updateService) {

    // 初始化富文本框编辑器
    var ue = UE.getEditor('editor');
    $scope.init = function () {
        $scope.getCategoryList();
        $scope.findOneById();
    };

    // 获取分类列表
    $scope.getCategoryList = function () {
        updateService.getCategoryList().success(function (response) {
            $scope.config = {
                data: response
                // data: [{id:1,text:'动物'},{id:2,text:'植物'},{id:3,text:'人物'},{id:4,text:'其他'}]
            };
        })
    };

    // 关于作品封面照片的图片上传
    $scope.uploadPic = function () {
        var uploadFile = document.getElementById("upload");
        uploadFile.click();
        uploadFile.onchange = function () {
            var filename = this.files[0].name;
            var lastIndexOf = filename.lastIndexOf(".");
            var extName = filename.substring(lastIndexOf + 1).toUpperCase();
            if (extName == "JPG" || extName == "PNG" || extName == "GIF") {
                updateService.upload(this.files[0]).success(function (response) {
                    alert(response.cover)
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

    // 通过作品的id去查找作品的所有信息并显示
    $scope.entity = {};
    $scope.findOneById = function () {
        var url = location.href;
        var index = url.indexOf("=");
        var id = url.substring(index + 1);// 通过路径上面的拼接数据获取id
        var re = /^[0-9]+$/ ;// 正则表达式匹配id是否为正整数
        if (re.test(id)) {
            // 请求后台获取作品的信息
            updateService.findOneById(id).success(function (response) {
                $scope.entity = response;
                //异步回调，将作品数据中的内容放到富文本框中
                ue.ready( function() {
                    ue.execCommand('insertHtml', response.content)
                });
                // 请求后台获取整个作品属于哪些分类
                updateService.findCategoryIds(id).success(function (response) {
                    $scope.cS2 = response;
                });
            });
        } else {
            // 从路径上获取的id有问题直接跳转到404页面
            window.location.href = "pageNotFind.html";
        }
    };

    // 提交修改后的作品信息
    $scope.update = function () {
        var arr = [];
        // 获取富文本框中的内容并且添加到arr对象中
        arr.push(UE.getEditor('editor').getContent());
        $scope.entity.content = arr.toString();
        // 进行数据的为空判断
        if ($scope.entity.title == null || $scope.entity.brief == null || $scope.entity.content == null || $scope.entity.coverUrl == null) {
            alert("请将信息补充完整")
            // $scope.flashMessage = "请将信息补充完整";
            // $scope.message = {
            //     "display" : "block"
            // };
            // $scope.hiddenMessage()
        } else {// 都不为空时才允许携带数据访问后台
            updateService.update($scope.entity, $scope.ids).success(function (response) {
                if (response.result) {
                    // $scope.flashMessage = response.message;
                    alert(response.message)
                    // $scope.message = {
                    //     "display" : "block"
                    // };
                    // $scope.hiddenMessage(response.message);
                }
            })
        }
    };

    // 提示三秒消失
    $scope.hiddenMessage = function (id) {
        var timeout = $timeout(function(){
            $scope.message = {
                "display" : "none"
            };
            window.location.href = "/details.html?id="+id;
        }, 3000);   // 三秒后执行此方法
    }

});