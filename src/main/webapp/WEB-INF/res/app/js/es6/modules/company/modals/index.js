/* Modules */
import angular from 'angular';

/* Modals */
import addCompany from './addCompany';
import editCompany from './editCompany';
import addOffice from './addOffice';


export default angular.module('app.company.modals', [])
    .component('addCompanyModal', addCompany)
    .component('editCompanyModal', editCompany)
    .component('addOfficeModal', addOffice)
    .name;