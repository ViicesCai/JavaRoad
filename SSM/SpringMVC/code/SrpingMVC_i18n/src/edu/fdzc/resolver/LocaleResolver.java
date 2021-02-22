package edu.fdzc.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**
 * @author CAI
 * @time 2021/2/22
 */
public class LocaleResolver implements org.springframework.web.servlet.LocaleResolver {

    /**
     * 解析返回local
     *
     * @param httpServletRequest 请求
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String localInfo = httpServletRequest.getParameter("locale");
        Locale locale;

        if (localInfo != null && !localInfo.isEmpty()) {
            locale = new Locale(localInfo.split("_")[0], localInfo.split("_")[1]);

        } else {
            locale = httpServletRequest.getLocale();
        }

        return locale;
    }

    /**
     * 修改locale
     *
     * @param httpServletRequest 请求
     * @param httpServletResponse 响应
     * @param locale 区域信息
     */
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
