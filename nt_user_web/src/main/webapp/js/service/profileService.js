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

        // lrz(file)
        //     .then(function (rst) {
        //         // 处理成功会执行
        //         console.log(rst);
        //         var formdata = new FormData();
        //         formdata.append('avatar', rst.formData);
        //         formData.append("mobile", mobile);
        //         return $http({
        //                 method:'POST',
        //                 url:"../user/changeAvatar.do", // 后台接口
        //                 data: formData,
        //                 headers: {'Content-Type':undefined},
        //                 transformRequest: angular.identity
        //             });
        //     })
        //     .catch(function (err) {
        //         // 处理失败会执行
        //     })
        //     .always(function () {
        //         // 不管是成功失败，都会执行
        //     });
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