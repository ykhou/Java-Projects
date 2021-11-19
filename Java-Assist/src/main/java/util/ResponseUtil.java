package util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtil {
    public static void resTxtHtml(HttpServletResponse resp, String responseTxt) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        writer(resp, responseTxt);
    }

    public static void resApplicationJson(HttpServletResponse resp, String responseTxt) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        writer(resp, responseTxt);
    }

    private static void writer(HttpServletResponse resp, String responseTxt) {
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(responseTxt == null ? "" : responseTxt);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
