/* Modules */
import angular from 'angular';

/* Config */
import routing from './routing';

export default angular.module('app.dashboard', [])
    .config(routing)
    .name;