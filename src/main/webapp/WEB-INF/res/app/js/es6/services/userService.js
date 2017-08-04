export default class UserService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/users/:userId',
            {userId: '@id'}
        );
    }

    /**
     * Retrieves information about currently logged in user.
     *
     * @param callback function to be executed when data is ready.
     * Current user data will be passed as a first parameter of callback function.
     */
    getUserById(userId) {
        let _service = this;
        let user = {};

        _service.res.get(
            {userId:userId},
            function (userResponseObject) {
                console.log(userResponseObject);
            }
        );

    }
}