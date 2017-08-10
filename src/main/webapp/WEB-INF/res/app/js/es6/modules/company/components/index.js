/* Modules */
import angular from 'angular';

/* Components */
import officesList from './officesList';


export default angular.module('app.company.components', [])
    .component('officesList', officesList)
    .name;