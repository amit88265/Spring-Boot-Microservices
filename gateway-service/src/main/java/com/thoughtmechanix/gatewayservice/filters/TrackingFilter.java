package com.thoughtmechanix.gatewayservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrackingFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;


    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        log.info("******Entered in filter type *****");
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent() {
        if (filterUtils.getCorrelationId() != null) {
            return true;
        }
        return false;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public Object run() throws ZuulException {
        log.info("******Entered in run *****");
        if (isCorrelationIdPresent()) {
            filterUtils.getCorrelationId();
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info(ctx.getRequest().getRequestURI());
        log.info("correlaion id is " + ctx.getZuulRequestHeaders().get(FilterUtils.CORRELATION_ID));
        return null;
    }
}
