export default class ChangePasswordController {
    constructor(userService) {
        'ngInject';

        this.userService = userService;
        this.alerts = [];
    }

    dismiss() {
        this.modalInstance.dismiss();
    }

    change() {
        let _ctrl = this;
        _ctrl.userService.changePassword(
            _ctrl.oldPassword,
            _ctrl.newPassword,
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