/* Modules */
import angular from 'angular';

/* Modals */
import deleteConfirmation from './deleteConfirmation';

export default angular.module('app.modals', [])
    .component('deleteConfirmationModal', deleteConfirmation)
    .name;