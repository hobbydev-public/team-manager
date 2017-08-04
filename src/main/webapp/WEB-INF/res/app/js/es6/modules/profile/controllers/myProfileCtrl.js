export default class MyProfileCtrl {
    constructor($scope,
                principalService, userService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;

        _ctrl.userService = userService;

        _ctrl.userId = -1;
        _ctrl.user = null;

        principalService.getCurrentUser(function (currentUser) {
            _ctrl.userId = $scope.appContext.currentUser.id;
            _ctrl._initData();
        });
    }

    _initData() {
        let _ctrl = this;

        _ctrl.user = _ctrl.userService.getUserById(_ctrl.userId);
    }
}