app.service('indexService', function ($http) {

    this.getUserInfo = function () {
        return $http.get("../login/getUserInfo.do");
    };

    this.getSlideshowInfo = function () {
        return $http.get("../slideshow/getAll.do");
    };

    this.getInformation = function () {
        return $http.get("../information/getInformation.do");
    }

    this.getAllWork = function () {
        return $http.get("../work/getAll.do")
    };
    
    this.collectWork = function (work) {
        return $http.post("../work/collectWork.do", work);
    };

    this.findWorkByCategoryId = function (id) {
        return $http.get("../work/findWorkByCategoryId.do?id="+id);
    };

    this.search = function (keyword) {
        return $http.get("../search/search.do?keyword="+encodeURIComponent(encodeURIComponent(keyword)));
    };

    this.getMyCollect = function (id) {
        return $http.get("../work/getMyCollect.do?id="+id);
    };

});