app.service("workService", function ($http) {

    this.getAllWork = function () {
        return $http.get("../work/getAll.do")
    };

    this.getInformation = function () {
        return $http.get("../information/getInformation.do");
    };

    this.updateInformation = function (information) {
        return $http.post("../information/updateInformation.do", information);
    };

    this.deleteWork = function (id) {
        return $http.get("../work/delete.do?id="+id);
    };

    this.searchWorks = function (keyword) {
        return $http.get("../work/search.do?keyword="+encodeURIComponent(encodeURIComponent(keyword)))
    };

    this.upload = function (file) {
        var formData = new FormData();
        formData.append('picture', file);
        return $http({
            method:'POST',
            url:"../information/changePicture.do", // 后台接口
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });
    };

});