package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author CAI
 * @time 2021/2/14
 */
@Controller
@SessionAttributes(value = "msg")
public class OutputController {
    /**
     * SpringMVC 除了在方法上传原生的request和session外还能怎样把数据带给页面？
     *  1.可以在方法处传入Map或者Model或者ModelMap
     *      这些参数中保存的所有数据都会放在请求域中(request)，可以在页面通过请求域(requestScope)获取
     *
     *      关系：Map、Model、ModelMap：最终都是BindingAwareModelMap在工作；
     *      相当于BindingAwareModelMap中保存的数据都会被放在请求域中
     *
     *      Map(Interface(JDK))   Model(Interface(Spring))
     *
     *  2.方法的返回值可以变为ModelAndView类型
     *      既包含视图信息（页面地址）也包含模型数据（给页面带的数据）
     *      数据放置在请求域中
     *
     *  3.SpringMVC提供一种可以临时给Session保存数据的方式
     *      使用@SessionAttributes(value = ""):只能标注在类上
     *      给BindingAwareModelMap中保存数据或ModelAndView保存数据时，同时给Session中保存数据
     *      value指定保存数据时，要给session中放的数据的key
     *
     *      value={"msg"}：根据这个key，在Session中放入值
     *      types={String.class}：只要保存的为此类型的数据，在Session中放入值
     *      推荐：不使用@SessionAttributes，可能会引发异常
     *      给Session中放数据，推荐使用原生的ServletAPI
     *
     */

    @RequestMapping("/handle01")
    public String handle01(Map<String, Object> map) {
        map.put("msg", "Hello");
        System.out.println("Map的类型：" + map.getClass());
        return "success";
    }

    // model：一个接口
    @RequestMapping("/handle02")
    public String handle02(Model model) {
        model.addAttribute("msg", "Hi");
        System.out.println("Model的类型：" + model.getClass());

        return "success";
    }

    @RequestMapping("/handle03")
    public String handle03(ModelMap modelMap) {
        modelMap.addAttribute("msg", "How are you");
        System.out.println("ModelMap的类型：" + modelMap.getClass());

        return "success";
    }

    // ModelAndView：为视图携带数据
    @RequestMapping("/handle04")
    public ModelAndView handle04() {
        // 可以传入一个视图名：视图解析器会自动此视图名自动拼接得到页面的真实地址
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("msg", "HelloWorld");

        return modelAndView;
    }
}
