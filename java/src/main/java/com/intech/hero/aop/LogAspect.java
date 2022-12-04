package com.intech.hero.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Aspect
@Configuration
public class LogAspect {

	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd HH:mm");

	public static final int PERF4J_TIMETHRESHOLD = 0;

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution(public * *(..))")
	public void controllers() {

	}

	@Autowired
	private MeterRegistry registry;

	@Around("controllers()")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		registry.counter(pjp.getSignature().getName()).increment();
		return logAround(pjp, "CONTROLLER-");
	}

	private Object logAround(ProceedingJoinPoint pjp, String prefix) throws Throwable {
		logMethodIN(pjp);
		Object result = logExecutionTime(pjp, getPrefixedMethodSignature(pjp, prefix));
		logMethodOUT(pjp, result);
		return result;
	}

	private void logMethodIN(JoinPoint jp) {
		Logger logger = LoggerFactory.getLogger(jp.getSignature().getDeclaringTypeName());
		logger.info(jp.getSignature().getName() + " : " + getMethodParameters(jp));
	}

	private void logMethodOUT(JoinPoint jp, Object result) {
		Logger logger = LoggerFactory.getLogger(jp.getSignature().getDeclaringTypeName());
		if (logger.isDebugEnabled()) {
			if (result instanceof Collection && result != null) {
				if (logger.isTraceEnabled()) {
					logger.debug(jp.getSignature().getName() + " returns : " + result);
				} else {
					logger.debug(
							jp.getSignature().getName() + " returns : " + ((Collection<?>) result).size() + " results");
				}
			} else if (result instanceof Object[] && result != null) {
				if (logger.isTraceEnabled()) {
					logger.debug(jp.getSignature().getName() + " returns : " + result);
				} else {
					logger.debug(jp.getSignature().getName() + " returns : " + ((Object[]) result).length + " results");
				}
			} else {
				logger.debug(jp.getSignature().getName() + " returns : " + result);
			}
		}
	}

	public static Object logExecutionTime(ProceedingJoinPoint pjp, String tag) throws Throwable {
		return logExecutionTime(pjp, tag, getTimeThreshold());
	}

	public static Object logExecutionTime(ProceedingJoinPoint pjp, String tag, long timethreshold) throws Throwable {
		// Log4JStopWatch _stopWatch = getPerfLogger(timethreshold);
		// AgnosticTimingAspect ata = new AgnosticTimingAspect();
		// Profiled p = new Perf4jProfiled(tag, timethreshold);
		// return ata.runProfiledMethod(new Perf4jJoinPoint(pjp), p, _stopWatch);
		return pjp.proceed();
	}

	public static String getPrefixedMethodSignature(JoinPoint jp, String prefix) {
		return prefix + getMethodSignature(jp);
	}

	public static String getMethodSignature(JoinPoint jp) {
		StringBuilder sb = new StringBuilder(jp.getSignature().getDeclaringTypeName());
		sb.append(".");
		sb.append(jp.getSignature().getName());
		sb.append("(");
		sb.append(getMethodParameters(jp));
		sb.append(")");
		return noEl(sb.toString());
	}

	public static String getMethodParameters(JoinPoint jp) {
		StringBuilder sb = new StringBuilder();
		try {
			if (jp.getArgs() != null && jp.getArgs().length > 0) {
				for (Object arg : jp.getArgs()) {
					if (arg != null) {
						if (arg instanceof String || arg instanceof Number || arg instanceof Boolean) {
							sb.append(arg.toString());
						} else if (arg instanceof Date) {
							sb.append(DATE_FORMATTER.format((Date) arg));
						} else if (arg instanceof Object[]) {
							sb.append(Arrays.deepToString((Object[]) arg));
						} else {
							sb.append(arg.getClass().getSimpleName());
						}
					} else {
						sb.append("null");
					}
					sb.append(",");
				}
				if (sb.length() > 0) {
					sb.deleteCharAt(sb.length() - 1);
				}
			} else {
				sb.append("()");
			}
		} catch (Throwable t) {
			sb.append("toString error : " + t.getMessage());
		}
		return sb.toString();
	}

	public static String noEl(String tag) {
		return tag.replaceAll("[{}]", "");
	}

	public static long getTimeThreshold() {
		return PERF4J_TIMETHRESHOLD;
	}

}
