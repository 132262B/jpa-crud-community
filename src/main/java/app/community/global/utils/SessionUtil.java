package app.community.global.utils;

import app.community.api.member.dto.MemberInfo;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@UtilityClass
public class SessionUtil {

    public static final String SESSION_USER = "memberInfo";

    /**
     * 세션에 저장한 정보를 return 한다.
     *
     * @param key 세션에 저장한 key
     * @return Object
     */
    public static Object getAttribute(String key) {
        return RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 담긴 User 정보를 return 한다.
     *
     * @return MemberInfo
     */
    public static MemberInfo getMemberInfoAttribute() {
        return (MemberInfo) RequestContextHolder.getRequestAttributes().getAttribute(SESSION_USER, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 key로 value를 저장한다.
     *
     * @param key    세션에 저장한 key
     * @param object 세션에 담을 value
     */
    public static void setAttribute(String key, Object object) {
        RequestContextHolder.getRequestAttributes().setAttribute(key, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * User 데이터를 받아, 세션에 저장한다.
     *
     * @param memberInfo User 정보
     */
    public static void setMemberInfoAttribute(MemberInfo memberInfo) {
        RequestContextHolder.getRequestAttributes().setAttribute(SESSION_USER, memberInfo, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 key 로 저장한 정보를 삭제한다.
     *
     * @param key 세션에 저장한 key
     */
    public static void removeAttribute(String key) {
        RequestContextHolder.getRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 세션에 모든 데이터를 삭제한다.
     */
    public static void invalidate() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = req.getSession();
        session.invalidate();
    }

    /**
     * 세션ID를 return 한다.
     *
     * @return SessionID
     */
    public static String getSessionId() {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }
}