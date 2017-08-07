export default class AddCompanyModalController {
    constructor(companyService) {
        'ngInject';

        this.companyService = companyService;
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
                console.error(httpResp.data.message);
                console.error(httpResp.data.stackTrace);
            }
        );
    }

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }

}