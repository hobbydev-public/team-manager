/* Modules */
import angular from 'angular';

/* Modals */
import addProject from './addProject';


export default angular.module('app.project.modals', [])
    .component('addProjectModal', addProject)
    .name;