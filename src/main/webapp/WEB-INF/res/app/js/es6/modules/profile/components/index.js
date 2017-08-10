/* Modules */
import angular from 'angular';

/* Components */
import editUserDataForm from './editUserDataForm';

export default angular.module('app.profile.components', [])
    .component('editUserDataForm', editUserDataForm)
    .name