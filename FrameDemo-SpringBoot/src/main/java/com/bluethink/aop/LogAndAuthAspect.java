package com.bluethink.aop;


import com.bluethink.enums.ResultEnum;
import com.bluethink.exception.FrameException;
import com.bluethink.vo.GraphVO;
import com.bluethink.vo.ResultVO;
import com.bluethink.vo.UserVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/21
 */
@Slf4j
@Aspect
@Component
public class LogAndAuthAspect {
    private Gson gson = new Gson();
    /** 用来存放登陆的用户信息，
     * 简单实现获取用户信息的逻辑 */
    private UserVO userVO = null;

    /** 用户登陆切点 */
    @Pointcut("@annotation(com.bluethink.annotation.LoginAndRegister)")
    public void loginAndRegister(){
    }
    /** 用户AOP思想设置用户只能访问自己的图形数据，
     * 个人理解是在数据获取切点进行数据过滤然后返回 */
    @Pointcut("execution(* com.bluethink.service.GraphService.findGraphAll())")
    public void graphServiceAll(){
    }

    /** controller 切点 */
    @Pointcut("@annotation(com.bluethink.annotation.GraphAuth))")
    public void graphControllerLog(){
    }
    /** service 切点 */
    @Pointcut("execution(* com.bluethink.service.GraphService.*(..))")
    public void graphServiceLog(){
    }

    /** 环绕切法切用户登陆
     * 由于无法从session中获取用户信息
     * 在此记录成功登陆的用户信息 */
    @Around("loginAndRegister()")
    public Object loginAndRegisterAround(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("**********【loginStart】**********");
        log.info("==========【loginBefore】==========");
        /** 调用方法信息 */
        methodInfoLog(joinPoint);
        /** 执行原方法本身 */
        Object result = joinPoint.proceed();
        log.info("==========【loginAfter】==========");
        /** 登陆和注册方法返回值比较明确,
         * 只是不知道在这里强转操作是否规范 */
        ResultVO<UserVO> resultVO = (ResultVO) result;
        if (resultVO.getCode() == 0) {
            userVO = resultVO.getData();
        }
        /** 获取返回值 */
        log.info("【return】: {}", gson.toJson(result));
        log.info("***********【loginEnd】***********");
        return result;
    }

    @Around("graphServiceAll()")
    public Object graphServiceAllAround(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("**********【graphAllStart】**********");
        /** 验证用户登陆信息 */
        userInfoLog();
        log.info("==========【graphAllBefore】==========");
        /** 调用方法信息 */
        methodInfoLog(joinPoint);
        /** 执行原方法本身 */
        Object result = joinPoint.proceed();
        log.info("==========【graphAllAfter】==========");
        /** 此处应获取用户权限列表，简单起见
         * 用common代表普通用户
         * 用admin代表管理员*/
        if ("common".equals(userVO.getUserAuth())) {
            List<GraphVO> graphVOList = ((List<GraphVO>) result);
//            log.info("size: {}", graphVOList.size());
            List<GraphVO> filterList = new ArrayList<>();
            graphVOList.forEach(graphVO -> {
                if (userVO.getUserId().equals(graphVO.getUserId())) {
                    filterList.add(graphVO);
                }
            });
//            log.info("filterListSize: {}", filterList.size());
            /** 获取返回值 */
            log.info("【return】: {}", filterList.toString());
            log.info("***********【graphAllEnd】***********");
            /** 改变返回数据 */
            return filterList;
        } else if ("admin".equals(userVO.getUserAuth())){
            /** 获取返回值 */
            log.info("【return】: {}", gson.toJson(result));
            log.info("***********【graphAllEnd】***********");
            return result;
        } else {
            log.error("【未知的用户权限】");
            throw new FrameException(ResultEnum.UNKNOWN_USER_AUTH);
        }
    }

    /** 环绕切法切service */
//    @Around("graphServiceLog()")
//    public Object graphServiceAround(ProceedingJoinPoint joinPoint) throws Throwable{
//        log.info("**********【serviceAround】**********");
//        log.info("==========【serviceBefore】==========");
//        /** 调用方法信息 */
//        methodInfoLog(joinPoint);
//        /** 执行原方法本身 */
//        Object result = joinPoint.proceed();
//        log.info("==========【serviceAfter】==========");
//        /** 获取返回值 */
//        log.info("【return】: {}", gson.toJson(result));
//        log.info("***********【serviceEnd】***********");
//        return result;
//    }


    /** 环绕切法切controller */
    @Around("graphControllerLog()")
    public Object graphControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("**********【controllerStart】**********");
        /** 若有用户登陆，输出当前用户信息 */
        userInfoLog();
        log.info("==========【controllerBefore】==========");
        /** 调用方法信息 */
        methodInfoLog(joinPoint);
        /** 执行原方法本身 */
        Object result = joinPoint.proceed();
        log.info("==========【controllerAfter】==========");
        /** http请求信息 */
        httpInfoLog(joinPoint);
        if ("common".equals(userVO.getUserAuth())) {
            log.error("【用户权限不足】");
            throw new FrameException(ResultEnum.USER_AUTH_IS_NOT_ENOUGH);
        } else if ("admin".equals(userVO.getUserAuth())){
            /** 获取返回值 */
            log.info("【return】: {}", gson.toJson(result));
            log.info("***********【graphControllerEnd】***********");
            return result;
        } else {
            log.error("【未知的用户权限】");
            throw new FrameException(ResultEnum.UNKNOWN_USER_AUTH);
        }
    }

    private void userInfoLog() {
        if (userVO != null){
            log.info("**********【userInfo】**********");
            log.info("【id】: {}, 【user】: {}, 【auth】：{}", userVO.getUserId(), userVO.getUserName(), userVO.getUserAuth());
        } else {
            log.info("【user】: 当前没有用户登陆，停止操作");
//            log.error("【当前没有用户登陆】");
            throw new FrameException(ResultEnum.NO_USER_IS_LOGIN);
        }
    }


    private void methodInfoLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("【method】：{}", signature.getName());
        log.info("【package】: {}", signature.getDeclaringTypeName());
        log.info("【type】: {}",signature.getDeclaringType());

        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        log.info("【param】：{}", Arrays.toString(strings));
        log.info("【paramValue】: {}", Arrays.toString(joinPoint.getArgs()));
    }

    private void httpInfoLog(JoinPoint joinPoint) {
        /** 接收到请求，记录请求内容 */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        /** 记录下请求内容 */
        log.info("【requestURL】: {}", request.getRequestURL().toString());
        log.info("【requestMethod】: {}", request.getMethod());
        log.info("【ip】: {}", request.getRemoteAddr());
        log.info("【classMethod】: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

}
