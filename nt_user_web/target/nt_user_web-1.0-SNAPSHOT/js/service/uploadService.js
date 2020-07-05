app.service("uploadService", function ($http) {

    this.issue = function (entity, ids) {
        return $http.post("../work/issue.do?ids="+ids, entity);
    };

    this.getCategoryList = function () {
        return $http.get("../category/getAll.do");
    };

    this.upload = function (file) {
        var formData = new FormData();
        formData.append('cover', file);
        return $http({
            method:'POST',
            url:"../work/saveCover.do", // 后台接口
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });
    }



});