export default class MyProfileCtrl {
    constructor($scope,
                $uibModal,
                principalService,
                userService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;

        _ctrl.userService = userService;
        _ctrl.modalProvider = $uibModal;

        _ctrl.userId = -1;
        _ctrl.user = null;

        principalService.getCurrentUser(function (currentUser) {
            _ctrl.userId = currentUser.id;
            _ctrl._initData();
        });
    }

    openChangePasswordModal() {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'changePasswordModal',
        });

        modal.result.then(
            function (success) {
                // on close
                if(success) window.location.reload();
            },
            function () {
                // on dismiss
            }
        );
    }

    _initData() {
        let _ctrl = this;

        _ctrl.user = _ctrl.userService.getUserById(
            _ctrl.userId,
            function (user) {
                
            },
            function (httpResp) {
                console.error(httpResp.data.message);
                console.error(httpResp.data.stackTrace);
            }
        );
    }
}