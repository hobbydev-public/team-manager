import ctrl from './controller';

export default {
    controller: ctrl,
    template: require('./template.html'),
    bindings: {
        modalInstance: '='
    }
}