app.service('indexService', function ($http) {

    this.deleteUser = function (ids) {
        return $http.post("../user/delete.do", ids)
    };

    this.findPage = function (pageNum, pageSize, keyword) {
        return $http.get("../user/findPage.do?pageNum="+pageNum+"&pageSize="+pageSize+"&keyword="+encodeURIComponent(encodeURIComponent(keyword)))
    };

});