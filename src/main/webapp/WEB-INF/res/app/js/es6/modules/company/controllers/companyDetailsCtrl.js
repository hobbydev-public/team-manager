export default class CompanyDetailsCtrl {
    constructor($scope,
                $uibModal,
                $location,
                companyService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;

        _ctrl.modalProvider = $uibModal;
        _ctrl.location = $location;
        _ctrl.companyService = companyService;
        _ctrl.log = logService;

        _ctrl._initData();
    }

    openEditCompanyAccountModal() {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'editCompanyModal',
            resolve: {
                company: function () {
                    return _ctrl.companyService.getCompanyAccount(
                        function (company) {},
                        function (httpResp) {
                            //fail

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
            }
        });

        modal.result.then(
            function (success) {
                // on close
                if(success) {
                    window.location.reload();
                }
            },
            function () {
                // on dismiss
            }
        );
    }

    openDeleteCompanyAccountModal() {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'deleteConfirmationModal',
            resolve: {
                message: function () {
                    return 'You are about to delete the following company account:';
                },
                resource: function () {
                    return _ctrl.companyService.getCompanyAccount(
                        function (company) {
                            company.owner.id = '';
                        },
                        function (httpResp) {
                            //fail

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
            }
        });

        modal.result.then(
            function (success) {
                // on close
                if(success) {
                    _ctrl.location.path('/');
                    window.location.reload();
                }
            },
            function () {
                // on dismiss
            }
        );
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