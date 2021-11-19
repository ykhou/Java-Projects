package util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

public class FreemarkerUtil {
    private static Configuration cfg = null;
    static {
        cfg = new Configuration(Configuration.VERSION_2_3_0);
        cfg.setClassForTemplateLoading(FreemarkerUtil.class, "/tpls/");
        // template_update_delay
        cfg.setTemplateUpdateDelayMilliseconds(0L);


    }

    public static String parse2Str(String tplName, Object data) {
        try (StringWriter stringWriter = new StringWriter()) {
            Template template = cfg.getTemplate(tplName);
            // freemarker把解析模板的结果写入StringWriter，我们通过StringWriter获取结果即可
//            System.out.println(template.getEncoding());
            template.process(data, stringWriter);

            stringWriter.flush();
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        final String s = FreemarkerUtil.parse2Str("index.ftl", null);
        System.out.println(s);
    }
}
