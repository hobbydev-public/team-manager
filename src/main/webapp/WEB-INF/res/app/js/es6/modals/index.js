/* Modules */
import angular from 'angular';

/* Modals */
import deleteConfirmation from './deleteConfirmation';
import addCompany from './addCompany';

export default angular.module('app.modals', [])
    .component('deleteConfirmationModal', deleteConfirmation)
    .component('addCompanyModal', addCompany)
    .name;