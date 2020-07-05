app.controller("loginController",function ($scope, loginService, $interval) {

    $scope.msg = "";
    $scope.imagePictureUrl = "/login/changePicture.do";
    //切换图片验证码图片，new Date().getTime()是为了避免缓存
    $scope.changePicture = function () {
        $scope.imagePictureUrl = "/login/changePicture.do?time="+new Date().getTime();
    };

    //登录
    $scope.login = function () {
        if($scope.mobile == null || $scope.password== null || $scope.securityCode == null){
            $scope.msg = "请将信息填写完整";
            $scope.countDown();
            //判断手机号是否正确
        }else if(!(/^1[3456789]\d{9}$/.test($scope.mobile))){
            $scope.msg = "手机号码有误，请重填";
            $scope.countDown();
        }else {
            loginService.checkSecurityCode($scope.securityCode).success(function (response) {
                if(!response.result){
                    $scope.msg = response.message;
                }else {
                    //将表单的数据提交给spring security验证
                    document:loginForm.submit();
                }
            })
        }
    };

    //提示倒计时三秒
    var second=3;
    var timerHandler;
    $scope.countDown = function () {
        timerHandler = $interval(function(){
            if(second<=0){
                // 当执行的时间3s,结束时，取消定时器 ，cancle方法取消
                $interval.cancel(timerHandler);
                $scope.msg = "";
                second=3;
            }else{
                second--;
            }
        }, 3000)   //每一秒执行一次$interval定时器方法
    };


    $scope.getMessage = function () {
        var url = location.href;
        var index = url.indexOf("=");
        var msg = url.substring(index + 1);
        if (msg === "true") {
            $scope.msg = "用户名或者密码错误";
            $scope.countDown();
        }
    }

});