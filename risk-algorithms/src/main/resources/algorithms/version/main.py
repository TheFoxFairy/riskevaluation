import json
import random
with open("./指标体系.json", "r", encoding="UTF-8") as fp:
    indicators = json.load(fp)
level1_indicators = []
for i in range(len(indicators["data"][0])):
    Curdata1 = indicators["data"][0][i]
    level1_indicators.append({})
    level1_indicators[i]["indexId"] = Curdata1["indexId"]
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
                level4_indicators[-1]["indexId"] = Curdata4["indexId"]
                level5_indicators = []
                for p in range(len(Curdata4["children"])):
                    Curdata5 = Curdata4["children"][p]
                    # level5_indicators.append({})
                    # 判断是否异常
                    # print(Curdata5["indexName"])
                    # print(Curdata5["indexId"])
                    level5_indicators.append({})
                    if random.random() > 0.5:
                        level5_indicators[p]["indexId"] = Curdata5["indexId"]
                        level5_indicators[p]["观测指标是否异常"] = "异常"
                    else:
                        level5_indicators[p]["indexId"] = Curdata5["indexId"]
                        level5_indicators[p]["观测指标是否异常"] = "正常"
                level4_indicators[-1]["观测指标"] = level5_indicators


    level1_indicators[i]["四级指标"] = level4_indicators

output = {"msg": "success",
          "data":level1_indicators}

filename='测试结果.json'
with open(filename,'w') as file_obj:
    json.dump(output,file_obj)