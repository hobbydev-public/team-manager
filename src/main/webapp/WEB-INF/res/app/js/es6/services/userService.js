export default class UserService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/users/:userId',
            {userId: '@id'},
            {
                update: {
                    method: 'PUT',
                    params: {userId: '@id'}
                }
            }
        );
    }

    /**
     * <p>Retrieves user by its ID.</p>
     *
     * @param userId - id of a user
     * @param success - success callback.
     * Success callback is called with the following arguments
     * <ul>
     *     <li>(value (Object|Array)</li>
     *     <li>responseHeaders (Function)</li>
     *     <li>status (number)</li>
     *     <li>statusText (string))</li>
     * </ul>
     * where the value is the populated resource instance or collection object.
     * @param fail - fail callback. Callback is called with (httpResponse) argument.
     */
    getUserById(userId, success, fail) {
        let _service = this;

        return _service.res.get(
            {userId:userId},
            success,
            fail
        );
    }

    updateUser(userResource, success, fail) {
        userResource.$update(
            {},
            success,
            fail
        );
    }
}