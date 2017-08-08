export default class DeleteConfirmationModalController {
    constructor(logService) {
        'ngInject';

        this.alerts = [];
        this.log = logService;
    }

    dismiss() {
        this.modalInstance.dismiss();
    }

    delete() {
        let _ctrl = this;
        _ctrl.resolve.resource.$delete(
            {},
            function () {
                //success
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