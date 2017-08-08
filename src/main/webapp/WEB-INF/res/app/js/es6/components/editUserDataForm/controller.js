export default class EditUserDataFormController {
    constructor($scope,
                userService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.userService = userService;
        _ctrl.log = logService;

        this._initData();

    }

    updateUserData() {
        let _ctrl = this;
        _ctrl.userService.updateUser(
            _ctrl.user,
            function () {
                _ctrl.alerts.push({
                    type: 'success',
                    title: 'Great!',
                    message: 'Data saved.'
                });
            },
            function (httpResp) {
                _ctrl.alerts.push({
                    type: 'danger',
                    title: 'Oh snap!',
                    message: httpResp.data.message
                });

                _ctrl.log.error(
                    httpResp.config.method,
                    httpResp.config.url,
                    httpResp.status,
                    httpResp.statusText,
                    httpResp.data.message,
                    httpResp.data.stackTrace
                );
            }
        );
    }

    resetChanges() {
        window.location.reload();
    }

    _initData() {
        let _ctrl = this;
        _ctrl.alerts = [];
    }

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }
}