package edu.fdzc.view;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * 自定义视图解析器
 *
 * @author CAI
 * @time 2021/2/15
 */
@Controller
public class PhotoViewResovler implements ViewResolver, Ordered {
    private Integer order = 0; // 视图优先级

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith("photo:")) { // 根据视图名返回对象
            return new MyView();

        } else {
            // 如果不能处理，返回 null
            return null;
        }
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
