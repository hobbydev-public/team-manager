/* Modules */
import angular from 'angular';

/* additional submodules */
import components from './components';
import modals from './modals';

/* Config */
import routing from './routing';

export default angular.module('app.profile', [components, modals])
    .config(routing)
    .name;