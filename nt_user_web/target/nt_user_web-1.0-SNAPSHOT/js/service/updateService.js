app.service("updateService", function ($http) {

    this.getCategoryList = function () {
        return $http.get("../category/getAll.do");
    };

    this.findCategoryIds = function (id) {
        return $http.get("../category/findCategoryByWorkId.do?id="+id)
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
    };

    this.findOneById = function (id) {
        return $http.get("../work/findOneById.do?id="+id);
    };

    this.update = function (entity, ids) {
        return $http.post("../work/update.do?ids="+ids, entity);
    };

});