# coding: utf-8

import sys, redis,requests,pymysql
import json
import random,config

from api.CommonResult import CommonResult


class Algorithm1:

    def __init__(self):
        self.commonResult = CommonResult()

    def create_mysql(self,index_id):
        # 打开数据库连接
        db = pymysql.connect(host='localhost',
                             user='root',
                             password='123456',
                             database='indicator')

        # 使用 cursor() 方法创建一个游标对象 cursor
        cursor = db.cursor()

        # 使用 execute()  方法执行 SQL 查询
        cursor.execute('SELECT index_name from indexes where index_id = {}'.format(index_id))

        # 使用 fetchone() 方法获取单条数据.
        data = cursor.fetchone()

        # print("Database version : %s " % data[0], type(data[0]))

        # 关闭数据库连接
        db.close()

        return data[0]

    # 读取数据
    def read(self):
        data = requests.get(config.URL).json()
        return data

    # 解析数据
    def parse(self, indicators):
        level1_indicators = []
        for i in range(len(indicators["data"][0])):
            Curdata1 = indicators["data"][0][i]
            level1_indicators.append({})
            level1_indicators[i]["指标名称"] = Curdata1["indexId"]
            # level1_indicators[i]["指标等级"] = "一级指标"
            if random.random() < 0.3:
                level1_indicators[i]["风险等级"] = "低"
                # level1_indicators[i][Curdata1["indexId"]] = {"风险等级":"低"}
            elif random.random() > 0.7:
                level1_indicators[i]["风险等级"] = "中"
            else:
                level1_indicators[i]["风险等级"] = "高"
            level4_indicators = []
            for j in range(len(Curdata1["children"])):
                Curdata2 = Curdata1["children"][j]
                for k in range(len(Curdata2["children"])):
                    Curdata3 = Curdata2["children"][k]
                    for l in range(len(Curdata3["children"])):
                        Curdata4 = Curdata3["children"][l]
                        level4_indicators.append({})
                        level4_indicators[-1]["指标名称"] = Curdata4["indexId"]
                        level5_indicators = []
                        for p in range(len(Curdata4["children"])):
                            Curdata5 = Curdata4["children"][p]
                            # level5_indicators.append({})
                            # 判断是否异常
                            # print(Curdata5["indexName"])
                            # print(Curdata5["indexId"])
                            level5_indicators.append({})
                            if random.random() > 0.5:
                                level5_indicators[p]["指标名称"] = Curdata5["indexId"]
                                level5_indicators[p]["观测指标是否异常"] = "异常"
                            else:
                                level5_indicators[p]["指标名称"] = Curdata5["indexId"]
                                level5_indicators[p]["观测指标是否异常"] = "正常"
                        level4_indicators[-1]["观测指标"] = level5_indicators
            level1_indicators[i]["四级指标"] = level4_indicators

        data = []
        output = {"msg": "success",
                      "data": level1_indicators}

        for i in range(len(level1_indicators)):

            indicator1 = {
                'indexId': level1_indicators[i]["指标名称"],
                '风险等级': level1_indicators[i]["风险等级"]
            }

            data.append(indicator1)

            for j in range(len(level1_indicators[i]["四级指标"])):
                for k in range(len(level1_indicators[i]['四级指标'][j]['观测指标'])):

                    if level1_indicators[i]['四级指标'][j]['观测指标'][k]['观测指标是否异常'] != '正常':
                        indicator4 = {
                            'indexId':level1_indicators[i]['四级指标'][j]['观测指标'][k]['指标名称'],
                            '指标状态':level1_indicators[i]['四级指标'][j]['观测指标'][k]['观测指标是否异常']
                        }

                        data.append(indicator4)

        return data

    def save(self,data):
        # 打开数据库连接
        db = pymysql.connect(host='localhost',
                             user='root',
                             password='123456',
                             database='indicator')

        # 使用 cursor() 方法创建一个游标对象 cursor
        cursor = db.cursor()

        cursor.execute('TRUNCATE TABLE error_index')
        # db.commit()

        # 使用 execute()  方法执行 SQL 查询
        for i in range(len(data)):
            indexId = data[i]['indexId']
            level = data[i].get('风险等级')
            status = data[i].get('指标状态')

            if level is None:
                level = ''
            if status is None:
                status = ''

            # print(indexId,level,status,sep=':')

            cursor.execute('INSERT INTO error_index (index_id, level,status) VALUE (%d, %s, %s)' % (indexId, repr(level), repr(status)))

            db.commit()

        # 使用 fetchone() 方法获取单条数据.
        # data = cursor.fetchone()

        # print("Database version : %s " % data[0], type(data[0]))

        # 关闭数据库连接
        db.close()

    # 主程序入口
    def run(self):
        data = self.read()
        output = self.parse(data)
        self.save(output)


algorithm1 = Algorithm1()
algorithm1.run()
