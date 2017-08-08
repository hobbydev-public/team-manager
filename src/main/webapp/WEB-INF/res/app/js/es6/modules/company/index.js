/* Modules */
import angular from 'angular';

/* Config */
import routing from './routing';

export default angular.module('app.company', [])
    .config(routing)
    .name;