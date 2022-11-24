# coding: utf-8
from enum import Enum


class BaseCode:

    def __init__(self, code, message, data):
        self.code = code
        self.message = message
        self.data = data

    def getCode(self):
        return self.code

    def getMessage(self):
        return self.message


class SuccessResultCode(BaseCode):
    def __init__(self, data={}):
        super().__init__(
            code=200,
            message="调取成功",
            data=data
        )


class FailedResultCode(BaseCode):
    def __init__(self, data={}):
        super().__init__(
            code=500,
            message="调取失败",
            data=data
        )


class ResultCode(Enum):
    SUCCESS = SuccessResultCode()
    FAILED = FailedResultCode()
