export default class LogService {
    constructor($resource) {
        'ngInject';

        this.res = $resource(
            'api/web/logs/:level',
            {},
            {
                warn: {
                    method: 'POST',
                    params: {level: 'WARN'}
                },
                error: {
                    method: 'POST',
                    params: {level: 'ERROR'}
                }
            }
        );
    }

    warn(method, url, sCode, sText, message, stackTraceArray) {
        let _service = this;

        _service.res.warn(
            {},
            {
                method: method,
                url: url,
                statusCode: sCode,
                statusText: sText,
                message: message,
                stackTrace: stackTraceArray
            },
            function () {},
            function () {}
        );
    }

    error(method, url, sCode, sText, message, stackTraceArray) {
        let _service = this;

        _service.res.error(
            {},
            {
                method: method,
                url: url,
                statusCode: sCode,
                statusText: sText,
                message: message,
                stackTrace: stackTraceArray
            },
            function () {},
            function () {}
        );
    }
}