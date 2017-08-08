/* Modules */
import angular from 'angular';

/* Module modals */
import modals from './modals';

/* Config */
import routing from './routing';

export default angular.module('app.company', [modals])
    .config(routing)
    .name;