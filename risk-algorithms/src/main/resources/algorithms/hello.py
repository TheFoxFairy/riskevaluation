# coding: utf-8

import sys

from api.CommonResult import CommonResult


class Algorithm1:

    def __init__(self):
        self.commonResult = CommonResult()

    # 连接数据库
    def connect(self):
        pass

    # 从数据库中读取数据
    def read(self):
        pass

    # 解析数据
    def parse(self):
        pass

    # 主程序入口
    def run(self, *args, **kwargs):
        data = "测试"
        result = self.commonResult.success(data=data)
        print(result)


args = sys.argv  # 获取参数
algorithm1 = Algorithm1()
algorithm1.run(args[1])
