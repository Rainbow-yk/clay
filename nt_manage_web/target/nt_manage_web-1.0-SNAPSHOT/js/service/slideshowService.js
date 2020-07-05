app.service("slideshowService", function ($http) {

    this.getSlideshowInfo = function () {
        return $http.get("../slideshow/getAll.do");
    };

    this.findOne = function (id) {
        return $http.get("../slideshow/findOne.do?id="+id);
    };

    this.upload = function (file, id) {
        var formData = new FormData();
        formData.append('picture', file);
        formData.append("id", id);
        return $http({
            method:'POST',
            url:"../slideshow/changePicture.do", // 后台接口
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        });
    };

    this.save = function (entity) {
        return $http.post("../slideshow/save.do", entity);
    }

});