package edu.fdzc.view;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义视图对象
 *
 * @author CAI
 * @time 2021/2/15
 */
public class MyView implements View {

    /**
     * 返回数据的内容类型
     */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("保存的数据：" + model);

        // 设置内容格式
        response.setContentType("text/html");
        response.getWriter().write("<h1>数据加载中</h1>");
    }

    /**
     * 返回数据的内容类型
     */
    @Override
    public String getContentType() {
        return "text/html";
    }
}
