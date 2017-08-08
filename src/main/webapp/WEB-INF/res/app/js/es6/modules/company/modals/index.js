/* Modules */
import angular from 'angular';

/* Modals */
import editCompany from './editCompany';


export default angular.module('app.company.modals', [])
    .component('editCompanyModal', editCompany)
    .name;