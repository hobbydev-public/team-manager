/* Modules */
import angular from 'angular';

/* Components */
import topNavBar from './topNavBar';
import editUserDataForm from './editUserDataForm';

export default angular.module('app.components', [])
    .component('topNavBar', topNavBar)
    .component('editUserDataForm', editUserDataForm)
    .name