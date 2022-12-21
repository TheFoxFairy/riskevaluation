package com.gov.risk.indicator.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gov.risk.indicator.entity.CollingEntity;
import com.gov.risk.indicator.entity.CollingIndexesEntity;
import com.gov.risk.indicator.entity.IndexPropertyEntity;
import com.gov.risk.indicator.entity.IndexesEntity;
import com.gov.risk.indicator.service.CollingIndexesService;
import com.gov.risk.indicator.service.CollingService;
import com.gov.risk.indicator.service.IndexPropertyService;
import com.gov.risk.indicator.service.IndexesService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class ExcelUtils {

    static class ReadExcel {

        private final List<String> collings = new ArrayList<>();

        public HashMap<String, Integer> getCollings() {

            HashMap<String, Integer> collingsMap = new HashMap<>();

            int count = 0;
            for (String colling : collings) {
                if (!collingsMap.containsKey(colling)) {
                    collingsMap.put(colling, count++);
                }
            }

            return collingsMap;
        }

        public List<Map<Integer, String>> read(String fileName) {

            // 接收结果集，为一个List列表，每个元素为一个map对象，key-value对为excel中每个列对应的值
            List<Map<Integer, String>> resultList = new ArrayList<>();

            EasyExcel.read(fileName, new AnalysisEventListener<Map<Integer, String>>() {

                @Override
                public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
//                    System.out.println("解析到一条数据：" + JSON.toJSONString(map));
                    resultList.add(map);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//                    System.out.println("excel文件解析完毕！" + JSON.toJSONString(resultList));
                }
            }).sheet().headRowNumber(2).doRead();

            return resultList;
        }

        public TreeMap<String, Object> toDict(List<Map<Integer, String>> mapList) {

            TreeMap<String, Object> results = new TreeMap<>();
            TreeMap<Integer, String> record = new TreeMap<>();

            for (Map<Integer, String> row : mapList) {
                for (int j = 0; j < row.size(); j++) {
                    if ((j < 5 || j == 6) && row.get(j) == null)
                        row.put(j, record.get(j));
                }


                results = this.mergeMap(row, results);
//                System.out.println(count++ + ":" + row); // 这里可以看到每一行观测指标的数据

                this.parseCollings(row.get(6));

                for (int j = 0; j < row.size(); j++) {
                    if (j < 5 || j == 6)
                        record.put(j, row.get(j));
                }

            }

            return results;
        }

        private void parseCollings(String s) {
            int start = -1;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '《') {
                    start = i;
                } else if (s.charAt(i) == '》') {
//                    System.out.println(s.substring(start,i+1));
                    this.collings.add(s.substring(start, i + 1));
                }
            }
        }

        private List<String> parseCollingsByList(String s) {
            int start = -1;
            List<String> collingsList = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '《') {
                    start = i;
                } else if (s.charAt(i) == '》') {
//                    System.out.println(s.substring(start,i+1));
                    collingsList.add(s.substring(start, i + 1));
                }
            }
            return collingsList;
        }

        private TreeMap<String, Object> mergeMap(Map<Integer, String> row, TreeMap<String, Object> results) {
            TreeMap<String, Object> head = results;
            int i = 0;
            String key;
            while (i < 4) { // 合并前5个指标
                key = row.get(i++);

                if (results.isEmpty() || !results.containsKey(key)) {
                    results.put(key, new TreeMap<>());
                }
                results = (TreeMap<String, Object>) results.get(key);
            }

            key = row.get(4);
            if (results.isEmpty() || !results.containsKey(key)) {
                results.put(key, new TreeMap<>());
            }

            TreeMap<Object, Object> indexMap = new TreeMap<>();

            // 合并几个指标
            indexMap.put("标准值", row.get(5));
            indexMap.put("指标依据", this.parseCollingsByList(row.get(6)));
            indexMap.put("标准值补充/修正", row.get(7));
            indexMap.put("可备注", row.get(8));

            results.put(key, indexMap);

            return head;
        }

    }

    private final ReadExcel readExcel;

    @Autowired
    CollingService collingService;

    @Autowired
    IndexesService indexesService;

    @Autowired
    CollingIndexesService collingIndexesService;

    @Autowired
    IndexPropertyService indexPropertyService;

    private List<CollingIndexesEntity> collingIndexesEntities = new ArrayList<>(); // 后续不需要修改
    private List<CollingEntity> collingEntities = new ArrayList<>(); // 后续不需要修改
    private List<IndexesEntity> indexesEntities = new ArrayList<>(); // 后续不需要修改
    private HashMap<Integer, String> levelMap = new HashMap<>();

    private HashSet<Integer> indexHashSet = new HashSet<>();

    private int count = 0; // 用于赋予ID

    {
        this.levelMap.put(0, "一级指标");
        this.levelMap.put(1, "二级指标");
        this.levelMap.put(2, "三级指标");
        this.levelMap.put(3, "四级指标");
        this.levelMap.put(4, "观测指标");
    }

    private List<IndexPropertyEntity> indexPropertyEntities = new ArrayList<>(); // 后续新增表，只需要修改该处

    public ExcelUtils() {
        this.readExcel = new ReadExcel();
    }


    public int parseStringForOrder(String str) {
        char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};

        int index = -1;

        for (int i = 0; i < cnArr.length; i++) {
            if (cnArr[i] == str.charAt(0)) index = i + 1;
        }

        if (index == -1) {

            StringBuilder s = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {

                if (str.charAt(i) == ' ' || str.charAt(i) == '.') continue;

                if (str.charAt(i) <= '9' && str.charAt(i) >= '0') {
                    s.append(str.charAt(i));
                } else {
                    break;
                }
            }
            if (s.length() != 0)
                index = Integer.parseInt(s.toString());
        }

        return index;
    }

    private List<String> parseCollingsByList(String s) {
        return readExcel.parseCollingsByList(s);
    }


    public void parse(TreeMap<String, Object> objectMap, HashMap<String, Integer> collings) {

        String uuid = String.valueOf(UUID.randomUUID());
        Date createTime = new Date();

        getCollingsToList(collings, createTime);
        parseDfs(objectMap, collings, uuid, createTime, 0, -1);

    }

    public void getCollingsToList(HashMap<String, Integer> collings, Date createTime) {
        for (String collingName : collings.keySet()) {
            Integer collingId = collings.get(collingName);
            CollingEntity collingEntity = new CollingEntity();
            collingEntity.setCollingId(collingId);
            collingEntity.setCollingName(collingName);
            collingEntity.setCreateTime(createTime);

            collingEntities.add(collingEntity);
        }
    }

    private void parseDfs(TreeMap<String, Object> objectMap, HashMap<String, Integer> collings, String uuid, Date createTime, int level, int parentId) {
        if (level > 8) return;
        if (level <= 4) {
            for (String key : objectMap.keySet()) {

                int id = ++count;

                IndexesEntity indexesEntity = new IndexesEntity();
                indexesEntity.setIndexId(id);
                indexesEntity.setCreateTime(createTime);
                indexesEntity.setIndexName(key);
                indexesEntity.setIndexParentId(parentId);
                indexesEntity.setIndexType(this.levelMap.get(level));

                if (indexHashSet.contains(id)) {
                    System.out.println("重复了" + ":" + indexesEntity);
                }

                indexHashSet.add(id);

                System.out.println(indexHashSet.size() + ":" + indexesEntity.toString());


                indexesEntities.add(indexesEntity);

                parseDfs((TreeMap<String, Object>) objectMap.get(key), collings, uuid, createTime, level + 1, id);
            }
        } else {
            // 合并几个指标
            Object standardValue = objectMap.get("标准值");
            Object standardValueSupplement = objectMap.get("标准值补充/修正");
            Object extra = objectMap.get("可备注");

            List<String> subCollings = (List<String>) objectMap.get("指标依据");

            IndexPropertyEntity indexPropertyEntity = new IndexPropertyEntity();
            indexPropertyEntity.setIndexId(parentId);
            indexPropertyEntity.setExtra((String) extra);
            indexPropertyEntity.setIdentification(uuid);
            indexPropertyEntity.setCreateTime(createTime);
            indexPropertyEntity.setStandardValue((String) standardValue);
            indexPropertyEntity.setStandardValueSupplement((String) standardValueSupplement);

            indexPropertyEntities.add(indexPropertyEntity);

            this.getCollingIndexesEntity(collings, subCollings, parentId, createTime);

        }

    }

    private void getCollingIndexesEntity(HashMap<String, Integer> collings, List<String> subCollings, int parentId, Date createTime) {
        for (String collingName : subCollings) {
            Integer collingId = collings.get(collingName);

//            CollingEntity collingEntity = new CollingEntity();
//            collingEntity.setCollingId(collingId);
//            collingEntity.setCollingName(collingName);
//            collingEntity.setCreateTime(createTime);

            // 将parentId 与 coolingId 组装
            CollingIndexesEntity collingIndexesEntity = new CollingIndexesEntity();
            collingIndexesEntity.setCollingId(collingId);
            collingIndexesEntity.setIndexId(parentId);

            collingIndexesEntities.add(collingIndexesEntity);

        }
    }


    public void run(String filename, boolean flag) {
        List<Map<Integer, String>> mapList = readExcel.read(filename);
        TreeMap<String, Object> objectMap = readExcel.toDict(mapList);
        HashMap<String, Integer> collings = readExcel.getCollings();

//        System.out.println(collings);

        this.parse(objectMap, collings);

        // 开始存入数据库
        this.saveToDB(flag);

//        for (String key:objectMap.keySet()) {
//            System.out.println("=====================");
////            System.out.println(key + ":" + objectMap.get(key));
//            System.out.println(JSON.toJSON(objectMap.get(key)));
////            System.out.println(objectMap.get(key));
//            System.out.println("=====================");
//        }

    }

    public void run(String filename) {
        Map<String, Object> params = new HashMap<>();
        if (indexesService.listWithTree(params).isEmpty())
            this.run(filename, true);
        else
            this.run(filename, false);
    }

    private void saveToDB(boolean flag) {
        if (flag) { // 或者运行一次后，将这里直接屏蔽
            this.saveCollingsToDB();
            this.saveCollingIndexesToDB();
            this.saveIndexesToDB();
        }
        this.saveIndexPropertyToDB();
    }

    private void saveIndexPropertyToDB() {
        System.out.println("开始存储indexProperty关系表，数据大小为：" + this.indexPropertyEntities.size());
        for (IndexPropertyEntity indexPropertyEntity : this.indexPropertyEntities) {
//            HashMap<String,Object> params = new HashMap<>();
//            params.put("identification",indexPropertyEntity.getIdentification());
//            params.put("indexId",indexPropertyEntity.getIndexId());
//            if(indexPropertyService.queryPage(params).getTotalCount() == 0)
            indexPropertyService.save(indexPropertyEntity);
        }
    }

    private void saveCollingIndexesToDB() { // collings-indexes
        System.out.println("开始存储collings-indexes关系表，数据大小为：" + this.collingIndexesEntities.size());
//        System.out.println(this.collingIndexesEntities);
        for (CollingIndexesEntity collingIndexesEntity : this.collingIndexesEntities) {
//            HashMap<String,Object> params = new HashMap<>();
//            params.put("collingId",collingIndexesEntity.getCollingId());
//            params.put("indexId",collingIndexesEntity.getIndexId());
//            if(collingIndexesService.queryPage()) {
            collingIndexesService.save(collingIndexesEntity);
//            }
        }
    }

    private void saveIndexesToDB() { // indexes
        System.out.println("开始存储indexes关系表，数据大小为：" + this.indexesEntities.size());
        for (IndexesEntity indexesEntity : this.indexesEntities) {
//            HashMap<String,Object> params = new HashMap<>();
//            params.put("indexName",indexesEntity.getIndexName());
//            params.put("indexId",indexesEntity.getIndexId());
//            params.put("indexType",indexesEntity.getIndexType());
            if (indexesService.getById(indexesEntity.getIndexId()) == null) {
                indexesService.save(indexesEntity);
            }
        }
    }

    private void saveCollingsToDB() { // collings
        System.out.println("开始存储collings关系表，数据大小为：" + this.collingEntities.size());
        for (CollingEntity collingEntity : this.collingEntities) {
//            HashMap<String,Object> params = new HashMap<>();
//            params.put("collingName",collingEntity.getCollingName());
//            if(collingService.queryPage(params).getTotalCount() == 0) {
            collingService.save(collingEntity);
//            }
        }
    }


//    public static void main(String[] args) throws FileNotFoundException {
//        ExcelUtils excelParse = new ExcelUtils();
//        File file = ResourceUtils.getFile("classpath:指标体系（0919）.xlsx");
//        excelParse.run(file.getAbsoluteFile().toString());
//    }

}
