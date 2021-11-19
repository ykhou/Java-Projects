package controller;

import util.FreemarkerUtil;
import util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author kangkang
 */
@WebServlet(value = "/")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 编码和内容类型
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // freemarker获取响应字符串
        String responseTxt = FreemarkerUtil.parse2Str("index.ftl", null);
        ResponseUtil.resTxtHtml(resp, responseTxt);
        // 写在try()里面的需要实现closeable的会自动实现close()，不需要手动在finish中写了
///        try(PrintWriter writer = resp.getWriter()) {
//            writer.write(responseTxt == null ? "" : responseTxt);
//            writer.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
