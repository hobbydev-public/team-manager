/* Modules */
import angular from 'angular';

/* Modals */
import deleteConfirmation from './deleteConfirmation';
import changePassword from './changePassword';

export default angular.module('app.modals', [])
    .component('deleteConfirmationModal', deleteConfirmation)
    .component('changePasswordModal', changePassword)
    .name;