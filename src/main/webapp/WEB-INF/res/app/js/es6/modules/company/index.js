/* Modules */
import angular from 'angular';

/* Module modals and components */
import modals from './modals';
import components from './components';

/* Config */
import routing from './routing';

export default angular.module('app.company', [modals, components])
    .config(routing)
    .name;