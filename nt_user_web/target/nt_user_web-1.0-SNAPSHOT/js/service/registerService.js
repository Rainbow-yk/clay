app.service('registerService', function ($http) {

    // 注册
    this.register = function (entity, securityCode) {
        return $http.post("../register/register.do?securityCode="+securityCode, entity)
    }

    // 请求后台发送验证码
    this.sendSecurityCode = function (mobile) {
        return $http.get("../register/sendSecurityCode.do?mobile="+mobile)
    }
});