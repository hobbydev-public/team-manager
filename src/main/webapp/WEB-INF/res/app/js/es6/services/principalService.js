export default class PrincipalService {
    constructor($resource) {
        'ngInject';

        this.res = $resource('api/web/principal');
        this.principal = null;
    }

    /**
     * Retrieves information about currently logged in user.
     *
     * @param callback function to be executed when data is ready.
     * Current user data will be passed as a first parameter of callback function.
     */
    getCurrentUser(callback) {
        let _service = this;

        // TODO think about better way to mitigate repeating calls.
        if(_service.principal == null) {
            _service.res.get(
                {},
                function (principal) {
                    _service.principal = principal;
                    callback(_service.principal);
                }
            );
        } else {
            callback(_service.principal);
        }
    }
}