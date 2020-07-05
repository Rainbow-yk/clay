app.controller('registerController',function ($scope, $interval, $timeout, registerService) {

    $scope.code = null;
    $scope.entity = {};
    // 请求后台发送短信验证码
    $scope.sendSecurityCode = function(){
        if(!(/^1[34578]\d{9}$/.test($scope.entity.mobile)) || $scope.entity.mobile == null){
            $scope.msg = "手机号码有误，请重填";
            $scope.hiddenMessage();
        }else {
            // 请求后台发送短信
            registerService.sendSecurityCode($scope.entity.mobile).success(function (respones) {
                // 发送失败进行处理
                // TODO
            });
            $scope.countDown(); // 点击发送验证码之后调用倒计时的方法
            $scope.notAllowed = { // 将效果设置为不能点击，并且按钮颜色变灰
                "pointer-events": "none",
                "opacity": "0.6",
                "cursor": "no-drop"
            };
        }
    };

    // 用户注册
    $scope.register = function () {
        if($scope.entity.nickname == null || $scope.entity.mobile == null || $scope.securityCode == null || $scope.entity.password == null || $scope.confirmPassword == null){
            $scope.msg = "请将信息补充完整";
            $scope.hiddenMessage();
        }else if ($scope.entity.password.length <= 3){
            $scope.msg = "密码不符合要求";
            $scope.hiddenMessage();
        }else if ($scope.entity.password != $scope.confirmPassword){//验证两次密码输入是否正确
            $scope.msg = "密码输入不一致";
            $scope.hiddenMessage();
        }else {
            registerService.register($scope.entity, $scope.securityCode).success(function (response) {
                if (response.result) {
                    location.href = response.message;
                } else {
                    $scope.msg = response.message;
                    $scope.hiddenMessage();
                }
            })
        }
    };

    // 短信验证码倒计时
    $scope.canClick=false;
    $scope.description = "获取验证码";
    var second=59;
    var timerHandler;
    $scope.countDown = function () {
        timerHandler = $interval(function(){
            if(second<=0){
                $interval.cancel(timerHandler);    //当执行的时间59s,结束时，取消定时器 ，cancle方法取消
                second=59;
                $scope.description="获取验证码";
                $scope.notAllowed = {};// 倒计时完毕后取消变灰和不能点击的效果
            }else{
                $scope.description=second+"s后重发";
                second--;
            }
        }, 1000)   //每一秒执行一次$interval定时器方法
    };

    // 提示三秒消失
    $scope.hiddenMessage = function () {
        var timeout = $timeout(function(){
            $scope.msg = "";
        }, 3000);   // 三秒后执行此方法
    }
});