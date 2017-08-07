export default class TopNavBarController {
    constructor($scope,
                /*$uibModal,*/
                principalService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        //this.modalProvider = $uibModal;
        principalService.getCurrentUser(function (currentUser) {
            _ctrl.principal = currentUser;
            _ctrl._initData();
        });
    }

    _initData() {
        let _ctrl = this;
    }
}