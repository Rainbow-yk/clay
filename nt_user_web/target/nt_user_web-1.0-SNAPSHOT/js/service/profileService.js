app.service("profileService", function ($http) {

    this.upload = function (file, mobile) {
        var formData = new FormData();
        formData.append('avatar', file);
        formData.append("mobile", mobile);
        return $http({
            method:'POST',
            url:"../user/changeAvatar.do", // 后台接口
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });
    };

    this.findOne = function () {
        return $http.get("../login/getUserInfo.do");
    };

    this.getMyWorks = function (id) {
        return $http.get("../work/getMyWorks.do?id="+id);
    };

    this.deleteWork = function (id) {
        return $http.get("../work/delete.do?id="+id);
    };

    this.updateUserInfo = function (user) {
        return $http.post("../user/update.do", user);
    };

});