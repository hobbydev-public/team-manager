export default class DeleteConfirmationModalController {
    constructor() {
        'ngInject';

        this.alerts = [];
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
            }
        );
    }

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }

}