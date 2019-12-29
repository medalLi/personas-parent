package com.desheng.bigdata.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    //filter过滤器的类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;//前置过滤器
    }

    @Override
    public int filterOrder() {//过滤器执行的优先级
        return 1;
    }

    @Override
    public boolean shouldFilter() {//是否执行当前过滤器，true执行，false不执行
        return true;
    }

    /**
     * 过滤的逻辑，判断用户请求参数中是否携带一个参数
     *  access-token
     *  request http://localhost:10010/api/user/2?access-token=
     *  如果从request中获取对应的parameter参数
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("access-token");
        if(StringUtils.isBlank(token)) { //异常拦截
            ctx.setSendZuulResponse(false);//true放行，false为拦截
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
