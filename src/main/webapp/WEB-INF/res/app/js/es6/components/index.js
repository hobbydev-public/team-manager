/* Modules */
import angular from 'angular';

/* Components */
import topNavBar from './topNavBar';

export default angular.module('app.components', [])
    .component('topNavBar', topNavBar)
    .name