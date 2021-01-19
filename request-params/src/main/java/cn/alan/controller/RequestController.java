package cn.alan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {

    /**
     * 请求参数常用注解：@PathVariable、@RequestHeader、@RequestParam、@CookieValue
     *
     * @param id
     * @param name
     * @param pv
     * @param userAgent
     * @param header
     * @param age
     * @param inters
     * @param params
     * @param _ga
     * @param cookie
     * @return
     */
    @GetMapping("/car/{id}/owner/{username}")
    @ResponseBody
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam(value = "age",required = false) Integer age,
                                      @RequestParam(value = "inters",required = false) List<String> inters,
                                      @RequestParam Map<String, String> params,
                                      @CookieValue(value = "_ga",required = false) String _ga,
                                      @CookieValue(value = "_ga",required = false) Cookie cookie) {

        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("headers", header);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        map.put("_ga", _ga);

        return map;
    }

    /**
     * HttpServlet请求：HttpServletRequest
     *
     * @param request
     * @return
     */
    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了....");
        request.setAttribute("code", 200);
        // 转发到success页
        return "forward:/success";
    }

    /**
     * 请求域属性：@RequestAttribute
     *
     * @param msg
     * @param code
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute(value = "msg", required = false) String msg,
                       @RequestAttribute(value = "code", required = false) Integer code,
                       HttpServletRequest request
    ) {
        Object msg1 = request.getAttribute("msg");
        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");

        Map<String, Object> map = new HashMap<>();
        map.put("req_msg", msg1);
        map.put("another_msg", msg);
        map.put("hello", hello);
        map.put("world", world);
        map.put("message", message);
        return map;
    }

    /**
     * 矩阵变量： @MatrixVariable
     * 路径： /car/sell;low=34;brand=byd,audi,yd
     * 2、SpringBoot默认是禁用了矩阵变量的功能
     * 手动开启：对于路径的处理。UrlPathHelper进行解析。
     * removeSemicolonContent（ 移除分号内容） 支持矩阵变量的
     * 3、矩阵变量必须有url路径变量才能被解析
     *
     * @param low
     * @param brands
     * @return
     */
    // 请求路径： http://localhost:8080/car/sell;low=34;brand=byd,audi,yd
    @GetMapping("/car/{path}")
    @ResponseBody
    public Map carSell(@MatrixVariable("low") Integer low,
                       @MatrixVariable("brand") List<String> brands,
                       @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brands);
        map.put("path", path);
        return map;

    }

    /**
     * 如果有相同名称的变量，可以通过pathVar 指定用于哪个路径变量
     *
     * @param bossAge
     * @param empAge
     * @return
     */
    // 请求路径： http://localhost:8080/boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    @ResponseBody
    public Map boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();

        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }

    /**
     * Map、Model、HttpServletRequest 都可以作为 HttpServletRequest 进行传递
     *
     * @param map
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/testparam")
    public String testParam(Map<String, Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        map.put("hello", "world666");
        model.addAttribute("world", "hello666");
        request.setAttribute("message", "Hello World");
        Cookie cookie = new Cookie("c1", "v1");
        response.addCookie(cookie);
        return "forward:/success";

    }


}
