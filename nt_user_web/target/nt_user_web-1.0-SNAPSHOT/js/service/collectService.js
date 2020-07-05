app.service("collectService", function ($http) {

    this.getMyCollect = function (id) {
        return $http.get("../work/getMyCollect.do?id="+id);
    };

    this.findOne = function () {
        return $http.get("../login/getUserInfo.do");
    };

    this.deleteWork = function (id) {
        return $http.get("../work/delete.do?id="+id);
    };

    this.deleteCollect = function (id) {
        return $http.get("../work/deleteCollect.do?id="+id);
    };

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

});