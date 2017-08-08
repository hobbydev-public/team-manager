export default class AddCompanyModalController {
    constructor(companyService,
                logService) {
        'ngInject';

        this.companyService = companyService;
        this.log = logService;
        this.alerts = [];
    }

    dismiss() {
        this.modalInstance.dismiss();
    }

    add() {
        let _ctrl = this;
        _ctrl.companyService.addCompanyAccount(
            _ctrl.name,
            function () {
                _ctrl.modalInstance.close(true);
            },
            function (httpResp) {
                //fail
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