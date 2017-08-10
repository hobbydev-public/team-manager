/* Modules */
import angular from 'angular';

/* Modals */
import changePassword from './changePassword';

export default angular.module('app.profile.modals', [])
    .component('changePasswordModal', changePassword)
    .name;