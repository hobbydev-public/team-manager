export default class OfficesListController {
    constructor($scope,
                $uibModal,
                officeService,
                logService) {
        'ngInject';

        $scope.ctrl = this; // after this assignment, controller instance is available in template either by 'ctrl' or by alias, defined in 'controllerAs'
        let _ctrl = this;
        let rootScope = $scope.$root;

        _ctrl.modalProvider = $uibModal;
        _ctrl.officeService = officeService;
        _ctrl.log = logService;

        this._initData();

    }

    openRemoveOfficeModal(officeId, companyId) {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'deleteConfirmationModal',
            resolve: {
                message: function () {
                    return 'You are about to delete the following office:';
                },
                resource: function () {
                    return _ctrl.officeService.getOfficeById(
                        officeId,
                        companyId,
                        function () {},
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

    openEditOfficeModal(officeId, companyId) {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'editOfficeModal',
            resolve: {
                office: function () {
                    return _ctrl.officeService.getOfficeById(
                        officeId,
                        companyId,
                        function () {},
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

    openAddOfficeModal() {
        let _ctrl = this;

        let modal = _ctrl.modalProvider.open({
            component: 'addOfficeModal'
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

    _initData() {
        let _ctrl = this;
        _ctrl.alerts = [];

        _ctrl.offices = _ctrl.officeService.getCompanyAccountOffices(
            function () {},
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

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }
}