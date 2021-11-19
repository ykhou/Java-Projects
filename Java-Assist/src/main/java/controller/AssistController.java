package controller;

import bean.ClassDefination;
import bean.FileDefination;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlCreateTableParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import res.ResultDTO;
import util.FreemarkerUtil;
import util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author kangkang
 */
@WebServlet(value = "/assist", name = "assistController")
public class AssistController extends HttpServlet {
    private static final Map<String, String> TYPE_MAPPING = new HashMap<>();
    static {
        TYPE_MAPPING.put("char","String");
        TYPE_MAPPING.put("varchar","String");
        TYPE_MAPPING.put("tinytext","String");
        TYPE_MAPPING.put("mediumtext","String");
        TYPE_MAPPING.put("longtext","String");
        TYPE_MAPPING.put("text","String");

        TYPE_MAPPING.put("tinyint","Byte");
        TYPE_MAPPING.put("smallint","Integer");
        TYPE_MAPPING.put("int","Integer");
        TYPE_MAPPING.put("bigint","Long");

        TYPE_MAPPING.put("float","Float");

        TYPE_MAPPING.put("double","Double");
        TYPE_MAPPING.put("numeric","Double");
        TYPE_MAPPING.put("decimal","Double");

        TYPE_MAPPING.put("timestamp","Date");
        TYPE_MAPPING.put("datetime","Date");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // application/x-www-form-urlencoded
        // @RequestBody @ResponseBody  application/json
        /// servlet接受application/json提交内容类型的方法
        JSONObject reqParam = new JSONObject();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            // 把请求体字符串转成了json对象
            reqParam = JSON.parseObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String cont = reqParam.getString("cont");
        try {
            ClassDefination classDefination = parseCreateTableSql(cont);
            String s = FreemarkerUtil.parse2Str("generateDO.ftl", classDefination);
            ResultDTO<String> dto = ResultDTO.buildSuccess(s);
            ResponseUtil.resApplicationJson(resp, JSON.toJSONString(dto));
        }catch (Exception e) {
            e.printStackTrace();
            ResponseUtil.resApplicationJson(resp, JSON.toJSONString(ResultDTO.buildFail(e.getMessage())));
        }


    }

    /**
     * cont：传过来的sql语句
     * 得到表的信息
     * */
    private ClassDefination parseCreateTableSql(String cont) {
        // 使用druid解析sql
        // 解析器MySqlCreateTableParser
        MySqlCreateTableParser createTableParser = new MySqlCreateTableParser(cont);
        MySqlCreateTableStatement statement = (MySqlCreateTableStatement) createTableParser.parseCreateTable();
        // 获取表名
        String tableNameOriginal = statement.getName().getSimpleName();
        String tableName = format(tableNameOriginal);
        ClassDefination classDefination = new ClassDefination();
        classDefination.setClassName(tableName);
        List<FileDefination> files = new ArrayList<>();

        statement.forEachColumn(cln -> {
            // 字段名 和 字段类型 和 注释
            String clnName = format(cln.getNameAsString());
            String clnType = formatDataType(cln.getDataType().getName());
            String commentOriginal = cln.getComment() != null ? cln.getComment().toString() : null;
            String comment = formatComment(commentOriginal);
            String javaType = TYPE_MAPPING.get(clnType);
            FileDefination fd = new FileDefination();
            fd.setFileName(clnName);
            fd.setType(javaType);
            fd.setComment(comment);
            files.add(fd);
        });
        classDefination.setFiles(files);
        return classDefination;
    }

    private String formatComment(String commentOriginal) {
        if (StringUtils.isBlank(commentOriginal)) {
            return null;
        }
        return commentOriginal.replace("'", "");
    }

    /**
    * 获取类型
    *
    */
    private String formatDataType(String dataType) {
        if (StringUtils.isBlank(dataType)) {
            throw new RuntimeException("字段类型不能为空");
        }
        dataType = dataType.trim().toLowerCase();
        if (dataType.contains("(")) {
            dataType = dataType.substring(0, dataType.indexOf("("));
        }
        return dataType;
    }

    /**
    * 规范表名和字段
    * 对建表语句格式化
    * 因为建表语句有的会带反引号
    */
    private String format(String tableNameOrColumnName) {
        if (StringUtils.isBlank(tableNameOrColumnName)) {
            throw new RuntimeException("表名或字段名不能为空");
        }
        // 用空替换反引号，去掉多于空格，转为小写
        String s = tableNameOrColumnName.replace("`", "").trim().toLowerCase();

        // 去掉下划线，并首字母大写
        // a_b: [a,b],
        String[] split = s.split("_");
        StringBuilder sb = new StringBuilder();
        // 首字母大写
        Stream.of(split).forEach(oneEle -> sb.append(StringUtils.capitalize(oneEle)));
        return sb.toString();
    }
}
