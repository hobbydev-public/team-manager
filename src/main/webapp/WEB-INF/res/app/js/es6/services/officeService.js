export default class OfficeService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/companies/:companyId/offices/:officeId',
            {
                companyId: '@company.id',
                officeId: '@id'
            },
            {
                update: {
                    method: 'PUT',
                    params: {
                        companyId: 'account'
                    }
                }
            }
        );
    }

    getCompanyAccountOffices(success, fail) {
        let _service = this;
        return _service.res.get(
            {
                companyId: 'account'
            },
            success,
            fail
        );
    }

    getOffices(companyId, success, fail) {
        let _service = this;
        return _service.res.get(
            {
                companyId: companyId
            },
            success,
            fail
        );
    }

    addOffice(name, success, fail) {
        let _service = this;

        _service.res.save(
            {
                companyId: 'account',
                name:name
            },
            {},
            success,
            fail
        );
    }

    editOffice(officeResource, success, fail) {
        officeResource.$update(
            {
                companyId: 'account'
            },
            success,
            fail
        );
    }
}