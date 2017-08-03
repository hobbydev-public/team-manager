/* Modules */
import angular from 'angular';
import resource from 'angular-resource';

/* Services */
import principalService from './principalService';

export default angular.module('app.services',
    [
        resource
    ])
    .service('principalService', principalService)
    .name;