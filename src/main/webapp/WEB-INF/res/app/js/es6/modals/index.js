/* Modules */
import angular from 'angular';

/* Modals */
import deleteConfirmation from './deleteConfirmation';
import addCompany from './addCompany';
import changePassword from './changePassword';

export default angular.module('app.modals', [])
    .component('deleteConfirmationModal', deleteConfirmation)
    .component('addCompanyModal', addCompany)
    .component('changePasswordModal', changePassword)
    .name;