export default class ManageCompanyCtrl {
    constructor($scope,
                companyService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;

        _ctrl.companyService = companyService;
        _ctrl.log = logService;

        _ctrl._initData();
    }

    _initData() {
        let _ctrl = this;
        _ctrl.alerts = [];
        _ctrl.companyExists = undefined;

        _ctrl.company = _ctrl.companyService.getCompanyAccount(
            function (company) {
                _ctrl.companyExists = true;
            },
            function (httpResp) {
                //fail
                if(httpResp.status == 404) {
                    _ctrl.companyExists = false;
                }

                _ctrl.alerts.push({
                    type: 'warning',
                    title: 'Warning!',
                    message: httpResp.data.message
                });

                _ctrl.log.warn(
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

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }
}