app.controller("slideshowController", function ($scope, slideshowService) {

    $scope.getSlideshowInfo = function() {
        slideshowService.getSlideshowInfo().success(function (response) {
            $scope.list = response;
        })
    };

    $scope.findOne = function (id) {
        slideshowService.findOne(id).success(function (response) {
            $scope.entity = response;
        })
    };

    $scope.upload = function (id) {
        var uploadFile = document.getElementById("upload");
        uploadFile.click();
        uploadFile.onchange = function () {
            slideshowService.upload(this.files[0], id).success(function (response) {
                $scope.entity.pictureUrl = response.sliderPicture;
            });
        }
    };

    $scope.save = function (entity) {
        slideshowService.save(entity).success(function (response) {
            if (response.result) {
                alert(response.message);
                $scope.getSlideshowInfo();
            }
        })
    }

});