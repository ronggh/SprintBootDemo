package cn.alan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {
    /**
     * HttpServlet请求：HttpServletRequest
     * @param request
     * @return
     */
    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request){
        request.setAttribute("msg","成功了....");
        request.setAttribute("code",200);
        // 转发到success页
        return "forward:/success";
    }

    /**
     * 请求域属性：@RequestAttribute
     * @param msg
     * @param code
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request
                       ){
        Object msg1 = request.getAttribute("msg");
        Map<String,Object> map = new HashMap<>();
        map.put("req_msg",msg1);
        map.put("another_msg",msg);
        return  map;
    }

    /**
     * 矩阵变量： @MatrixVariable
     * 路径： /car/sell;low=34;brand=byd,audi,yd
     *  2、SpringBoot默认是禁用了矩阵变量的功能
     *     手动开启：对于路径的处理。UrlPathHelper进行解析。
     *      removeSemicolonContent（ 移除分号内容） 支持矩阵变量的
     *  3、矩阵变量必须有url路径变量才能被解析
     * @param low
     * @param brands
     * @return
     */
    // 请求路径： http://localhost:8080/car/sell;low=34;brand=byd,audi,yd
    @GetMapping("/car/{path}")
    @ResponseBody
    public Map carSell(@MatrixVariable("low") Integer low,
                       @MatrixVariable("brand")List<String> brands,
                       @PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brands);
        map.put("path",path);
        return map;

    }

    /**
     * 如果有相同名称的变量，可以通过pathVar 指定用于哪个路径变量
     * @param bossAge
     * @param empAge
     * @return
     */
    // 请求路径： http://localhost:8080/boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    @ResponseBody
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String,Object> map = new HashMap<>();

        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }


}
