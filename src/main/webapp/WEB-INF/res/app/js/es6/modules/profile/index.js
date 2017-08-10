/* Modules */
import angular from 'angular';

/* Components */
import components from './components';

/* Config */
import routing from './routing';

export default angular.module('app.profile', [components])
    .config(routing)
    .name;