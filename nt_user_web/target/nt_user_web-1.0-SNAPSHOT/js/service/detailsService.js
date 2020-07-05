app.service("detailsService", function ($http) {

    this.getInfo = function (id) {
        return $http.get("../work/getWorkInfo.do?id="+id);
    }

});