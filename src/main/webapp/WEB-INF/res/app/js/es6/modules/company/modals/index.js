/* Modules */
import angular from 'angular';

/* Modals */
import addCompany from './addCompany';
import editCompany from './editCompany';


export default angular.module('app.company.modals', [])
    .component('addCompanyModal', addCompany)
    .component('editCompanyModal', editCompany)
    .name;