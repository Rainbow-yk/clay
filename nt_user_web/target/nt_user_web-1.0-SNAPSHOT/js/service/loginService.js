app.service("loginService",function ($http) {

    this.checkSecurityCode = function (securityCode) {
        return $http.get("../login/checkSecurityCode.do?securityCode="+securityCode);
    }
    
});