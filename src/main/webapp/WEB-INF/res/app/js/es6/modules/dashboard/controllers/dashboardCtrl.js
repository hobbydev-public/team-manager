export default class DashboardCtrl {
    constructor($scope,
                principalService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;

        principalService.getCurrentUser(function (currentUser) {
            _ctrl.principal = currentUser;
            _ctrl._initData();
        });
    }

    _initData() {
        let _ctrl = this;
    }
}