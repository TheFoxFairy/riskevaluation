# coding: utf-8
from api.ResultCode import ResultCode


class CommonResult:

    def __init__(self, code=None, message=None, data={}):
        self.code = code
        self.message = message
        self.data = data

    def _toJson(self, code=None, message=None, data={}):
        return {
            "code": self.code,
            "message": self.message,
            "data": self.data
        }

    def success(self, code=ResultCode.SUCCESS.value.getCode(), message=ResultCode.SUCCESS.value.getMessage(), data={}):
        self.code = code
        self.message = message
        self.data = data
        return self._toJson(code, message, data)

    def failed(self, code=ResultCode.FAILED.value.getCode(), message=ResultCode.FAILED.value.getMessage(), data={}):
        self.code = code
        self.message = message
        self.data = data
        return self._toJson(code, message, data)
