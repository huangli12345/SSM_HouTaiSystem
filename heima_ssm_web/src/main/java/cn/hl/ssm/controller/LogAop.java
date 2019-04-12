package cn.hl.ssm.controller;

import cn.hl.ssm.domain.SysLog;
import cn.hl.ssm.service.ISysLogService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    //访问的时间
    private Date visitTime;
    //访问的类
    private Class clazz;
    //访问的方法
    private Method method;

    //前置通知：主要获取 访问的时间、类、方法
    @Before("execution(* cn.hl.ssm.controller.*.*(..))")
    public  void doBefore(JoinPoint jp)throws Exception{
        //访问时间
        visitTime=new Date();
        //访问的类
        clazz=jp.getTarget().getClass();
        //访问的方法
        String methodName=jp.getSignature().getName();
        //访问的方法的参数
        Object[] args=jp.getArgs();
        if(args==null||args.length==0){
            // 获取无参数方法
            method=clazz.getMethod(methodName);
        }else{
            //有参数
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i < classArgs.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            // 获取有参数方法
            method=clazz.getMethod(methodName,classArgs);
        }

    }

    //后置通知:主要获取访问方法的时长、ip、url
    @After("execution(* cn.hl.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp)throws Exception{
        //访问时长
        String url="";
        long time=new Date().getTime()-visitTime.getTime();

        //获取url
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            //获取类上的@requestmapping注解中的值
            RequestMapping classAnnotation=(RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                //获取类上的注解的值
                String[] classValue=classAnnotation.value();

                //获取方法上的注解的值
                RequestMapping methodAnnotation=method.getAnnotation(RequestMapping.class);
                if(method!=null){
                    String[] methodValue=methodAnnotation.value();

                    url = classValue[0]+methodValue[0];

                    //获取ip
                    String ip=request.getRemoteAddr();

                    //获取操作者
                    SecurityContext context= SecurityContextHolder.getContext();
                    User user=(User)context.getAuthentication().getPrincipal();
                    String username=user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    //调用service层完成操作
                    sysLogService.save(sysLog);


                }

            }

        }



    }







}
